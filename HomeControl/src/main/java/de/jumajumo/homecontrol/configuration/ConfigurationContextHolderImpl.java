package de.jumajumo.homecontrol.configuration;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

/**
 * The Class ConfigurationHolder is used to hold the currently loaded
 * configuration and provide it to the services
 */
@Component
public class ConfigurationContextHolderImpl implements
		ConfigurationContextHolder
{

	private ConfigurationContext configuration;

	/**
	 * Constructor
	 */
	public ConfigurationContextHolderImpl()
	{
		super();

		this.loadConfiguration();
	}

	private void loadConfiguration()
	{
		final InputStream resourceAsStream = this.getClass()
				.getResourceAsStream("configuration.xml");

		try
		{
			final JAXBContext context = JAXBContext
					.newInstance(ConfigurationContext.class);

			final Unmarshaller unmarshaller = context.createUnmarshaller();

			this.configuration = (ConfigurationContext) unmarshaller
					.unmarshal(resourceAsStream);

		} catch (JAXBException e)
		{
			throw new IllegalArgumentException(
					"error on loading configuration!", e);
		}
	}

	@Override
	public ConfigurationContext getConfiguration()
	{
		if (null == this.configuration)
		{
			this.configuration = new ConfigurationContext();
		}

		return this.configuration;
	}

}
