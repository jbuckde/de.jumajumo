package de.jumajumo.core.service.weather;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.jumajumo.core.type.CurrentWeather;

/**
 * The Class WeatherServiceOpenWeatherMap.
 */
@Service
public class WeatherServiceOpenWeatherMap implements WeatherService
{

	private Map<String, CurrentWeather> weatherCache = new HashMap<String, CurrentWeather>();

	@Override
	public CurrentWeather loadCurrentWeather()
	{
		CurrentWeather currentWeather = this.loadFromCache();

		if (null == currentWeather)
		{
			currentWeather = this.loadFromService("Bad Schussenried");

			if (null != currentWeather.getSunset())
			{
				this.weatherCache.put(
						this.getDateFromSunset(currentWeather.getSunset()),
						currentWeather);
			}
		}

		return currentWeather;
	}

	protected CurrentWeather loadFromCache()
	{
		final String today = this.getToday();

		if (this.weatherCache.containsKey(today))
		{
			return this.weatherCache.get(today);
		}

		return null;
	}

	private String getDateFromSunset(Date sunset)
	{
		final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		return format.format(sunset);
	}

	private String getToday()
	{
		final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		return format.format(new Date());
	}

	protected CurrentWeather loadFromService(final String location)
			throws RestClientException
	{
		final List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new OpenWeatherMapMessageConverter());

		final RestTemplate restTemplate = new RestTemplate(converters);

		CurrentWeather forObject = null;
		try
		{
			// FIXME: make apikey configurable
			String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
					+ URLEncoder.encode(location, "UTF-8")
					+ "&appid=5ab77ad296ebeec4fda0a335ab44ed88";
			forObject = restTemplate.getForObject(urlString,
					CurrentWeather.class);
		} catch (UnsupportedEncodingException e)
		{
			throw new IllegalArgumentException("invalid url string", e);
		}

		return forObject;
	};

}
