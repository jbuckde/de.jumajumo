package de.jumajumo.homecontrol.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;
import de.jumajumo.homecontrol.configuration.client.Device;
import de.jumajumo.homecontrol.configuration.server.Action;
import de.jumajumo.homecontrol.service.action.ActionExecutor;

@Service
public class ActionServiceImpl implements ActionService
{
	private final static Log LOGGER = LogFactory.getLog(ActionService.class);

	@Autowired
	private ApplicationContext appContext;

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
		// execute the defined bean
		final String actionBeanName = action.getBeanName();
		if (null != actionBeanName)
		{
			ActionExecutor actionBean = (ActionExecutor) appContext
					.getBean(actionBeanName);

			if (null != actionBean)
			{
				boolean executionResult = actionBean
						.executeAction(action.getProperties());

				LOGGER.debug("action <" + action.getName()
						+ "> executed with result <" + executionResult
						+ "> (bean: <" + actionBeanName + ">)");

				return executionResult;
			} else
			{
				LOGGER.debug("action <" + action.getName()
						+ "> executed with result <" + false + "> (bean: <"
						+ actionBeanName + "> could not be found.)");

				return false;
			}
		}

		// execute the specified device actors
		final List<UUID> actorUuids = action.getActorUuids();

		for (final UUID actorUuid : actorUuids)
		{
			final Device device = this.deviceService
					.findDeviceByActorUuid(actorUuid);

			if (!this.deviceService.callActor(device, actorUuid))
			{
				LOGGER.debug("action <" + action.getName()
						+ "> executed with result <false>");

				return false;
			}
		}

		LOGGER.debug("action <" + action.getName()
				+ "> executed with result <true>");

		return true;
	}
}
