package de.jumajumo.homecontrol.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.homecontrol.configuration.ConfigurationContext;
import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;
import de.jumajumo.homecontrol.configuration.client.Actor;
import de.jumajumo.homecontrol.configuration.client.Device;
import de.jumajumo.homecontrol.utils.http.ClientActorRequestTemplate;

@Service
public class DeviceServiceImpl implements DeviceService
{
	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Override
	public Device findDeviceByActorUuid(UUID actorUuid)
	{
		final ConfigurationContext configuration = configurationContextHolder
				.getConfiguration();

		final List<Device> devices = configuration.getDevices();

		for (Device device : devices)
		{
			if (this.isActorOnDevice(device, actorUuid))
			{
				return device;
			}
		}

		throw new IllegalArgumentException(
				"the given actor is not defined in configuration ("
						+ actorUuid.toString() + ")");
	}

	@Override
	public boolean callActor(Device device, UUID actorUuid)
	{
		final Actor actor = this.getActor(device, actorUuid);

		final ClientActorRequestTemplate requestTemplate = new ClientActorRequestTemplate(
				device, actor.getRequestInfo());

		return requestTemplate.executeRequest();
	}

	private Actor getActor(Device device, UUID actorUuid)
	{
		final List<Actor> actors = device.getActors();

		for (final Actor actor : actors)
		{
			if (actorUuid.equals(actor.getUuid()))
			{
				return actor;
			}
		}

		return null;
	}

	private boolean isActorOnDevice(Device device, UUID actorUuid)
	{
		return (null != this.getActor(device, actorUuid));
	}

}
