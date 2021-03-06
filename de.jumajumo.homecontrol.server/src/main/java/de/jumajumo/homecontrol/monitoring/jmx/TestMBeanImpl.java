package de.jumajumo.homecontrol.monitoring.jmx;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import de.jumajumo.core.service.weather.WeatherService;
import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;
import de.jumajumo.homecontrol.configuration.client.Device;
import de.jumajumo.homecontrol.configuration.server.Condition;
import de.jumajumo.homecontrol.service.ConditionService;
import de.jumajumo.homecontrol.service.DeviceConfigurationService;

@Component
@ManagedResource(objectName = "de.jumajumo.homecontrol.monitoring.jmx:name=TestBean", description = "the test bean")
public class TestMBeanImpl implements TestMBean
{
	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Autowired
	private ConditionService conditionService;

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private DeviceConfigurationService deviceConfigurationService;

	@Override
	@ManagedAttribute
	public String getValue()
	{
		return "the value";
	}

	@ManagedOperation
	public void callActor(final String actorUuid)
	{
		final UUID uuid = UUID.fromString(actorUuid);

		final Device device = this.deviceConfigurationService
				.findDeviceByActorUuid(uuid);

		this.deviceConfigurationService.callActor(device, uuid);
	}

	@ManagedOperation
	public boolean getConditionValue(final String conditionUuid)
	{
		final Condition condition = this.conditionService
				.findConditionByUuid(UUID.fromString(conditionUuid));

		return this.conditionService.isConditionSatisfied(condition);
	}

	@ManagedOperation
	public String getSunrise()
	{
		return this.weatherService.loadCurrentWeather().getSunrise()
				.toLocaleString();
	}

	@ManagedOperation
	public String getSunset()
	{
		return this.weatherService.loadCurrentWeather().getSunset()
				.toLocaleString();
	}
}
