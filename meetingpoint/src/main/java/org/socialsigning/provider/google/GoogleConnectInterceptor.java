package org.socialsigning.provider.google;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

@Component
public class GoogleConnectInterceptor implements ConnectInterceptor<Google>
{

	@Override
	public void postConnect(Connection<Google> connection, WebRequest webRequest)
	{
	}

	@Override
	public void preConnect(ConnectionFactory<Google> arg0,
			MultiValueMap<String, String> arg1, WebRequest arg2)
	{
	}
}
