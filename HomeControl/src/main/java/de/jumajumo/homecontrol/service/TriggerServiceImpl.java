package de.jumajumo.homecontrol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;
import de.jumajumo.homecontrol.configuration.server.ActionChain;
import de.jumajumo.homecontrol.configuration.server.trigger.Trigger;
import de.jumajumo.homecontrol.configuration.server.trigger.TriggerByClientSensor;
import de.jumajumo.homecontrol.configuration.server.trigger.TriggerByRestCall;
import de.jumajumo.homecontrol.configuration.server.trigger.TriggerByScheduling;
import de.jumajumo.homecontrol.service.scheduling.ESchedulingPointInTime;
import de.jumajumo.homecontrol.type.ActionChainResult;

@Service
public class TriggerServiceImpl implements TriggerService
{
	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Autowired
	private ActionChainService actionChainService;

	@Override
	public Trigger findTriggerByPath(String path)
	{
		final List<Trigger> triggers = this.configurationContextHolder
				.getConfiguration().getTriggers();

		for (final Trigger trigger : triggers)
		{
			if (trigger instanceof TriggerByRestCall)
			{
				TriggerByRestCall specialTrigger = (TriggerByRestCall) trigger;

				if (path.equalsIgnoreCase(specialTrigger.getRequestInfo()
						.getPath()))
				{
					return trigger;
				}
			}
		}

		throw new IllegalArgumentException(
				"the trigger-by-path with the path <" + path
						+ "> is not defined in the current configuration.");
	}

	@Override
	public Trigger findTriggerBySensor(final UUID sensorUuid)
	{
		final List<Trigger> triggers = this.configurationContextHolder
				.getConfiguration().getTriggers();

		for (final Trigger trigger : triggers)
		{
			if (trigger instanceof TriggerByClientSensor)
			{
				TriggerByClientSensor specialTrigger = (TriggerByClientSensor) trigger;

				if (specialTrigger.getSensorUuids().contains(sensorUuid))
				{
					return trigger;
				}
			}
		}

		throw new IllegalArgumentException(
				"the trigger-by-sensor with the sensor uuid <"
						+ sensorUuid.toString()
						+ "> is not defined in the current configuration.");
	}

	@Override
	public List<TriggerByScheduling> findTriggersByScheduledInterval(
			ESchedulingPointInTime pointInTime)
	{
		final List<Trigger> triggers = this.configurationContextHolder
				.getConfiguration().getTriggers();

		final List<TriggerByScheduling> result = new ArrayList<TriggerByScheduling>();

		for (final Trigger trigger : triggers)
		{
			if (trigger instanceof TriggerByScheduling)
			{
				TriggerByScheduling specialTrigger = (TriggerByScheduling) trigger;

				if (pointInTime.equals(specialTrigger.getRunAt()))
				{
					result.add(specialTrigger);
				}
			}
		}

		return result;
	}

	@Override
	public List<ActionChainResult> executeTrigger(final Trigger trigger)
	{
		final List<ActionChainResult> results = new ArrayList<ActionChainResult>();

		assert (null != trigger);

		final List<ActionChain> actionChains = this.actionChainService
				.findActionChainsByTrigger(trigger.getUuid());

		for (final ActionChain actionChain : actionChains)
		{
			results.add(this.actionChainService.executeActionChain(actionChain));
		}

		return results;
	}

}
