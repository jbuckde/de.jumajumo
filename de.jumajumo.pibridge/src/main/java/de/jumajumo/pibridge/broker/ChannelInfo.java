package de.jumajumo.pibridge.broker;

import java.time.LocalDateTime;

public class ChannelInfo
{
	private LocalDateTime lastUpdate;
	private String value;

	public LocalDateTime getLastUpdate()
	{
		return lastUpdate;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
		this.lastUpdate = LocalDateTime.now();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ChannelInfo other = (ChannelInfo) obj;
		if (value == null)
		{
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
