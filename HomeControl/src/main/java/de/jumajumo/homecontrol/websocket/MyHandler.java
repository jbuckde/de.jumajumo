package de.jumajumo.homecontrol.websocket;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler
{

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws IOException
	{
		System.out.println(message.getPayload());

		session.sendMessage(message);
	}

}