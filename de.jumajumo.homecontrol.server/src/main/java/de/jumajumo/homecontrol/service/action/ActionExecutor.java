package de.jumajumo.homecontrol.service.action;

import java.util.List;

import de.jumajumo.homecontrol.configuration.property.Property;

public interface ActionExecutor
{
	boolean executeAction(final List<Property> properties);
}
