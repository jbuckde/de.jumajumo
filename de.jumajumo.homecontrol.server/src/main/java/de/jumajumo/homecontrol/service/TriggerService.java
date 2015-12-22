package de.jumajumo.homecontrol.service;

import java.util.List;
import java.util.UUID;

import de.jumajumo.homecontrol.configuration.server.trigger.Trigger;
import de.jumajumo.homecontrol.configuration.server.trigger.TriggerByScheduling;
import de.jumajumo.homecontrol.service.scheduling.ESchedulingPointInTime;
import de.jumajumo.homecontrol.type.ActionChainResult;

public interface TriggerService
{
	Trigger findTriggerByPath(final String path);

	Trigger findTriggerBySensor(final UUID sensorUuid);

	List<TriggerByScheduling> findTriggersByScheduledInterval(
			final ESchedulingPointInTime pointInTime);

	List<ActionChainResult> executeTrigger(final Trigger trigger);
}
