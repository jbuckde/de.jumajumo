package de.jumajumo.core.service.weather;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestClientException;

import de.jumajumo.core.type.CurrentWeather;

public class WeatherServiceOpenWeatherMapTest
{
	private WeatherServiceOpenWeatherMap testee;

	@Before
	public void setup()
	{
		testee = new WeatherServiceOpenWeatherMap();
	}

	@Test
	public void testHttpRequest() throws RestClientException
	{
		testee.loadFromService("Bad Schussenried");
	}

	@Test
	public void testLoadFromCache() throws Exception
	{
		// force to put it to cache
		testee.loadCurrentWeather();

		final CurrentWeather loadFromCache = testee.loadFromCache();
		Assert.assertNotNull(loadFromCache);
	}
}
