package de.jumajumo.homecontrol.service.action;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jumajumo.homecontrol.configuration.property.Property;

@Component("actionExecutorMessageQueueProducer")
public class ActionExecutorMessageQueueProducer implements ActionExecutor
{
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public boolean executeAction(final List<Property> properties)
	{
		final String exchange = this.getProperty(properties, "exchange");
		final String routingKey = this.getProperty(properties, "routingKey");
		final String message = this.getProperty(properties, "message");

		rabbitTemplate.setExchange(exchange);
		rabbitTemplate.setRoutingKey(routingKey);

		rabbitTemplate.convertAndSend(message);

		return true;
	}

	private String getProperty(final List<Property> properties,
			final String propertyName)
	{
		for (final Property property : properties)
		{
			if (property.getKey().equals(propertyName))
			{
				return property.getValue();
			}
		}

		return "";
	}

}
