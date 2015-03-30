package de.jumajumo.meetingpoint.api.facade;

import java.util.Date;
import java.util.List;

import de.jumajumo.core.api.facade.DataTransferObject;

public class MeetingDTO implements DataTransferObject
{
	private Long id;
	private String uuid;
	private int version;
	private String name;
	private Date date;
	private String address;
	private String initiatorName;
	private String initiatorImageUrl;
	private List<ParticipantDTO> participants;
	private List<FeedDTO> feeds;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((feeds == null) ? 0 : feeds.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((initiatorImageUrl == null) ? 0 : initiatorImageUrl
						.hashCode());
		result = prime * result
				+ ((initiatorName == null) ? 0 : initiatorName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((participants == null) ? 0 : participants.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingDTO other = (MeetingDTO) obj;
		if (address == null)
		{
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (date == null)
		{
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (feeds == null)
		{
			if (other.feeds != null)
				return false;
		} else if (!feeds.equals(other.feeds))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (initiatorImageUrl == null)
		{
			if (other.initiatorImageUrl != null)
				return false;
		} else if (!initiatorImageUrl.equals(other.initiatorImageUrl))
			return false;
		if (initiatorName == null)
		{
			if (other.initiatorName != null)
				return false;
		} else if (!initiatorName.equals(other.initiatorName))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (participants == null)
		{
			if (other.participants != null)
				return false;
		} else if (!participants.equals(other.participants))
			return false;
		if (uuid == null)
		{
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getInitiatorName()
	{
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName)
	{
		this.initiatorName = initiatorName;
	}

	public String getInitiatorImageUrl()
	{
		return initiatorImageUrl;
	}

	public void setInitiatorImageUrl(String initiatorImageUrl)
	{
		this.initiatorImageUrl = initiatorImageUrl;
	}

	public List<ParticipantDTO> getParticipants()
	{
		return participants;
	}

	public void setParticipants(List<ParticipantDTO> participants)
	{
		this.participants = participants;
	}

	public List<FeedDTO> getFeeds()
	{
		return feeds;
	}

	public void setFeeds(List<FeedDTO> feeds)
	{
		this.feeds = feeds;
	}

}
