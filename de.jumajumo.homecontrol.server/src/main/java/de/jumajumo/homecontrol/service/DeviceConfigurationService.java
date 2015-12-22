package de.jumajumo.homecontrol.service;

import java.util.UUID;

import de.jumajumo.homecontrol.configuration.client.Device;

public interface DeviceConfigurationService
{
	Device findDeviceByUuid(final UUID uuid);

	Device findDeviceByActorUuid(final UUID actorUuid);

	boolean callActor(final Device device, final UUID actorUuid);
}
