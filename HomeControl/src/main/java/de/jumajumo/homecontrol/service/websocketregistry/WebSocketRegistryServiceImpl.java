package de.jumajumo.homecontrol.service.websocketregistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import de.jumajumo.homecontrol.configuration.client.Device;

@Service
public class WebSocketRegistryServiceImpl implements WebSocketRegistryService
{
	private final static Log LOGGER = LogFactory
			.getLog(WebSocketRegistryService.class);

	final Map<String, WebSocketInfo> registry = new HashMap<String, WebSocketInfo>();

	@Override
	public void registerSession(String registryId, WebSocketInfo webSocketInfo)
	{
		if (this.registry.containsKey(registryId))
		{
			throw new IllegalArgumentException(
					"device registry: item with id <" + registryId
							+ "> already exists.");
		}

		LOGGER.debug("register web socket session: " + registryId);

		this.registry.put(registryId, webSocketInfo);
	}

	@Override
	public void unregisterSession(String registryId)
	{
		if (this.registry.containsKey(registryId))
		{
			LOGGER.debug("unregister web socket session: " + registryId);

			this.registry.remove(registryId);
		}
	}

	@Override
	public Map<String, WebSocketInfo> getRegisteredSessions()
	{
		return this.registry;
	}

	@Override
	public WebSocketInfo getInfo(String registryId)
	{
		if (this.registry.containsKey(registryId))
		{
			return this.registry.get(registryId);
		}

		throw new IllegalArgumentException("session with id <" + registryId
				+ "> is not registered");
	}

	@Override
	public List<WebSocketSession> getSessionsForDevice(Device device)
	{
		final List<WebSocketSession> result = new ArrayList<WebSocketSession>();

		for (final WebSocketInfo info : this.registry.values())
		{
			if (device.equals(info.getDevice()))
			{
				result.add(info.getSession());
			}
		}

		return result;
	}

}
