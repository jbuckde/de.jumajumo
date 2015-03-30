package de.jumajumo.meetingpoint.api.facade;

import java.util.Date;

import de.jumajumo.core.api.facade.DataTransferObject;

public class FeedDTO implements DataTransferObject
{
	private Date postedAt;
	private String postedFrom;
	private String postedFromImageUrl;
	private String feedMessage;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((feedMessage == null) ? 0 : feedMessage.hashCode());
		result = prime * result
				+ ((postedAt == null) ? 0 : postedAt.hashCode());
		result = prime * result
				+ ((postedFrom == null) ? 0 : postedFrom.hashCode());
		result = prime
				* result
				+ ((postedFromImageUrl == null) ? 0 : postedFromImageUrl
						.hashCode());
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
		FeedDTO other = (FeedDTO) obj;
		if (feedMessage == null)
		{
			if (other.feedMessage != null)
				return false;
		} else if (!feedMessage.equals(other.feedMessage))
			return false;
		if (postedAt == null)
		{
			if (other.postedAt != null)
				return false;
		} else if (!postedAt.equals(other.postedAt))
			return false;
		if (postedFrom == null)
		{
			if (other.postedFrom != null)
				return false;
		} else if (!postedFrom.equals(other.postedFrom))
			return false;
		if (postedFromImageUrl == null)
		{
			if (other.postedFromImageUrl != null)
				return false;
		} else if (!postedFromImageUrl.equals(other.postedFromImageUrl))
			return false;
		return true;
	}

	public Date getPostedAt()
	{
		return postedAt;
	}

	public void setPostedAt(Date postedAt)
	{
		this.postedAt = postedAt;
	}

	public String getPostedFrom()
	{
		return postedFrom;
	}

	public void setPostedFrom(String postedFrom)
	{
		this.postedFrom = postedFrom;
	}

	public String getFeedMessage()
	{
		return feedMessage;
	}

	public void setFeedMessage(String feedMessage)
	{
		this.feedMessage = feedMessage;
	}

	public String getPostedFromImageUrl()
	{
		return postedFromImageUrl;
	}

	public void setPostedFromImageUrl(String postedFromImageUrl)
	{
		this.postedFromImageUrl = postedFromImageUrl;
	}
}
