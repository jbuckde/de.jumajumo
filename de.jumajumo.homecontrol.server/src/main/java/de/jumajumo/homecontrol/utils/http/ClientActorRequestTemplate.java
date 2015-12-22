package de.jumajumo.homecontrol.utils.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import de.jumajumo.homecontrol.configuration.client.Device;
import de.jumajumo.homecontrol.type.RequestInfo;

/**
 * The Class ClientActorRequestTemplate encapsulates the client actor request.
 */
public class ClientActorRequestTemplate
{
	private final static Log LOGGER = LogFactory
			.getLog(ClientActorRequestTemplate.class);

	private final Device device;
	private final RequestInfo requestInfo;

	public ClientActorRequestTemplate(final Device device,
			final RequestInfo requestInfo)
	{
		super();

		this.device = device;
		this.requestInfo = requestInfo;
	}

	public boolean executeRequest()
	{
		boolean isStatus200 = true;

		final URI uri = this.getUri();

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);

		try
		{
			if (null != this.requestInfo.getAuthorization()
					&& !"".equals(this.requestInfo.getAuthorization()))
			{
				httpGet.setHeader("Authorization",
						"Basic " + this.requestInfo.getAuthorization());
			}

			CloseableHttpResponse response;
			response = httpclient.execute(httpGet);

			try
			{
				if (!response.getStatusLine().toString()
						.contains("HTTP/1.1 200 OK"))
				{
					isStatus200 = false;

					LOGGER.error("exception status received: <"
							+ response.getStatusLine().toString() + ">");
				}

				HttpEntity entity1 = response.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity1);
			} finally
			{
				response.close();
			}
		} catch (HttpHostConnectException e)
		{
			LOGGER.error("request terminated with HttpHostConnectException: "
					+ e.getMessage() + " (" + uri.toString() + ")");

			return false;
		} catch (IOException e)
		{
			LOGGER.error("request terminated with IOException: "
					+ e.getMessage() + " (" + uri.toString() + ")");

			return false;
		}

		return true && isStatus200;
	}

	private URI getUri()
	{
		try
		{
			return new URI(this.device.getProtocol(),
					this.device.getHostName(), this.requestInfo.getPath(),
					this.requestInfo.getQuery(), "");
		} catch (URISyntaxException e)
		{
			throw new IllegalArgumentException("uri syntax is not valid", e);
		}
	}
}
