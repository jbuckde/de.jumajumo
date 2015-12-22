package de.jumajumo.homecontrol.service.websocketregistry;

import java.util.List;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

import de.jumajumo.homecontrol.configuration.client.Device;

public interface WebSocketRegistryService
{
	void registerSession(String registryId, WebSocketInfo webSocketInfo);

	void unregisterSession(String registryId);

	Map<String, WebSocketInfo> getRegisteredSessions();

	WebSocketInfo getInfo(final String registryId);

	List<WebSocketSession> getSessionsForDevice(Device device);
}
