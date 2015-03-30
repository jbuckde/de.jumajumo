package de.jumajumo.homecontrol.configuration.client;

import de.jumajumo.homecontrol.configuration.AbstractConfigurationObject;
import de.jumajumo.homecontrol.type.RequestInfo;

/**
 * The Class Actor represents an actor on a client device.
 * 
 * Carries information on how to access the actor from server side
 */
public class Actor extends AbstractConfigurationObject
{
	// information on how to request the actor
	private RequestInfo requestInfo;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((requestInfo == null) ? 0 : requestInfo.hashCode());
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
		Actor other = (Actor) obj;
		if (requestInfo == null)
		{
			if (other.requestInfo != null)
				return false;
		} else if (!requestInfo.equals(other.requestInfo))
			return false;
		return true;
	}

	public RequestInfo getRequestInfo()
	{
		return requestInfo;
	}

	public void setRequestInfo(RequestInfo requestInfo)
	{
		this.requestInfo = requestInfo;
	}
}
