package de.jumajumo.homecontrol.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.jumajumo.homecontrol.configuration.ConfigurationContext;
import de.jumajumo.homecontrol.configuration.ConfigurationContextHolder;
import de.jumajumo.homecontrol.configuration.client.Actor;
import de.jumajumo.homecontrol.configuration.client.Device;
import de.jumajumo.homecontrol.service.websocketregistry.WebSocketRegistryService;
import de.jumajumo.homecontrol.type.RequestInfo;
import de.jumajumo.homecontrol.utils.http.ClientActorRequestTemplate;
import de.jumajumo.homecontrol.websocket.EWebSocketCommand;
import de.jumajumo.homecontrol.websocket.WebSocketRequest;

@Service
public class DeviceConfigurationServiceImpl implements
		DeviceConfigurationService
{
	private final static Log LOGGER = LogFactory
			.getLog(DeviceConfigurationService.class);

	@Autowired
	private ConfigurationContextHolder configurationContextHolder;

	@Autowired
	private WebSocketRegistryService webSocketRegistryService;

	@Override
	public Device findDeviceByUuid(UUID uuid)
	{
		final ConfigurationContext configuration = configurationContextHolder
				.getConfiguration();

		final List<Device> devices = configuration.getDevices();

		for (Device device : devices)
		{
			if (uuid.equals(device.getUuid()))
			{
				return device;
			}
		}

		throw new IllegalArgumentException(
				"the given device is not defined in configuration ("
						+ uuid.toString() + ")");
	}

	@Override
	public Device findDeviceByActorUuid(UUID actorUuid)
	{
		final ConfigurationContext configuration = configurationContextHolder
				.getConfiguration();

		final List<Device> devices = configuration.getDevices();

		for (Device device : devices)
		{
			if (this.isActorOnDevice(device, actorUuid))
			{
				return device;
			}
		}

		throw new IllegalArgumentException(
				"the given actor is not defined in configuration ("
						+ actorUuid.toString() + ")");
	}

	@Override
	public boolean callActor(Device device, UUID actorUuid)
	{
		final Actor actor = this.getActor(device, actorUuid);

		if (RequestInfo.EnumRequestType.REQUEST_TYPE_HTTP.equals(actor
				.getRequestInfo().getRequestType()))
		{
			final ClientActorRequestTemplate requestTemplate = new ClientActorRequestTemplate(
					device, actor.getRequestInfo());

			final boolean requestResult = requestTemplate.executeRequest();

			LOGGER.debug("actor <" + actor.getName()
					+ "> executed with result: <" + requestResult + ">");

			return requestResult;
		}

		else if (RequestInfo.EnumRequestType.REQUEST_TYPE_WEBSOCKET
				.equals(actor.getRequestInfo().getRequestType()))
		{

			final List<WebSocketSession> sessionsForDevice = this.webSocketRegistryService
					.getSessionsForDevice(device);

			final WebSocketRequest request = new WebSocketRequest();
			request.setCommand(EWebSocketCommand.EXECUTE_ACTOR);
			request.getParameters().put("actorId", actorUuid.toString());
			request.getParameters().put("name", actor.getName());

			try
			{
				final TextMessage webSocketMessage = this
						.getWebSocketMessage(request);

				for (final WebSocketSession session : sessionsForDevice)
				{
					try
					{
						session.sendMessage(webSocketMessage);
					} catch (IOException e)
					{
						LOGGER.debug("actor <" + actor.getName()
								+ "> executed with result: <false>");

						return false;
					}
				}
			} catch (JsonProcessingException e1)
			{
				LOGGER.debug("actor <" + actor.getName()
						+ "> executed with result: <false>");

				return false;
			}

			LOGGER.debug("actor <" + actor.getName()
					+ "> executed with result: <true>");

			return true;
		}

		throw new IllegalAccessError("the actor <" + actor.getName()
				+ "> is not valid for calling.");
	}

	private TextMessage getWebSocketMessage(WebSocketRequest request)
			throws JsonProcessingException
	{
		final ObjectMapper serializationMapper = new ObjectMapper();
		final ObjectWriter w = serializationMapper.writer();
		String json = w.with(SerializationFeature.INDENT_OUTPUT)
				.writeValueAsString(request);

		return new TextMessage(json.getBytes());
	}

	private Actor getActor(Device device, UUID actorUuid)
	{
		final List<Actor> actors = device.getActors();

		for (final Actor actor : actors)
		{
			if (actorUuid.equals(actor.getUuid()))
			{
				return actor;
			}
		}

		return null;
	}

	private boolean isActorOnDevice(Device device, UUID actorUuid)
	{
		return (null != this.getActor(device, actorUuid));
	}

}
