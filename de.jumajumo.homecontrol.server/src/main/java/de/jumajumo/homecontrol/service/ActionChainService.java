package de.jumajumo.homecontrol.service;

import java.util.List;
import java.util.UUID;

import de.jumajumo.homecontrol.configuration.server.ActionChain;
import de.jumajumo.homecontrol.type.ActionChainResult;

/**
 * The Interface ActionChainService defines the api to the action chain.
 */
public interface ActionChainService
{
	ActionChain findActionChainByUuid(final UUID actionChainUuid);

	List<ActionChain> findActionChainsByTrigger(final UUID triggerUuid);

	ActionChainResult executeActionChain(final ActionChain actionChain);
}
