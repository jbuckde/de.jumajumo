package de.jumajumo.meetingpoint.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.core.api.facade.DataTransferObject;
import de.jumajumo.core.api.mapper.AbstractDTOMapper;
import de.jumajumo.core.api.mapper.DTOMapper;
import de.jumajumo.core.api.mapper.MappingService;
import de.jumajumo.core.persistence.EntityObject;
import de.jumajumo.meetingpoint.api.facade.FeedDTO;
import de.jumajumo.meetingpoint.api.facade.MeetingDTO;
import de.jumajumo.meetingpoint.api.facade.ParticipantDTO;
import de.jumajumo.meetingpoint.persistence.MeetingPoint;
import de.jumajumo.meetingpoint.persistence.MeetingPointFeed;
import de.jumajumo.meetingpoint.persistence.MeetingPointParticipant;

@Service
public class MeetingPointMapper extends
		AbstractDTOMapper<MeetingPoint, MeetingDTO> implements
		DTOMapper<MeetingPoint, MeetingDTO>
{
	@Autowired
	private MappingService mappingService;

	@Override
	public boolean canMap(Class<EntityObject> businessObjectClass,
			Class<DataTransferObject> dtoClass)
	{
		if (businessObjectClass.equals(MeetingPoint.class)
				&& dtoClass.equals(MeetingDTO.class))
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public MeetingDTO mapBOToDTO(MeetingPoint entityObject,
			MeetingDTO dataTransferObject)
	{
		assert (null != dataTransferObject);

		final MeetingPoint bo = this.flush(entityObject);

		dataTransferObject.setId(bo.getId());
		dataTransferObject.setUuid(bo.getUuid().toString());
		dataTransferObject.setVersion(bo.getVersion());
		dataTransferObject.setName(bo.getName());
		dataTransferObject.setDate(bo.getDate());
		dataTransferObject.setAddress(bo.getLocation().getAddress());
		dataTransferObject.setInitiatorName(bo.getInitiator().getDisplayName());
		dataTransferObject
				.setInitiatorImageUrl(bo.getInitiator().getImageUrl());

		final List<ParticipantDTO> participants = new ArrayList<ParticipantDTO>();

		for (MeetingPointParticipant participant : bo.getParticipants())
		{
			final ParticipantDTO dto = new ParticipantDTO();
			this.mappingService.mapBOToDTO(participant, dto);

			if (bo.getInitiator().equals(participant.getUserProfile()))
			{
				dto.setInitiator(true);
			}

			participants.add(dto);
		}
		dataTransferObject.setParticipants(participants);

		final List<FeedDTO> feeds = new ArrayList<FeedDTO>();
		for (MeetingPointFeed feed : bo.getFeeds())
		{
			final FeedDTO feedDto = new FeedDTO();
			this.mappingService.mapBOToDTO(feed, feedDto);
			feeds.add(feedDto);
		}
		dataTransferObject.setFeeds(feeds);

		return dataTransferObject;
	}
}
