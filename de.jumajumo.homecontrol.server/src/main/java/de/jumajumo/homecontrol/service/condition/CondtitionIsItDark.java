package de.jumajumo.homecontrol.service.condition;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jumajumo.homecontrol.service.sunset.SunsetService;

@Component("conditionIsItDark")
public class CondtitionIsItDark implements ConditionChecker
{
	@Autowired
	private SunsetService sunsetService;

	@Override
	public boolean checkCondition()
	{
		return this.getSunsetService().isItDarkAt(new Date());
	}

	public SunsetService getSunsetService()
	{
		return sunsetService;
	}

	public void setSunsetService(SunsetService sunsetService)
	{
		this.sunsetService = sunsetService;
	}

}
