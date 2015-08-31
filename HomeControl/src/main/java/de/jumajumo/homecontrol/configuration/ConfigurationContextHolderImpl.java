package de.jumajumo.homecontrol.configuration;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jumajumo.core.service.filestore.FileHandle;
import de.jumajumo.core.service.filestore.FileStorageService;

/**
 * The Class ConfigurationHolder is used to hold the currently loaded
 * configuration and provide it to the services
 */
@Component
public class ConfigurationContextHolderImpl implements
		ConfigurationContextHolder
{

	private final static Log LOGGER = LogFactory
			.getLog(ConfigurationContextHolder.class);

	private final static boolean USE_FILE_STORE = Boolean.parseBoolean(System
			.getProperty("config.use.filestore"));

	private ConfigurationContext configuration;

	@Autowired
	private FileStorageService fileStorageService;

	/**
	 * Constructor
	 */
	public ConfigurationContextHolderImpl()
	{
		super();
	}

	@Override
	public ConfigurationContext getConfiguration()
	{
		if (null == this.configuration)
		{
			this.loadConfiguration();
		}

		return this.configuration;
	}

	@Override
	public void invalidateContext()
	{
		this.loadConfiguration();
	}

	private void loadConfiguration()
	{
		InputStream resourceAsStream;

		if (USE_FILE_STORE)
		{
			resourceAsStream = this.loadConfigurationFromFileStore();
		} else
		{
			resourceAsStream = this.loadConfigurationFromResource();
		}

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

	private InputStream loadConfigurationFromFileStore()
	{
		LOGGER.info("load configuration from file store");

		return this.fileStorageService.loadFile(new FileHandle("configuratio"));
	}

	private InputStream loadConfigurationFromResource()
	{
		LOGGER.info("load configuration from resource");

		return this.getClass().getResourceAsStream("configuration.xml");
	}

}
