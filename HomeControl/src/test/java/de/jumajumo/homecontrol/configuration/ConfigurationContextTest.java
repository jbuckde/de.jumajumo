package de.jumajumo.homecontrol.configuration;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationContextTest
{
	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Test
	public void testExportJson() throws Exception
	{
		final ConfigurationContextHolder configurationHolder = new ConfigurationContextHolderImpl();

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, configurationHolder.getConfiguration());
	}

	@Test
	public void testExportXml()
	{
		final ConfigurationContextHolder configurationHolder = new ConfigurationContextHolderImpl();

		JAXBContext context;
		try
		{
			context = JAXBContext.newInstance(ConfigurationContext.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(configurationHolder.getConfiguration(),
					System.out);
		} catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testImportXml() throws Exception
	{
		final InputStream resourceAsStream = this.getClass()
				.getResourceAsStream("configuration.sample.xml");

		JAXBContext context = JAXBContext
				.newInstance(ConfigurationContext.class);

		final Unmarshaller unmarshaller = context.createUnmarshaller();

		ConfigurationContext configuration = (ConfigurationContext) unmarshaller
				.unmarshal(resourceAsStream);

	}
}
