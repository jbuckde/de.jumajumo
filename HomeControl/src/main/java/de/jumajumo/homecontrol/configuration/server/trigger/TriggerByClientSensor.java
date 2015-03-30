package de.jumajumo.homecontrol.configuration.server.trigger;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TriggerByClientSensor extends Trigger
{
	private List<UUID> sensorUuids;

	public TriggerByClientSensor()
	{
		super();
	}

	public TriggerByClientSensor(final UUID... sensorUuids)
	{
		super();

		this.setSensorUuids(Arrays.asList(sensorUuids));
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((getSensorUuids() == null) ? 0 : getSensorUuids().hashCode());
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
		TriggerByClientSensor other = (TriggerByClientSensor) obj;
		if (getSensorUuids() == null)
		{
			if (other.getSensorUuids() != null)
				return false;
		} else if (!getSensorUuids().equals(other.getSensorUuids()))
			return false;
		return true;
	}

	public List<UUID> getSensorUuids()
	{
		return sensorUuids;
	}

	public void setSensorUuids(List<UUID> sensorUuids)
	{
		this.sensorUuids = sensorUuids;
	}
}
