package de.jumajumo.homecontrol.service.action;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import twitter4j.StatusUpdate;
import de.jumajumo.core.service.twitter.TwitterService;

@Component("actionExecutorTwitterUpdate")
public class ActionExecutorTwitterUpdate implements ActionExecutor
{
	private final static Log LOGGER = LogFactory
			.getLog(ActionExecutorTwitterUpdate.class);

	@Autowired
	private TwitterService twitterService;

	@Override
	public boolean executeAction()
	{
		this.twitterService.sendUpdate(new StatusUpdate(
				"sent by twitter action: "
						+ DateFormat.getDateTimeInstance().format(new Date())));

		LOGGER.info("sent twitter update.");

		return true;
	}

}
