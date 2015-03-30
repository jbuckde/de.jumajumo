package de.jumajumo.core.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.jumajumo.core.persistence.UserProfile;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private CrudService<UserProfile> userProfileCrudService;

	@Override
	public boolean isAuthenticated()
	{
		return getAuthentication().isAuthenticated();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserProfile getUserProfile()
	{
		// TODO: cache user profile, maybe in a special authentication object
		// get the login name
		final Authentication authentication = this.getAuthentication();
		final String providerUserId = authentication.getName();

		UserProfile userProfile;
		try
		{
			userProfile = getByProviderUserId(providerUserId);
		} catch (NoResultException ex)
		{
			throw new AccessDeniedException("user profile not registered", ex);
		}

		return userProfile;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserProfile getByProviderUserId(final String providerUserId)
			throws NoResultException
	{
		// load the user profile by login name
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("providerUserId", providerUserId);

		return this.userProfileCrudService.querySingleResult(UserProfile.class,
				"UserProfile.findByProviderUserId", parameters);
	}

	private Authentication getAuthentication()
	{
		final Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();

		return authentication;
	}

}
