package de.jumajumo.homecontrol.service.scheduling;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.jumajumo.homecontrol.configuration.server.trigger.TriggerByScheduling;
import de.jumajumo.homecontrol.service.TriggerService;

public class SchedulingServiceImpl implements SchedulingService
{
	private final static Log LOGGER = LogFactory
			.getLog(SchedulingService.class);

	@Autowired
	private TriggerService triggerService;

	@Override
	public void runEveryMinute()
	{
		LOGGER.info("execute scheduled triggers: every minute.");

		final List<TriggerByScheduling> triggers = this.triggerService
				.findTriggersByScheduledInterval(ESchedulingPointInTime.EVERY_MINUTE);

		for (final TriggerByScheduling trigger : triggers)
		{
			try
			{
				this.triggerService.executeTrigger(trigger);
			} catch (RuntimeException ex)
			{
				LOGGER.error("execution of trigger <" + trigger.getName()
						+ "> terminated with exception <"
						+ ex.getLocalizedMessage() + ">");
			}
		}
	}

	@Override
	public void runEveryQuarterHour()
	{
		LOGGER.info("execute scheduled triggers: every quarter hour.");

		final List<TriggerByScheduling> triggers = this.triggerService
				.findTriggersByScheduledInterval(ESchedulingPointInTime.EVERY_QUARTER_HOUR);

		for (final TriggerByScheduling trigger : triggers)
		{
			try
			{
				this.triggerService.executeTrigger(trigger);
			} catch (RuntimeException ex)
			{
				LOGGER.error("execution of trigger <" + trigger.getName()
						+ "> terminated with exception <"
						+ ex.getLocalizedMessage() + ">");
			}
		}
	}

}
