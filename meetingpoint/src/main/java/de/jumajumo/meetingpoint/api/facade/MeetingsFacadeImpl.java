package de.jumajumo.meetingpoint.api.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import de.jumajumo.core.api.mapper.MappingService;
import de.jumajumo.core.persistence.UserProfile;
import de.jumajumo.core.service.CrudService;
import de.jumajumo.core.service.UserService;
import de.jumajumo.core.type.GeoPosition;
import de.jumajumo.meetingpoint.persistence.MeetingPoint;
import de.jumajumo.meetingpoint.persistence.MeetingPointFeed;
import de.jumajumo.meetingpoint.persistence.MeetingPointParticipant;

@Service
public class MeetingsFacadeImpl implements MeetingsFacade
{
	@Autowired
	private CrudService<MeetingPoint> crudService;

	@Autowired
	private CrudService<MeetingPointFeed> crudServiceFeed;

	@Autowired
	private CrudService<MeetingPointParticipant> crudServiceParticipant;

	@Autowired
	private MappingService mappingService;

	@Autowired
	private UserService userService;

	@Override
	public MeetingDTO createOrUpdateMeeting(MeetingDTO dto)
	{
		// declare the meeting
		MeetingPoint meeting = null;

		// search the object by given uuid
		if (!StringUtils.isEmpty(dto.getUuid()))
		{
			meeting = crudService.findByUUID(MeetingPoint.class,
					UUID.fromString(dto.getUuid()));
		}

		// create a new instance if necessary
		if (null == meeting)
		{
			meeting = new MeetingPoint();
			meeting.setInitiator(this.userService.getUserProfile());

			// add the initiator also as a participant
			final MeetingPointParticipant participant = new MeetingPointParticipant();
			participant.setUuid(UUID.randomUUID());
			participant.setUserProfile(this.userService.getUserProfile());
			// this.crudServiceParticipant.createOrUpdate(participant);

			meeting.getParticipants().add(participant);
		}

		// TODO: mapper infrastructure
		meeting.setVersion(dto.getVersion());
		meeting.setName(dto.getName());
		meeting.setDate(dto.getDate());
		meeting.getLocation().setAddress(dto.getAddress());

		final MeetingPoint persistentMeeting = crudService
				.createOrUpdate(meeting);

		return mappingService.mapBOToDTO(persistentMeeting, new MeetingDTO());
	}

	@Override
	public List<MeetingDTO> loadAllRelevant()
	{
		// calculate the valid date for meeting points ( 21 days in the future )
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -21);

		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("validDate", cal.getTime());
		parameters.put("userProfileUUID", this.userService.getUserProfile()
				.getUuid());

		final List<MeetingPoint> meetings = crudService.queryResultList(
				MeetingPoint.class, "Meeting.findAllRelevant", parameters);

		final List<MeetingDTO> result;
		if (null != meetings)
		{
			result = new ArrayList<MeetingDTO>();

			for (final MeetingPoint meeting : meetings)
			{
				final MeetingDTO dto = this.mappingService.mapBOToDTO(meeting,
						new MeetingDTO());

				result.add(dto);
			}
		} else
		{
			result = Collections.emptyList();
		}

		return result;
	}

	@Override
	public MeetingDTO loadByUuid(final UUID uuid)
	{
		final MeetingPoint meeting = this.crudService.findByUUID(
				MeetingPoint.class, uuid);

		final MeetingDTO dto = new MeetingDTO();

		return this.mappingService.mapBOToDTO(meeting, dto);
	}

	@Override
	public MeetingDTO participateToMeeting(final UUID meetingUUID)
	{
		final MeetingPoint meeting = this.crudService.findByUUID(
				MeetingPoint.class, meetingUUID);

		final MeetingDTO dto = new MeetingDTO();
		if (!this.isCurrentUserParticipantOfMeeting(meeting))
		{
			this.addCurrentUserToMeetingParticipants(meeting);

			final MeetingPoint persistedMeeting = this.crudService
					.createOrUpdate(meeting);

			return this.mappingService.mapBOToDTO(persistedMeeting, dto);
		} else
		{
			return this.mappingService.mapBOToDTO(meeting, dto);
		}
	}

	private void addCurrentUserToMeetingParticipants(MeetingPoint meeting)
	{
		final UserProfile currentUserProfile = this.userService
				.getUserProfile();

		// create a new participant instance
		final MeetingPointParticipant participant = new MeetingPointParticipant();
		participant.setUserProfile(currentUserProfile);

		// save the participant
		this.crudServiceParticipant.createOrUpdate(participant);

		meeting.getParticipants().add(participant);
	}

	private boolean isCurrentUserParticipantOfMeeting(MeetingPoint meeting)
	{
		for (MeetingPointParticipant participant : meeting.getParticipants())
		{
			final UserProfile currentUserProfile = this.userService
					.getUserProfile();
			if (currentUserProfile.equals(participant.getUserProfile()))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public MeetingDTO postMessage(final UUID meetingPointUUID,
			final FeedDTO feedDto)
	{
		// load the meeting point
		final MeetingPoint meeting = this.crudService.findByUUID(
				MeetingPoint.class, meetingPointUUID);

		// assert
		if (null == meeting)
		{
			throw new IllegalArgumentException(
					"meeting for message post could not be found.");
		}

		// create a new feed
		final MeetingPointFeed feed = new MeetingPointFeed();

		// map the values - don't trust client side parameters for posting user
		// and date
		feed.setPostedAt(new Date());
		feed.setPostedFrom(this.userService.getUserProfile());
		feed.setFeedMessage(feedDto.getFeedMessage());

		// save the feed
		this.crudServiceFeed.createOrUpdate(feed);

		// add the feed object to the meeting
		meeting.getFeeds().add(feed);

		// find the participant and set the message as last posted
		final UserProfile currentUserProfile = this.userService
				.getUserProfile();
		for (MeetingPointParticipant participant : meeting.getParticipants())
		{
			if (currentUserProfile.equals(participant.getUserProfile()))
			{
				participant.setLastFeedMessage(feedDto.getFeedMessage());

				this.crudServiceParticipant.createOrUpdate(participant);
			}
		}

		// save the meeting
		this.crudService.createOrUpdate(meeting);

		// build the result
		final MeetingDTO resultDTO = new MeetingDTO();
		this.mappingService.mapBOToDTO(meeting, resultDTO);

		return resultDTO;
	}

	@Override
	public MeetingDTO postGeoPosition(UUID meetingPointUUID,
			GeoPosition position)
	{
		// load the meeting point
		final MeetingPoint meeting = this.crudService.findByUUID(
				MeetingPoint.class, meetingPointUUID);

		// assert
		if (null == meeting)
		{
			throw new IllegalArgumentException(
					"meeting for message post could not be found.");
		}

		// find the participant and set the current position
		final UserProfile currentUserProfile = this.userService
				.getUserProfile();
		for (MeetingPointParticipant participant : meeting.getParticipants())
		{
			if (currentUserProfile.equals(participant.getUserProfile()))
			{
				participant.setCurrentPosition(position);
				participant.setPositionUpdated(new Date());

				this.crudServiceParticipant.createOrUpdate(participant);
			}
		}

		// build the result
		final MeetingDTO resultDTO = new MeetingDTO();
		this.mappingService.mapBOToDTO(meeting, resultDTO);

		return resultDTO;
	}
}
