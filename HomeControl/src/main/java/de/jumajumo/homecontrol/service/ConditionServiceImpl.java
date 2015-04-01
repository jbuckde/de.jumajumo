package de.jumajumo.homecontrol.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;
import de.jumajumo.homecontrol.configuration.server.Condition;
import de.jumajumo.homecontrol.service.condition.ConditionChecker;
import de.jumajumo.homecontrol.service.condition.ConditionPropertyAware;

@Service
public class ConditionServiceImpl implements ConditionService
{
	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Override
	public Condition findConditionByUuid(UUID conditionUuid)
	{
		final List<Condition> conditions = this.configurationContextHolder
				.getConfiguration().getConditions();

		assert (null != conditions);

		for (final Condition condition : conditions)
		{
			if (conditionUuid.equals(condition.getUuid()))
			{
				return condition;
			}
		}

		throw new IllegalArgumentException(
				"the configuration contains no condition with the uuid: "
						+ conditionUuid.toString());
	}

	@Override
	public boolean isConditionSatisfied(Condition condition)
	{
		String conditionBeanName = condition.getBeanName();
		ConditionChecker conditionBean = (ConditionChecker) appContext
				.getBean(conditionBeanName);

		if (conditionBean instanceof ConditionPropertyAware)
		{
			((ConditionPropertyAware) conditionBean).setProperties(condition
					.getProperties());
		}

		if (null != conditionBean)
		{

			return conditionBean.checkCondition();
		} else
		{
			return false;
		}
	}
}
