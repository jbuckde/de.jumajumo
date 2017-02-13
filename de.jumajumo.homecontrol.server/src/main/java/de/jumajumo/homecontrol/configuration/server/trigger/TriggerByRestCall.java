package de.jumajumo.homecontrol.configuration.server.trigger;

import de.jumajumo.homecontrol.type.RequestInfo;

/**
 * The Class TriggerByRestCall contains the configuration of a trigger that is
 * processed by a rest call to the server.
 */
public class TriggerByRestCall extends Trigger
{
	private RequestInfo requestInfo;
	private int blockIntervall;

	public TriggerByRestCall()
	{
		super();
	}

	public TriggerByRestCall(final RequestInfo requestInfo)
	{
		super();

		assert ((null != requestInfo));
		this.setRequestInfo(requestInfo);
	}

	public RequestInfo getRequestInfo()
	{
		return requestInfo;
	}

	public void setRequestInfo(RequestInfo requestInfo)
	{
		this.requestInfo = requestInfo;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getRequestInfo() == null) ? 0
				: getRequestInfo().hashCode());
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
		TriggerByRestCall other = (TriggerByRestCall) obj;
		if (getRequestInfo() == null)
		{
			if (other.getRequestInfo() != null)
				return false;
		} else if (!getRequestInfo().equals(other.getRequestInfo()))
			return false;
		return true;
	}

	public int getBlockIntervall()
	{
		return blockIntervall;
	}

	public void setBlockIntervall(int blockIntervall)
	{
		this.blockIntervall = blockIntervall;
	}

}
