package de.jumajumo.homecontrol.configuration.server.trigger;

import java.util.UUID;

import de.jumajumo.homecontrol.configuration.AbstractConfigurationObject;

public class TriggerRef extends AbstractConfigurationObject
{
	// the reference to the trigger
	private UUID triggerUuid;

	public TriggerRef()
	{
		super();

		this.setUuid(UUID.randomUUID());
	}

	public UUID getTriggerUuid()
	{
		return triggerUuid;
	}

	public void setTriggerUuid(UUID triggerUuid)
	{
		this.triggerUuid = triggerUuid;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((triggerUuid == null) ? 0 : triggerUuid.hashCode());
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
		TriggerRef other = (TriggerRef) obj;
		if (triggerUuid == null)
		{
			if (other.triggerUuid != null)
				return false;
		} else if (!triggerUuid.equals(other.triggerUuid))
			return false;
		return true;
	}
}
