package de.jumajumo.core.service.weather;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
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

	private Map<Date, CurrentWeather> weatherCache = new HashMap<Date, CurrentWeather>();

	@Override
	public CurrentWeather loadCurrentWeather()
	{
		CurrentWeather currentWeather = this.loadFromCache();

		if (null == currentWeather)
		{
			currentWeather = this.loadFromService("Bad Schussenried");

			this.weatherCache.put(this.getToday(), currentWeather);
		}

		return currentWeather;
	}

	protected CurrentWeather loadFromCache()
	{
		final Date today = getToday();

		if (this.weatherCache.containsKey(today))
		{
			return this.weatherCache.get(today);
		}

		return null;
	}

	private Date getToday()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE));
		final Date today = cal.getTime();

		return today;
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
			String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
					+ URLEncoder.encode(location, "UTF-8");
			forObject = restTemplate.getForObject(urlString,
					CurrentWeather.class);
		} catch (UnsupportedEncodingException e)
		{
			throw new IllegalArgumentException("invalid url string", e);
		}

		return forObject;
	};

}
