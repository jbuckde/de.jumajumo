package de.jumajumo.homecontrol.service;

import de.jumajumo.homecontrol.configuration.server.trigger.Trigger;

public interface TriggerService
{
	Trigger findTriggerByRestCall(final String path);
}
