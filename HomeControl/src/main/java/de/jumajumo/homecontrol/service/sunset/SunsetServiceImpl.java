package de.jumajumo.homecontrol.service.sunset;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.core.service.weather.WeatherService;
import de.jumajumo.core.type.CurrentWeather;

@Service
public class SunsetServiceImpl implements SunsetService
{
	private final static Log LOGGER = LogFactory.getLog(SunsetService.class);

	@Autowired
	private WeatherService weatherService;

	@Override
	public boolean isItDarkAt(final Date timeToCheck)
	{
		final CurrentWeather currentWeather = getWeatherService()
				.loadCurrentWeather();

		if (null != currentWeather.getSunset()
				&& null != currentWeather.getSunrise())
		{
			final boolean afterSunset = currentWeather.getSunset().compareTo(
					timeToCheck) < 0;
			final boolean beforeSunrise = currentWeather.getSunrise()
					.compareTo(timeToCheck) > 0;

			return afterSunset || beforeSunrise;
		} else
		{
			LOGGER.warn("something went wrong with sunset determination.");
			return false;
		}
	}

	public WeatherService getWeatherService()
	{
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService)
	{
		this.weatherService = weatherService;
	}

}
