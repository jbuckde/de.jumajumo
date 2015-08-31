package de.jumajumo.homecontrol.websocket;

import java.util.HashMap;
import java.util.Map;

public class WebSocketRequest
{

	private EWebSocketCommand command;
	private Map<String, String> parameters;

	public EWebSocketCommand getCommand()
	{
		return command;
	}

	public void setCommand(EWebSocketCommand command)
	{
		this.command = command;
	}

	public Map<String, String> getParameters()
	{
		if (null == this.parameters)
		{
			this.parameters = new HashMap<String, String>();
		}

		return parameters;
	}

	public void setParameters(Map<String, String> parameters)
	{
		this.parameters = parameters;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
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
		WebSocketRequest other = (WebSocketRequest) obj;
		if (command != other.command)
			return false;
		if (parameters == null)
		{
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}
}
