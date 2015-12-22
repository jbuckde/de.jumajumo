package de.jumajumo.homecontrol.service;

import java.util.UUID;

import de.jumajumo.homecontrol.configuration.server.Action;

public interface ActionService
{
	Action findActionByUuid(final UUID actionUuid);

	boolean executeAction(final Action action);
}
