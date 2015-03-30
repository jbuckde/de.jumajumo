package de.jumajumo.homecontrol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;
import de.jumajumo.homecontrol.configuration.server.Action;
import de.jumajumo.homecontrol.configuration.server.ActionChain;
import de.jumajumo.homecontrol.configuration.server.ActionRef;
import de.jumajumo.homecontrol.configuration.server.Condition;
import de.jumajumo.homecontrol.configuration.server.ConditionRef;
import de.jumajumo.homecontrol.configuration.server.trigger.TriggerRef;
import de.jumajumo.homecontrol.type.ActionChainResult;

@Service
public class ActionChainServiceImpl implements ActionChainService
{
	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Autowired
	private ActionService actionService;

	@Autowired
	private ConditionService conditionService;

	@Override
	public ActionChain findActionChainByUuid(UUID actionChainUuid)
	{
		// get the list of action chains from configuration
		final List<ActionChain> actionChains = this.configurationContextHolder
				.getConfiguration().getActionChains();

		assert (null != actionChains);

		for (final ActionChain actionChain : actionChains)
		{
			if (actionChainUuid.equals(actionChain.getUuid()))
			{
				return actionChain;
			}
		}

		throw new IllegalArgumentException(
				"the configuration contains no action chain with the uuid: "
						+ actionChainUuid.toString());
	}

	@Override
	public List<ActionChain> findActionChainsByTrigger(UUID triggerUuid)
	{
		final List<ActionChain> result = new ArrayList<ActionChain>();

		final List<ActionChain> actionChains = this.configurationContextHolder
				.getConfiguration().getActionChains();
		for (final ActionChain actionChain : actionChains)
		{
			if (this.actionChainIsTriggeredBy(actionChain, triggerUuid))
			{
				result.add(actionChain);
			}
		}

		return result;
	}

	@Override
	public ActionChainResult executeActionChain(ActionChain actionChain)
	{
		final ActionChainResult result = new ActionChainResult(actionChain);

		final List<ActionRef> actionRefs = actionChain.getActionRefs();

		for (final ActionRef actionRef : actionRefs)
		{
			if (this.checkConditions(actionRef))
			{
				result.setSuccess(result.isSuccess()
						&& this.executeActionByRef(actionRef));
			}
		}

		return result;
	}

	private boolean actionChainIsTriggeredBy(final ActionChain actionChain,
			final UUID triggerUuid)
	{
		final List<TriggerRef> triggerRefs = actionChain.getTriggerRefs();

		for (final TriggerRef triggerRef : triggerRefs)
		{
			if (triggerUuid.equals(triggerRef.getTriggerUuid()))
			{
				return true;
			}
		}

		return false;
	}

	private boolean checkConditions(ActionRef actionRef)
	{
		final List<ConditionRef> conditionRefs = actionRef.getConditionRefs();

		if (null == conditionRefs)
		{
			return true;
		}

		boolean retVal = true;
		for (final ConditionRef conditionRef : conditionRefs)
		{
			final UUID conditionUuid = conditionRef.getConditionUuid();
			final Condition condition = this.conditionService
					.findConditionByUuid(conditionUuid);

			retVal = retVal
					&& this.conditionService.isConditionSatisfied(condition);
		}

		return retVal;
	}

	private boolean executeActionByRef(ActionRef actionRef)
	{
		final UUID actionUuid = actionRef.getActionUuid();
		final Action action = this.actionService.findActionByUuid(actionUuid);

		return this.actionService.executeAction(action);
	}

}
