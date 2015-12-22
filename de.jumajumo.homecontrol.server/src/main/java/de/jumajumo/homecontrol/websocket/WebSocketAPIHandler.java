package de.jumajumo.homecontrol.websocket;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.jumajumo.homecontrol.configuration.client.Device;
import de.jumajumo.homecontrol.configuration.server.trigger.Trigger;
import de.jumajumo.homecontrol.service.DeviceConfigurationService;
import de.jumajumo.homecontrol.service.TriggerService;
import de.jumajumo.homecontrol.service.websocketregistry.WebSocketInfo;
import de.jumajumo.homecontrol.service.websocketregistry.WebSocketRegistryService;

public class WebSocketAPIHandler extends TextWebSocketHandler
{
	private final static Log LOGGER = LogFactory
			.getLog(WebSocketAPIHandler.class);

	@Autowired
	private WebSocketRegistryService webSocketRegistryService;

	@Autowired
	private DeviceConfigurationService deviceConfigurationService;

	@Autowired
	private TriggerService triggerService;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws IOException
	{
		try
		{
			session.sendMessage(message);

			final ObjectMapper deserializationMapper = new ObjectMapper();
			final WebSocketRequest targetRequest = deserializationMapper
					.readValue(message.getPayload(), WebSocketRequest.class);

			this.executeRequest(session, targetRequest);
		} catch (RuntimeException e)
		{
			LOGGER.error("websocket message handler terminated with exception ("
					+ e.getMessage() + ")");
		}
	}

	private void executeRequest(final WebSocketSession session,
			final WebSocketRequest targetRequest)
	{
		if (EWebSocketCommand.DEFINE_DEVICE.equals(targetRequest.getCommand()))
		{
			final WebSocketInfo info = this.webSocketRegistryService
					.getInfo(session.getId());

			final Device device = this.deviceConfigurationService
					.findDeviceByUuid(UUID.fromString(targetRequest
							.getParameters().get("deviceId")));

			info.setDevice(device);
		}

		if (EWebSocketCommand.SENSOR_TRIGGER.equals(targetRequest.getCommand()))
		{
			final UUID sensorUuid = UUID.fromString(targetRequest
					.getParameters().get("sensorId"));

			final Trigger trigger = this.triggerService
					.findTriggerBySensor(sensorUuid);

			if (null != trigger)
			{
				this.triggerService.executeTrigger(trigger);
			}
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception
	{
		this.webSocketRegistryService.registerSession(session.getId(),
				new WebSocketInfo(session));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception
	{
		this.webSocketRegistryService.unregisterSession(session.getId());
	}
}