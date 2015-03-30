package de.jumajumo.meetingpoint.api.facade;

import java.util.List;
import java.util.UUID;

import de.jumajumo.core.type.GeoPosition;

public interface MeetingsFacade
{

	/**
	 * Creates the or update meeting.
	 *
	 * @param dto
	 *            the dto
	 * @return the meeting dto
	 */
	MeetingDTO createOrUpdateMeeting(final MeetingDTO dto);

	/**
	 * Load all relevant meeting points.
	 *
	 * @return the list
	 */
	List<MeetingDTO> loadAllRelevant();

	/**
	 * Load by uuid.
	 *
	 * @param uuid
	 *            the uuid
	 * @return the meeting dto
	 */
	MeetingDTO loadByUuid(UUID uuid);

	/**
	 * Participate to meeting.
	 *
	 * @param meetingUUID
	 *            the meeting uuid
	 * @return the meeting dto
	 */
	MeetingDTO participateToMeeting(UUID meetingUUID);

	/**
	 * Post a feed message.
	 *
	 * @param feedMessage
	 *            the feed message
	 */
	MeetingDTO postMessage(UUID meetingPointUUID, FeedDTO feed);

	/**
	 * Post geo position.
	 *
	 * @param meetingPointUUID
	 *            the meeting point uuid
	 * @param position
	 *            the position
	 * @return the meeting dto
	 */
	MeetingDTO postGeoPosition(UUID meetingPointUUID, GeoPosition position);
}
