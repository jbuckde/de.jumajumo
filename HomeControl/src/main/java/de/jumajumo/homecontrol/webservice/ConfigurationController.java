package de.jumajumo.homecontrol.webservice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.jumajumo.core.service.filestore.FileHandle;
import de.jumajumo.core.service.filestore.FileStorageService;
import de.jumajumo.homecontrol.configuration.ConfigurationContext;
import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;

/**
 * The Class ConfigurationController is used to read the current configuration.
 */
@RestController
@RequestMapping("/configuration")
public class ConfigurationController
{
	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping(method = RequestMethod.POST)
	public ConfigurationContext handleConfigFileUpload(
			HttpServletRequest request,
			@RequestParam(value = "fileUpload") MultipartFile fileUpload)
			throws Exception
	{
		this.fileStorageService.storeFile(fileUpload.getInputStream(),
				new FileHandle("configuratio"));

		return this.getConfiguration();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ConfigurationContext removeConfigurationFromStore()
	{
		this.fileStorageService.removeFile(new FileHandle("configuratio"));

		return this.getConfiguration();
	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public ConfigurationContext getConfiguration()
	{
		configurationContextHolder.invalidateContext();

		return configurationContextHolder.getConfiguration();
	}

	@RequestMapping(method = RequestMethod.GET, value = "xml", headers = "Accept=application/json")
	public void getConfigurationAsXml(HttpServletResponse response)
			throws JAXBException, IOException
	{
		configurationContextHolder.invalidateContext();

		final ConfigurationContext configuration = configurationContextHolder
				.getConfiguration();

		final JAXBContext context = JAXBContext
				.newInstance(ConfigurationContext.class);

		final Marshaller marshaller = context.createMarshaller();

		marshaller.marshal(configuration, response.getOutputStream());
		response.setContentType("application/xml");
	}
}
