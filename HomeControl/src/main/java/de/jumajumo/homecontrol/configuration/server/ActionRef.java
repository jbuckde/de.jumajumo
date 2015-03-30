package de.jumajumo.homecontrol.configuration.server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import de.jumajumo.homecontrol.configuration.AbstractConfigurationObject;

public class ActionRef extends AbstractConfigurationObject
{
	// the reference to the action
	private UUID actionUuid;

	// the conditions that have to be fulfilled in order to perform the
	// references action in the context of the referenced action chain
	@XmlElementWrapper(name = "conditionRefs")
	@XmlElement(name = "conditionRef")
	private List<ConditionRef> conditionRefs;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((actionUuid == null) ? 0 : actionUuid.hashCode());
		result = prime * result
				+ ((conditionRefs == null) ? 0 : conditionRefs.hashCode());
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
		ActionRef other = (ActionRef) obj;
		if (actionUuid == null)
		{
			if (other.actionUuid != null)
				return false;
		} else if (!actionUuid.equals(other.actionUuid))
			return false;
		if (conditionRefs == null)
		{
			if (other.conditionRefs != null)
				return false;
		} else if (!conditionRefs.equals(other.conditionRefs))
			return false;
		return true;
	}

	public List<ConditionRef> getConditionRefs()
	{
		if (null == conditionRefs)
		{
			conditionRefs = new ArrayList<ConditionRef>();
		}

		return conditionRefs;
	}

	public void setConditionRefs(List<ConditionRef> conditionRefs)
	{
		this.conditionRefs = conditionRefs;
	}

	public UUID getActionUuid()
	{
		return actionUuid;
	}

	public void setActionUuid(UUID actionUuid)
	{
		this.actionUuid = actionUuid;
	}
}
