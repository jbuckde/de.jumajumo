package de.jumajumo.homecontrol.configuration.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import de.jumajumo.homecontrol.configuration.AbstractConfigurationObject;

public class Device extends AbstractConfigurationObject
{
	private String protocol;
	private String hostName;

	@XmlElementWrapper(name = "sensors")
	@XmlElement(name = "sensor")
	private List<Sensor> sensors;

	@XmlElementWrapper(name = "actors")
	@XmlElement(name = "actor")
	private List<Actor> actors;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result
				+ ((hostName == null) ? 0 : hostName.hashCode());
		result = prime * result
				+ ((protocol == null) ? 0 : protocol.hashCode());
		result = prime * result + ((sensors == null) ? 0 : sensors.hashCode());
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
		Device other = (Device) obj;
		if (actors == null)
		{
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (hostName == null)
		{
			if (other.hostName != null)
				return false;
		} else if (!hostName.equals(other.hostName))
			return false;
		if (protocol == null)
		{
			if (other.protocol != null)
				return false;
		} else if (!protocol.equals(other.protocol))
			return false;
		if (sensors == null)
		{
			if (other.sensors != null)
				return false;
		} else if (!sensors.equals(other.sensors))
			return false;
		return true;
	}

	public String getHostName()
	{
		return hostName;
	}

	public String getProtocol()
	{
		return protocol;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	public void setHostName(String hostName)
	{
		this.hostName = hostName;
	}

	public List<Sensor> getSensors()
	{
		if (null == sensors)
		{
			sensors = new ArrayList<Sensor>();
		}

		return sensors;
	}

	public void setSensors(List<Sensor> sensors)
	{
		this.sensors = sensors;
	}

	public List<Actor> getActors()
	{
		if (null == actors)
		{
			actors = new ArrayList<Actor>();
		}

		return actors;
	}

	public void setActors(List<Actor> actors)
	{
		this.actors = actors;
	}

}
