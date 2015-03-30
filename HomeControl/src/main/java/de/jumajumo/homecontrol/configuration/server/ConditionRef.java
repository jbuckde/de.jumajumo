package de.jumajumo.homecontrol.configuration.server;

import java.util.UUID;

import de.jumajumo.homecontrol.configuration.AbstractConfigurationObject;

public class ConditionRef extends AbstractConfigurationObject
{
	private UUID conditionUuid;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((conditionUuid == null) ? 0 : conditionUuid.hashCode());
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
		ConditionRef other = (ConditionRef) obj;
		if (conditionUuid == null)
		{
			if (other.conditionUuid != null)
				return false;
		} else if (!conditionUuid.equals(other.conditionUuid))
			return false;
		return true;
	}

	public UUID getConditionUuid()
	{
		return conditionUuid;
	}

	public void setConditionUuid(UUID conditionUuid)
	{
		this.conditionUuid = conditionUuid;
	}
}
