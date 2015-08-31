package de.jumajumo.homecontrol.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;
import de.jumajumo.homecontrol.configuration.client.Device;
import de.jumajumo.homecontrol.configuration.server.Action;

@Service
public class ActionServiceImpl implements ActionService
{
	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Autowired
	private DeviceConfigurationService deviceService;

	@Override
	public Action findActionByUuid(UUID actionUuid)
	{
		// get the list of action chains from configuration
		final List<Action> actions = this.configurationContextHolder
				.getConfiguration().getActions();

		assert (null != actions);

		for (final Action action : actions)
		{
			if (actionUuid.equals(action.getUuid()))
			{
				return action;
			}
		}

		throw new IllegalArgumentException(
				"the configuration contains no action with the uuid: "
						+ actionUuid.toString());
	}

	@Override
	public boolean executeAction(Action action)
	{
		final List<UUID> actorUuids = action.getActorUuids();

		for (final UUID actorUuid : actorUuids)
		{
			final Device device = this.deviceService
					.findDeviceByActorUuid(actorUuid);

			if (!this.deviceService.callActor(device, actorUuid))
			{
				return false;
			}
		}

		return true;
	}
}
