package de.jumajumo.core.type;

import java.util.Date;

/**
 * The Class CurrentWeather is used to hold the current weather data loaded from
 * a weather provider.
 */
public class CurrentWeather
{
	private Date sunrise;
	private Date sunset;

	public Date getSunrise()
	{
		return sunrise;
	}

	public void setSunrise(Date sunrise)
	{
		this.sunrise = sunrise;
	}

	public Date getSunset()
	{
		return sunset;
	}

	public void setSunset(Date sunset)
	{
		this.sunset = sunset;
	}
}
