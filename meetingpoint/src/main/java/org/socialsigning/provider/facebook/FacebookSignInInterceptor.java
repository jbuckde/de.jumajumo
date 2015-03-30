package org.socialsigning.provider.facebook;

import javax.persistence.NoResultException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ProviderSignInInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

import de.jumajumo.core.persistence.EnumSocialSecurityProvider;
import de.jumajumo.core.persistence.UserProfile;
import de.jumajumo.core.service.CrudService;
import de.jumajumo.core.service.UserService;

@Component
public class FacebookSignInInterceptor implements
		ProviderSignInInterceptor<Facebook>
{
	private final static Log LOGGER = LogFactory
			.getLog(FacebookSignInInterceptor.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CrudService<UserProfile> crudService;

	@Override
	public void preSignIn(ConnectionFactory<Facebook> connectionFactory,
			MultiValueMap<String, String> parameters, WebRequest request)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void postSignIn(Connection<Facebook> connection, WebRequest request)
	{
		UserProfile userProfile;

		try
		{
			userProfile = this.userService.getByProviderUserId(connection
					.getKey().getProviderUserId());
		} catch (NoResultException ex)
		{
			LOGGER.info("provider user id not found: create a new user profile");

			userProfile = new UserProfile();

			userProfile.setVersion(-1);
			userProfile.setProvider(EnumSocialSecurityProvider.FACEBOOK);
			userProfile.setProviderUserId(connection.getKey()
					.getProviderUserId());
			userProfile.setLoginName(connection.getKey().getProviderUserId());
		}

		userProfile.setProfileUrl(connection.getProfileUrl());
		userProfile.setUserName(connection.getDisplayName());
		userProfile.setDisplayName(connection.getDisplayName());
		userProfile.setImageUrl(connection.getImageUrl());

		this.crudService.createOrUpdate(userProfile);
	}
}
