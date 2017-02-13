package de.jumajumo.homecontrol.configuration;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class RuntimeInformationService
{
	private Map<String, Date> triggerLastExecute = new Hashtable<>();

	public boolean isTriggerBlocked(final String triggerName,
			final int blockInterval)
	{
		if (triggerLastExecute.containsKey(triggerName))
		{
			final long lastExecuteInMilli = triggerLastExecute.get(triggerName)
					.getTime();
			final long nowInMilli = new Date().getTime();

			if ((lastExecuteInMilli + (blockInterval * 1000)) > nowInMilli)
			{
				return true;
			}
		}

		return false;
	}

	public void memberTriggerExecute(final String triggerName)
	{
		if (triggerLastExecute.containsKey(triggerName))
		{
			triggerLastExecute.remove(triggerName);
		}

		triggerLastExecute.put(triggerName, new Date());
	}
}
