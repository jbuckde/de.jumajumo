package de.jumajumo.homecontrol.service.condition;

import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.stereotype.Component;

import de.jumajumo.homecontrol.configuration.property.Property;

@Component("conditionScript")
public class ConditionScript implements ConditionChecker,
		ConditionPropertyAware
{
	// FIXME: beans are stateless !!!!
	private String script;

	@Override
	public boolean checkCondition()
	{
		// create a script engine manager
		ScriptEngineManager factory = new ScriptEngineManager();

		// create JavaScript engine
		ScriptEngine engine = factory.getEngineByName("JavaScript");

		try
		{
			engine.eval(script);
		} catch (ScriptException e)
		{
			return false;
		}

		Invocable inv = (Invocable) engine;
		ConditionChecker checker = inv.getInterface(ConditionChecker.class);

		return checker.checkCondition();
	}

	@Override
	public void setProperties(List<Property> properties)
	{
		// get the script-property value
		for (final Property property : properties)
		{
			if (property.getKey().equals("script"))
			{
				this.setScript(property.getValue());
			}
		}
	}

	public String getScript()
	{
		return script;
	}

	public void setScript(String script)
	{
		this.script = script;
	}

}
