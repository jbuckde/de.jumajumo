package de.jumajumo.homecontrol.service.websocketregistry;

import de.jumajumo.homecontrol.configuration.client.Device;

public class WebSocketInfoSerializationWrapper
{
	private String sessionId;
	private Device device;

	public WebSocketInfoSerializationWrapper(final WebSocketInfo info)
	{
		this.sessionId = info.getSession().getId();
		this.device = info.getDevice();
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public Device getDevice()
	{
		return device;
	}
}
