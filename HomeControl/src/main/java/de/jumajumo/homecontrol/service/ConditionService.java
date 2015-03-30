package de.jumajumo.homecontrol.service;

import java.util.UUID;

import de.jumajumo.homecontrol.configuration.server.Condition;

public interface ConditionService
{
	Condition findConditionByUuid(final UUID conditionUuid);

	boolean isConditionSatisfied(final Condition condition);
}
