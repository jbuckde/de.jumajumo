package de.jumajumo.meetingpoint.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.core.api.facade.DataTransferObject;
import de.jumajumo.core.api.mapper.AbstractDTOMapper;
import de.jumajumo.core.api.mapper.DTOMapper;
import de.jumajumo.core.api.mapper.MappingService;
import de.jumajumo.core.persistence.EntityObject;
import de.jumajumo.meetingpoint.api.facade.ParticipantDTO;
import de.jumajumo.meetingpoint.persistence.MeetingPointParticipant;

@Service
public class ParticipantMapper extends
		AbstractDTOMapper<MeetingPointParticipant, ParticipantDTO> implements
		DTOMapper<MeetingPointParticipant, ParticipantDTO>
{
	@Autowired
	private MappingService mappingService;

	@Override
	public boolean canMap(Class<EntityObject> businessObjectClass,
			Class<DataTransferObject> dtoClass)
	{
		if (businessObjectClass.equals(MeetingPointParticipant.class)
				&& dtoClass.equals(ParticipantDTO.class))
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public ParticipantDTO mapBOToDTO(MeetingPointParticipant entityObject,
			ParticipantDTO dataTransferObject)
	{
		assert (null != dataTransferObject);

		final MeetingPointParticipant bo = this.flush(entityObject);

		dataTransferObject.setInitiator(false); // default
		dataTransferObject.setDisplayName(bo.getUserProfile().getDisplayName());
		dataTransferObject.setImageUrl(bo.getUserProfile().getImageUrl());
		dataTransferObject.setLastFeedMessage(bo.getLastFeedMessage());
		dataTransferObject.setCurrentPosition(bo.getCurrentPosition());
		dataTransferObject.setPositionUpdated(bo.getPositionUpdated());

		return dataTransferObject;
	}
}
