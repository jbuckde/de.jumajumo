package de.jumajumo.homecontrol.configuration.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import de.jumajumo.homecontrol.configuration.AbstractConfigurationObject;
import de.jumajumo.homecontrol.configuration.server.trigger.TriggerRef;

/**
 * The Class ActionChain carries information on triggers and action references
 * to be performed in this chain.
 */

public class ActionChain extends AbstractConfigurationObject
{
	// the triggers that are able to start the action chain
	@XmlElementWrapper(name = "triggerRefs")
	@XmlElement(name = "triggerRef")
	private List<TriggerRef> triggerRefs;

	// the actions to perform in this action chain
	@XmlElementWrapper(name = "actionRefs")
	@XmlElement(name = "actionRef")
	private List<ActionRef> actionRefs;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((actionRefs == null) ? 0 : actionRefs.hashCode());
		result = prime * result
				+ ((triggerRefs == null) ? 0 : triggerRefs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionChain other = (ActionChain) obj;
		if (actionRefs == null)
		{
			if (other.actionRefs != null)
				return false;
		} else if (!actionRefs.equals(other.actionRefs))
			return false;
		if (triggerRefs == null)
		{
			if (other.triggerRefs != null)
				return false;
		} else if (!triggerRefs.equals(other.triggerRefs))
			return false;
		return true;
	}

	public List<TriggerRef> getTriggerRefs()
	{
		if (null == triggerRefs)
		{
			triggerRefs = new ArrayList<TriggerRef>();
		}

		return triggerRefs;
	}

	public void setTriggerRefs(List<TriggerRef> triggerRefs)
	{
		this.triggerRefs = triggerRefs;
	}

	public List<ActionRef> getActionRefs()
	{
		if (null == actionRefs)
		{
			actionRefs = new ArrayList<ActionRef>();
		}

		return actionRefs;
	}

	public void setActionRefs(List<ActionRef> actionRefs)
	{
		this.actionRefs = actionRefs;
	}
}
