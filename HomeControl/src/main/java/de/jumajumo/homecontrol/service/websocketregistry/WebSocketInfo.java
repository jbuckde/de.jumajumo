package de.jumajumo.homecontrol.service.websocketregistry;

import org.springframework.web.socket.WebSocketSession;

import de.jumajumo.homecontrol.configuration.client.Device;

public class WebSocketInfo
{
	private WebSocketSession session;
	private Device device;

	public WebSocketInfo(final WebSocketSession session)
	{
		this.session = session;
	}

	public WebSocketSession getSession()
	{
		return session;
	}

	public void setSession(WebSocketSession session)
	{
		this.session = session;
	}

	public Device getDevice()
	{
		return device;
	}

	public void setDevice(Device device)
	{
		this.device = device;
	}
}
