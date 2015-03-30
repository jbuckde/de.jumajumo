package de.jumajumo.homecontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;
import de.jumajumo.homecontrol.configuration.server.trigger.Trigger;
import de.jumajumo.homecontrol.configuration.server.trigger.TriggerByRestCall;

@Service
public class TriggerServiceImpl implements TriggerService
{
	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Override
	public Trigger findTriggerByRestCall(String path)
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
				"the trigger-by-rest-call with the path <" + path
						+ "> is not defined in the current configuration.");
	}

}
