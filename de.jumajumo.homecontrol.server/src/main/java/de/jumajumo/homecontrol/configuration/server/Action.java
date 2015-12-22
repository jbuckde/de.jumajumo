package de.jumajumo.homecontrol.configuration.server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import de.jumajumo.homecontrol.configuration.AbstractConfigurationObject;

/**
 * The Class Action defines a server side action item.
 * 
 * An action knows a collection of device-actors to request on processing the
 * action
 */
public class Action extends AbstractConfigurationObject
{
	@XmlElementWrapper(name = "actorUuids")
	@XmlElement(name = "actorUuid")
	private List<UUID> actorUuids;

	@XmlElement
	private String beanName;

	public List<UUID> getActorUuids()
	{
		if (null == actorUuids)
		{
			actorUuids = new ArrayList<UUID>();
		}

		return actorUuids;
	}

	public void setActorUuids(List<UUID> actorUuids)
	{
		this.actorUuids = actorUuids;
	}

	public String getBeanName()
	{
		return beanName;
	}

	public void setBeanName(String beanName)
	{
		this.beanName = beanName;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((actorUuids == null) ? 0 : actorUuids.hashCode());
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
		Action other = (Action) obj;
		if (actorUuids == null)
		{
			if (other.actorUuids != null)
				return false;
		} else if (!actorUuids.equals(other.actorUuids))
			return false;
		return true;
	}
}
