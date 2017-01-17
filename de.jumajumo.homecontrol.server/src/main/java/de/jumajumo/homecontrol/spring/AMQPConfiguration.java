package de.jumajumo.homecontrol.spring;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.jumajumo.homecontrol.configuration.ConfigurationContext;
import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;

@Configuration
public class AMQPConfiguration
{

	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Bean
	public ConnectionFactory connectionFactory()
	{
		final ConfigurationContext configuration = configurationContextHolder
				.getConfiguration();

		final CachingConnectionFactory factory = new CachingConnectionFactory(
				configuration.getPropertyValue("amqpServer", "localhost"));
		factory.setPort(Integer.valueOf(
				configuration.getPropertyValue("amqpServerPort", "5672")));
		factory.setUsername(configuration.getPropertyValue("amqpUserName", ""));
		factory.setPassword(configuration.getPropertyValue("amqpPassword", ""));

		return factory;
	}

	@Bean
	public AmqpAdmin amqpAdmin()
	{
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate()
	{
		return new RabbitTemplate(connectionFactory());
	}

}
