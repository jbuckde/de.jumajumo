package de.jumajumo.homecontrol.configuration.server.trigger;

public class TriggerByFirebaseListener extends Trigger
{
	private String action;

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((action == null) ? 0 : action.hashCode());
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
		TriggerByFirebaseListener other = (TriggerByFirebaseListener) obj;
		if (action == null)
		{
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		return true;
	}
}
