package de.jumajumo.homecontrol.configuration;

public interface ConfigurationContextHolder
{
	// the context holder provides the current configuration
	ConfigurationContext getConfiguration();

	void invalidateContext();
}
