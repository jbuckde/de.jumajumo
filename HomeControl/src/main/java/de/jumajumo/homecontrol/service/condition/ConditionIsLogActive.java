package de.jumajumo.homecontrol.service.condition;

import org.springframework.stereotype.Component;

@Component("conditionIsLogActive")
public class ConditionIsLogActive implements ConditionChecker
{

	@Override
	public boolean checkCondition()
	{
		return true;
	}

}
