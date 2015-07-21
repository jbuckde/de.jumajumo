package de.jumajumo.homecontrol.websocket;

import java.io.IOException;

import org.springframework.web.socket.CloseStatus;
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

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception
	{
		System.out.println("session connected.");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception
	{
		System.out.println("session disconnected.");
	}
}