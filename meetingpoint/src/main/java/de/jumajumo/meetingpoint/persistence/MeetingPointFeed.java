package de.jumajumo.meetingpoint.persistence;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.jumajumo.core.persistence.AbstractEntityObject;
import de.jumajumo.core.persistence.EntityObject;
import de.jumajumo.core.persistence.UserProfile;

@Entity
@Table(name = "MEETINGPOINT_FEED")
public class MeetingPointFeed extends AbstractEntityObject implements
		EntityObject
{
	@Column(name = "POSTED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date postedAt;

	@OneToOne(cascade = CascadeType.REFRESH, orphanRemoval = false)
	@JoinColumn(name = "POSTED_FROM_ID")
	private UserProfile postedFrom;

	@Column(name = "FEED_MESSAGE", length = 255)
	private String feedMessage;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((feedMessage == null) ? 0 : feedMessage.hashCode());
		result = prime * result
				+ ((postedAt == null) ? 0 : postedAt.hashCode());
		result = prime * result
				+ ((postedFrom == null) ? 0 : postedFrom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingPointFeed other = (MeetingPointFeed) obj;
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

	public UserProfile getPostedFrom()
	{
		return postedFrom;
	}

	public void setPostedFrom(UserProfile postedFrom)
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
}
