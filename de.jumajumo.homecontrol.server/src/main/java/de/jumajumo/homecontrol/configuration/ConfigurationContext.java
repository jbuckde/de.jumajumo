package de.jumajumo.homecontrol.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import de.jumajumo.homecontrol.configuration.client.Device;
import de.jumajumo.homecontrol.configuration.property.Property;
import de.jumajumo.homecontrol.configuration.server.Action;
import de.jumajumo.homecontrol.configuration.server.ActionChain;
import de.jumajumo.homecontrol.configuration.server.Condition;
import de.jumajumo.homecontrol.configuration.server.trigger.Trigger;

@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigurationContext implements Serializable
{
	private static final long serialVersionUID = -2904843840064791996L;

	@XmlElementWrapper(name = "properties")
	@XmlElement(name = "property")
	private List<Property> properties;

	@XmlElementWrapper(name = "actionChains")
	@XmlElement(name = "actionChain")
	private List<ActionChain> actionChains;

	@XmlElementWrapper(name = "devices")
	@XmlElement(name = "device")
	private List<Device> devices;

	@XmlElementWrapper(name = "triggers")
	@XmlElement(name = "trigger")
	private List<Trigger> triggers;

	@XmlElementWrapper(name = "conditions")
	@XmlElement(name = "condition")
	private List<Condition> conditions;

	@XmlElementWrapper(name = "actions")
	@XmlElement(name = "action")
	private List<Action> actions;

	public String getPropertyValue(final String propertyName,
			final String defaultValue)
	{
		for (final Property property : this.getProperties())
		{
			if (property.getKey().equals(propertyName))
			{
				return property.getValue();
			}
		}

		return defaultValue;
	}

	public List<Property> getProperties()
	{
		if (null == this.properties)
		{
			this.properties = new ArrayList<Property>();
		}

		return properties;
	}

	public void setProperties(List<Property> properties)
	{
		this.properties = properties;
	}

	public List<ActionChain> getActionChains()
	{
		if (null == actionChains)
		{
			actionChains = new ArrayList<ActionChain>();
		}

		return actionChains;
	}

	public void setActionChains(List<ActionChain> actionChains)
	{
		this.actionChains = actionChains;
	}

	public List<Device> getDevices()
	{
		if (null == devices)
		{
			devices = new ArrayList<Device>();
		}

		return devices;
	}

	public void setDevices(List<Device> devices)
	{
		this.devices = devices;
	}

	public List<Trigger> getTriggers()
	{
		if (null == triggers)
		{
			triggers = new ArrayList<Trigger>();
		}

		return triggers;
	}

	public void setTriggers(List<Trigger> triggers)
	{
		this.triggers = triggers;
	}

	public List<Condition> getConditions()
	{
		if (null == conditions)
		{
			conditions = new ArrayList<Condition>();
		}

		return conditions;
	}

	public void setConditions(List<Condition> conditions)
	{
		this.conditions = conditions;
	}

	public List<Action> getActions()
	{
		if (null == actions)
		{
			actions = new ArrayList<Action>();
		}

		return actions;
	}

	public void setActions(List<Action> actions)
	{
		this.actions = actions;
	}
}
