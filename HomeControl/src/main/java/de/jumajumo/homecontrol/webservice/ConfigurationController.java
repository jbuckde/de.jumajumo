package de.jumajumo.homecontrol.webservice;

import javax.servlet.http.HttpServletRequest;

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
}
