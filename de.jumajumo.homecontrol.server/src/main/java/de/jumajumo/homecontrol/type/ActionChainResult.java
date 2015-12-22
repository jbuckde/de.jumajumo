package de.jumajumo.homecontrol.type;

import java.util.UUID;

import de.jumajumo.homecontrol.configuration.server.ActionChain;

public class ActionChainResult
{
	private final UUID actionChainUuid;
	private final String actionChainName;
	private boolean success = true;

	public ActionChainResult(final ActionChain actionChain)
	{
		this.actionChainUuid = actionChain.getUuid();
		this.actionChainName = actionChain.getName();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actionChainName == null) ? 0 : actionChainName.hashCode());
		result = prime * result
				+ ((actionChainUuid == null) ? 0 : actionChainUuid.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionChainResult other = (ActionChainResult) obj;
		if (actionChainName == null)
		{
			if (other.actionChainName != null)
				return false;
		} else if (!actionChainName.equals(other.actionChainName))
			return false;
		if (actionChainUuid == null)
		{
			if (other.actionChainUuid != null)
				return false;
		} else if (!actionChainUuid.equals(other.actionChainUuid))
			return false;
		if (success != other.success)
			return false;
		return true;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public UUID getActionChainUuid()
	{
		return actionChainUuid;
	}

	public String getActionChainName()
	{
		return actionChainName;
	}
}
