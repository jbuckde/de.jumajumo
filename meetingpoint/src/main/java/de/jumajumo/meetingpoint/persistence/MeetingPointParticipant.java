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

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import de.jumajumo.core.persistence.AbstractEntityObject;
import de.jumajumo.core.persistence.EntityObject;
import de.jumajumo.core.persistence.UserProfile;
import de.jumajumo.core.type.GeoPosition;

@Entity
@Table(name = "MEETINGPOINT_PARTICIPANT")
@Converter(name = "geoPositionConverter", converterClass = de.jumajumo.core.persistence.GeoPositionConverter.class)
public class MeetingPointParticipant extends AbstractEntityObject implements
		EntityObject
{
	@OneToOne(cascade = CascadeType.REFRESH, orphanRemoval = false)
	@JoinColumn(name = "USERPROFILE_ID")
	private UserProfile userProfile;

	@Column(name = "LAST_FEED_MESSAGE", length = 255)
	private String lastFeedMessage;

	@Column(name = "GEO_POSITION", length = 255)
	@Convert("geoPositionConverter")
	private GeoPosition currentPosition;

	@Column(name = "POSITION_UPDATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date positionUpdated;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((currentPosition == null) ? 0 : currentPosition.hashCode());
		result = prime * result
				+ ((lastFeedMessage == null) ? 0 : lastFeedMessage.hashCode());
		result = prime * result
				+ ((positionUpdated == null) ? 0 : positionUpdated.hashCode());
		result = prime * result
				+ ((userProfile == null) ? 0 : userProfile.hashCode());
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
		MeetingPointParticipant other = (MeetingPointParticipant) obj;
		if (currentPosition == null)
		{
			if (other.currentPosition != null)
				return false;
		} else if (!currentPosition.equals(other.currentPosition))
			return false;
		if (lastFeedMessage == null)
		{
			if (other.lastFeedMessage != null)
				return false;
		} else if (!lastFeedMessage.equals(other.lastFeedMessage))
			return false;
		if (positionUpdated == null)
		{
			if (other.positionUpdated != null)
				return false;
		} else if (!positionUpdated.equals(other.positionUpdated))
			return false;
		if (userProfile == null)
		{
			if (other.userProfile != null)
				return false;
		} else if (!userProfile.equals(other.userProfile))
			return false;
		return true;
	}

	public UserProfile getUserProfile()
	{
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile)
	{
		this.userProfile = userProfile;
	}

	public String getLastFeedMessage()
	{
		return lastFeedMessage;
	}

	public void setLastFeedMessage(String lastFeedMessage)
	{
		this.lastFeedMessage = lastFeedMessage;
	}

	public GeoPosition getCurrentPosition()
	{
		return currentPosition;
	}

	public void setCurrentPosition(GeoPosition currentPosition)
	{
		this.currentPosition = currentPosition;
	}

	public Date getPositionUpdated()
	{
		return positionUpdated;
	}

	public void setPositionUpdated(Date positionUpdated)
	{
		this.positionUpdated = positionUpdated;
	}
}
