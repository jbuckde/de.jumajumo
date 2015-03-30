package de.jumajumo.meetingpoint.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.core.api.facade.DataTransferObject;
import de.jumajumo.core.api.mapper.AbstractDTOMapper;
import de.jumajumo.core.api.mapper.DTOMapper;
import de.jumajumo.core.api.mapper.MappingService;
import de.jumajumo.core.persistence.EntityObject;
import de.jumajumo.core.persistence.UserProfile;
import de.jumajumo.meetingpoint.api.facade.UserProfileDTO;

@Service
public class UserProfileMapper extends
		AbstractDTOMapper<UserProfile, UserProfileDTO> implements
		DTOMapper<UserProfile, UserProfileDTO>
{
	@Autowired
	private MappingService mappingService;

	@Override
	public boolean canMap(Class<EntityObject> businessObjectClass,
			Class<DataTransferObject> dtoClass)
	{
		if (businessObjectClass.equals(UserProfile.class)
				&& dtoClass.equals(UserProfileDTO.class))
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public UserProfileDTO mapBOToDTO(UserProfile entityObject,
			UserProfileDTO dataTransferObject)
	{
		assert (null != dataTransferObject);

		final UserProfile bo = this.flush(entityObject);

		dataTransferObject.setUserName(bo.getUserName());
		dataTransferObject.setDisplayName(bo.getDisplayName());
		dataTransferObject.setImageUrl(bo.getImageUrl());
		dataTransferObject.setProfileUrl(bo.getProfileUrl());

		return dataTransferObject;
	}
}
