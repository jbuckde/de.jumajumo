package de.jumajumo.meetingpoint.api.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.core.api.mapper.MappingService;
import de.jumajumo.core.persistence.UserProfile;
import de.jumajumo.core.service.UserService;

@Service
public class UserContextFacadeImpl implements UsersContextFacade
{
	@Autowired
	private UserService userService;

	@Autowired
	private MappingService mappingService;

	@Override
	public UserProfileDTO loadUserProfile()
	{
		// get the user profile of the logged in user
		final UserProfile userProfile = this.userService.getUserProfile();

		// map the user profile
		final UserProfileDTO userProfileDTO = this.mappingService.mapBOToDTO(
				userProfile, new UserProfileDTO());

		// return the data transfer object
		return userProfileDTO;
	}

}
