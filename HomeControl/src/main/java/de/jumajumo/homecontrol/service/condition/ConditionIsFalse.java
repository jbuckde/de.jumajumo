package de.jumajumo.homecontrol.service.condition;

import org.springframework.stereotype.Component;

@Component("conditionIsFalse")
public class ConditionIsFalse implements ConditionChecker
{

	@Override
	public boolean checkCondition()
	{
		return false;
	}

}
