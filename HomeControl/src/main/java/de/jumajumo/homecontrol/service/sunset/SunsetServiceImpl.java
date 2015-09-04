package de.jumajumo.homecontrol.service.sunset;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jumajumo.core.service.weather.WeatherService;
import de.jumajumo.core.type.CurrentWeather;

@Service
public class SunsetServiceImpl implements SunsetService
{
	@Autowired
	private WeatherService weatherService;

	@Override
	public boolean isItDarkAt(final Date timeToCheck)
	{
		final CurrentWeather currentWeather = getWeatherService()
				.loadCurrentWeather();

		return currentWeather.getSunset().compareTo(timeToCheck) < 0;
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
