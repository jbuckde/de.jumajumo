package de.jumajumo.homecontrol.service;

import java.util.List;
import java.util.UUID;

import de.jumajumo.homecontrol.configuration.server.trigger.Trigger;
import de.jumajumo.homecontrol.type.ActionChainResult;

public interface TriggerService
{
	Trigger findTriggerByPath(final String path);

	Trigger findTriggerBySensor(final UUID sensorUuid);

	List<ActionChainResult> executeTrigger(final Trigger trigger);
}
