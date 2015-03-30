package de.jumajumo.core.service.weather;

import de.jumajumo.core.type.CurrentWeather;

public interface WeatherService
{

	/**
	 * Load current weather from the weather service and store it in the current
	 * weather structure
	 *
	 * @return the current weather
	 */
	CurrentWeather loadCurrentWeather();
}
