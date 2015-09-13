package de.jumajumo.homecontrol.configuration.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import de.jumajumo.homecontrol.configuration.AbstractConfigurationObject;
import de.jumajumo.homecontrol.configuration.property.Property;

/**
 * The Class Condition carries information on a condition that can be used in
 * the action chain in order to decide whether an action should be performed or
 * not.
 */
public class Condition extends AbstractConfigurationObject
{
	private String beanName;
	private boolean expectedValue = true;

	@XmlElementWrapper(name = "properties")
	@XmlElement(name = "property")
	private List<Property> properties;

	public String getBeanName()
	{
		return beanName;
	}

	public void setBeanName(String beanName)
	{
		this.beanName = beanName;
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

	public boolean getExpectedValue()
	{
		return expectedValue;
	}

	public void setExpectedValue(boolean expectedValue)
	{
		this.expectedValue = expectedValue;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((beanName == null) ? 0 : beanName.hashCode());
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
		Condition other = (Condition) obj;
		if (beanName == null)
		{
			if (other.beanName != null)
				return false;
		} else if (!beanName.equals(other.beanName))
			return false;
		return true;
	}

}
