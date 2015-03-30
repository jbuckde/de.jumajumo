package de.jumajumo.meetingpoint.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.core.api.facade.DataTransferObject;
import de.jumajumo.core.api.mapper.AbstractDTOMapper;
import de.jumajumo.core.api.mapper.DTOMapper;
import de.jumajumo.core.api.mapper.MappingService;
import de.jumajumo.core.persistence.EntityObject;
import de.jumajumo.meetingpoint.api.facade.FeedDTO;
import de.jumajumo.meetingpoint.persistence.MeetingPointFeed;

@Service
public class FeedMapper extends AbstractDTOMapper<MeetingPointFeed, FeedDTO>
		implements DTOMapper<MeetingPointFeed, FeedDTO>
{
	@Autowired
	private MappingService mappingService;

	@Override
	public boolean canMap(Class<EntityObject> businessObjectClass,
			Class<DataTransferObject> dtoClass)
	{
		if (businessObjectClass.equals(MeetingPointFeed.class)
				&& dtoClass.equals(FeedDTO.class))
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public FeedDTO mapBOToDTO(MeetingPointFeed entityObject,
			FeedDTO dataTransferObject)
	{
		assert (null != dataTransferObject);

		final MeetingPointFeed bo = this.flush(entityObject);

		dataTransferObject.setPostedAt(bo.getPostedAt());
		dataTransferObject.setPostedFrom(bo.getPostedFrom().getDisplayName());
		dataTransferObject.setPostedFromImageUrl(bo.getPostedFrom()
				.getImageUrl());
		dataTransferObject.setFeedMessage(bo.getFeedMessage());

		return dataTransferObject;
	}
}
