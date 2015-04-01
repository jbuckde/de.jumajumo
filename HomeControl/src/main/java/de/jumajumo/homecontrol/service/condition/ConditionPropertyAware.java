package de.jumajumo.homecontrol.service.condition;

import java.util.List;

import de.jumajumo.homecontrol.configuration.property.Property;

public interface ConditionPropertyAware
{
	void setProperties(final List<Property> properties);

}
