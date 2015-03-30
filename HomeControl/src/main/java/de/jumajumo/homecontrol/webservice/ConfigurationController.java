package de.jumajumo.homecontrol.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public ConfigurationContext getConfiguration()
	{
		return configurationContextHolder.getConfiguration();
	}
}
