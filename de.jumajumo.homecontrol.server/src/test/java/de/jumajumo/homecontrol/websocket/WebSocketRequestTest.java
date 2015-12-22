package de.jumajumo.homecontrol.websocket;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class WebSocketRequestTest
{
	@Test
	public void testSerialization() throws IOException
	{
		final WebSocketRequest request = new WebSocketRequest();
		request.setCommand(EWebSocketCommand.DEFINE_DEVICE);
		request.getParameters().put("deviceId", "4711");

		final ObjectMapper serializationMapper = new ObjectMapper();
		final ObjectWriter w = serializationMapper.writer();
		String json = w.with(SerializationFeature.INDENT_OUTPUT)
				.writeValueAsString(request);

		final ObjectMapper deserializationMapper = new ObjectMapper();
		final WebSocketRequest targetRequest = deserializationMapper.readValue(
				json, WebSocketRequest.class);

		Assert.assertEquals(request, targetRequest);

		System.out.println(json);
	}
}
