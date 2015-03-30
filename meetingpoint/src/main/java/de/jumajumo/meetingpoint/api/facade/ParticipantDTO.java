package de.jumajumo.meetingpoint.api.facade;

import java.util.Date;

import de.jumajumo.core.api.facade.DataTransferObject;
import de.jumajumo.core.type.GeoPosition;

public class ParticipantDTO implements DataTransferObject
{
	private boolean isInitiator;
	private String displayName;
	private String imageUrl;

	private String lastFeedMessage;

	private GeoPosition currentPosition;
	private Date positionUpdated;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currentPosition == null) ? 0 : currentPosition.hashCode());
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result
				+ ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + (isInitiator ? 1231 : 1237);
		result = prime * result
				+ ((lastFeedMessage == null) ? 0 : lastFeedMessage.hashCode());
		result = prime * result
				+ ((positionUpdated == null) ? 0 : positionUpdated.hashCode());
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
		ParticipantDTO other = (ParticipantDTO) obj;
		if (currentPosition == null)
		{
			if (other.currentPosition != null)
				return false;
		} else if (!currentPosition.equals(other.currentPosition))
			return false;
		if (displayName == null)
		{
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (imageUrl == null)
		{
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (isInitiator != other.isInitiator)
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
		return true;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
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

	public boolean isInitiator()
	{
		return isInitiator;
	}

	public void setInitiator(boolean isInitiator)
	{
		this.isInitiator = isInitiator;
	}

}
