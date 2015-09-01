package de.jumajumo.core.service.twitter;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterServiceImpl implements TwitterService
{
	private String consumerKey;
	private String consumerSecret;

	private String accessToken;
	private String accessTokenSecret;

	final Twitter twitter = TwitterFactory.getSingleton();

	@Override
	public Status sendUpdate(StatusUpdate statusUpdate)
	{
		this.authenticate();

		try
		{
			return twitter.updateStatus(statusUpdate);
		} catch (TwitterException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	private void authenticate()
	{
		twitter.setOAuthConsumer(this.getConsumerKey(),
				this.getConsumerSecret());

		twitter.setOAuthAccessToken(new AccessToken(this.getAccessToken(), this
				.getAccessTokenSecret()));
	}

	public String getConsumerKey()
	{
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey)
	{
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret()
	{
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret)
	{
		this.consumerSecret = consumerSecret;
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret()
	{
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret)
	{
		this.accessTokenSecret = accessTokenSecret;
	}

}
