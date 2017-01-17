package de.jumajumo.homecontrol.service.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jumajumo.core.service.twitter.TwitterService;
import de.jumajumo.homecontrol.configuration.property.Property;
import twitter4j.StatusUpdate;

@Component("actionExecutorTwitterUpdate")
public class ActionExecutorTwitterUpdate implements ActionExecutor
{
	private final static Log LOGGER = LogFactory
			.getLog(ActionExecutorTwitterUpdate.class);

	@Autowired
	private TwitterService twitterService;

	@Override
	public boolean executeAction(final List<Property> properties)
	{
		this.twitterService
				.sendUpdate(new StatusUpdate("sent by twitter action: "
						+ DateFormat.getDateTimeInstance().format(new Date())));

		LOGGER.info("sent twitter update.");

		return true;
	}

}
