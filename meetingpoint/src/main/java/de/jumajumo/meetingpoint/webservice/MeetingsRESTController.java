package de.jumajumo.meetingpoint.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.jumajumo.core.type.GeoPosition;
import de.jumajumo.meetingpoint.api.facade.FeedDTO;
import de.jumajumo.meetingpoint.api.facade.MeetingDTO;
import de.jumajumo.meetingpoint.api.facade.MeetingsFacade;

@RestController
@RequestMapping("/meetings")
public class MeetingsRESTController
{

	@Autowired
	private MeetingsFacade meetingsFacade;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public MeetingDTO createMeeting(@RequestBody MeetingDTO dto)
	{
		final MeetingDTO meetingDTO = meetingsFacade.createOrUpdateMeeting(dto);

		return meetingDTO;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
	public List<MeetingDTO> createMeetingBulk(@RequestBody List<MeetingDTO> dtos)
	{
		final List<MeetingDTO> resultDTOList = new ArrayList<MeetingDTO>();

		for (final MeetingDTO meetingDTO : dtos)
		{
			resultDTOList.add(meetingsFacade.createOrUpdateMeeting(meetingDTO));
		}

		return resultDTOList;
	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<MeetingDTO> loadAll()
	{
		return meetingsFacade.loadAllRelevant();
	}

	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET, headers = "Accept=application/json")
	public MeetingDTO loadMeeting(@PathVariable(value = "uuid") UUID uuid)
	{
		return meetingsFacade.loadByUuid(uuid);
	}

	@RequestMapping(value = "/{uuid}/participate", method = RequestMethod.POST, headers = "Accept=application/json")
	public MeetingDTO addParticipation(
			@PathVariable(value = "uuid") UUID meetingUUID)
	{
		return this.meetingsFacade.participateToMeeting(meetingUUID);
	}

	@RequestMapping(value = "/{uuid}/postmessage", method = RequestMethod.POST, headers = "Accept=application/json")
	public MeetingDTO postMessage(
			@PathVariable(value = "uuid") UUID meetingPointUUID,
			@RequestBody FeedDTO feedDto)
	{
		return this.meetingsFacade.postMessage(meetingPointUUID, feedDto);
	}

	@RequestMapping(value = "/{uuid}/postgeoposition", method = RequestMethod.POST, headers = "Accept=application/json")
	public MeetingDTO postGeoLocation(
			@PathVariable(value = "uuid") UUID meetingPointUUID,
			@RequestBody GeoPosition position)
	{
		return this.meetingsFacade.postGeoPosition(meetingPointUUID, position);
	}
}
