package org.socialsigning.provider.facebook;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

@Component
public class FacebookConnectInterceptor implements ConnectInterceptor<Facebook>
{

	@Override
	public void postConnect(Connection<Facebook> connection,
			WebRequest webRequest)
	{
	}

	@Override
	public void preConnect(ConnectionFactory<Facebook> connectionFactory,
			MultiValueMap<String, String> parameters, WebRequest request)
	{
	}
}
