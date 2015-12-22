package de.jumajumo.core.service.twitter;

import org.junit.Ignore;
import org.junit.Test;

import twitter4j.Status;
import twitter4j.StatusUpdate;

public class TwitterServiceTest
{
	// Consumer Key (API Key) gqgY0HOfuZkjSKOsKJSI3qXbJ
	// Consumer Secret (API Secret)
	// TE3u7lFfnurgRMdVCvbthZ9Zcnzmwi9c6Q5ah7V08ceQFeeTgJ

	@Test
	@Ignore
	public void testUpdate() throws Exception
	{
		final TwitterServiceImpl twitterService = new TwitterServiceImpl();

		twitterService.setConsumerKey("gqgY0HOfuZkjSKOsKJSI3qXbJ");
		twitterService
				.setConsumerSecret("TE3u7lFfnurgRMdVCvbthZ9Zcnzmwi9c6Q5ah7V08ceQFeeTgJ");

		twitterService
				.setAccessToken("51803797-PfBAjdV3rYttu9Dz6EKz4i9FM0ThJIajzi07TGg2E");
		twitterService
				.setAccessTokenSecret("6V4e0UJpdmz0DyB9JTvJpQE7EhxTACjhnoBBeGRfEPChy");

		StatusUpdate update = new StatusUpdate("this is test status");
		update.setMedia("image",
				this.getClass().getResourceAsStream("Desert.jpg"));

		Status status = twitterService.sendUpdate(update);

		System.out.println(status.getText());
	}
}
