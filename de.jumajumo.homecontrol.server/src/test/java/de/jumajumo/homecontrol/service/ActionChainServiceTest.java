package de.jumajumo.homecontrol.service;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.jumajumo.homecontrol.configuration.server.Action;
import de.jumajumo.homecontrol.configuration.server.ActionChain;
import de.jumajumo.homecontrol.configuration.server.ActionRef;
import de.jumajumo.homecontrol.configuration.server.Condition;
import de.jumajumo.homecontrol.configuration.server.ConditionRef;

@RunWith(MockitoJUnitRunner.class)
public class ActionChainServiceTest
{
	@InjectMocks
	private ActionChainServiceImpl testee;

	@Mock
	private ConditionService conditionService;

	@Mock
	private ActionService actionService;

	private ActionChain actionChain;

	@Before
	public void setup()
	{
		this.actionChain = new ActionChain();

		final ActionRef actionRef1 = new ActionRef();

		final ConditionRef conditionRef1 = new ConditionRef();
		conditionRef1.setName("conditionRef1");
		actionRef1.getConditionRefs().add(conditionRef1);
		final Condition condition1 = new Condition();
		condition1.setUuid(UUID.randomUUID());
		condition1.setName("condition1");
		conditionRef1.setConditionUuid(condition1.getUuid());
		Mockito.when(
				this.conditionService.findConditionByUuid(condition1.getUuid()))
				.thenReturn(condition1);
		Mockito.when(this.conditionService.isConditionSatisfied(condition1))
				.thenReturn(false);

		final Action action1 = new Action();
		action1.setUuid(UUID.randomUUID());
		action1.setName("action1");
		actionRef1.setActionUuid(action1.getUuid());
		Mockito.when(this.actionService.findActionByUuid(action1.getUuid()))
				.thenReturn(action1);
		Mockito.when(this.actionService.executeAction(action1))
				.thenReturn(true);

		this.actionChain.getActionRefs().add(actionRef1);

		final ActionRef actionRef2 = new ActionRef();

		final ConditionRef conditionRef2 = new ConditionRef();
		conditionRef2.setName("conditionRef2");
		actionRef2.getConditionRefs().add(conditionRef2);
		final Condition condition2 = new Condition();
		condition2.setUuid(UUID.randomUUID());
		condition2.setName("condition2");
		conditionRef2.setConditionUuid(condition2.getUuid());
		Mockito.when(
				this.conditionService.findConditionByUuid(condition2.getUuid()))
				.thenReturn(condition2);
		Mockito.when(this.conditionService.isConditionSatisfied(condition2))
				.thenReturn(true);

		final Action action2 = new Action();
		action2.setUuid(UUID.randomUUID());
		action2.setName("action1");
		actionRef2.setActionUuid(action2.getUuid());
		Mockito.when(this.actionService.findActionByUuid(action2.getUuid()))
				.thenReturn(action2);
		Mockito.when(this.actionService.executeAction(action2))
				.thenReturn(true);

		this.actionChain.getActionRefs().add(actionRef2);
	}

	@Test
	public void testActionChainWithMultipleActions()
	{
		testee.executeActionChain(this.actionChain);
	}
}
