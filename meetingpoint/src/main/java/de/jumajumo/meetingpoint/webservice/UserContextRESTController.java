package de.jumajumo.meetingpoint.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.jumajumo.meetingpoint.api.facade.UserProfileDTO;
import de.jumajumo.meetingpoint.api.facade.UsersContextFacade;

@RestController
@RequestMapping("/usercontext")
public class UserContextRESTController
{
	@Autowired
	private UsersContextFacade userContextFacade;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public UserProfileDTO loadUserContext()
	{
		return userContextFacade.loadUserProfile();
	}
}
