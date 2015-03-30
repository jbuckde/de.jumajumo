package de.jumajumo.homecontrol.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.jumajumo.core.service.weather.WeatherService;
import de.jumajumo.core.type.CurrentWeather;

public class SunsetServiceTest
{
	private SunsetServiceImpl testee;

	@Mock
	private WeatherService weatherService;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);

		testee = new SunsetServiceImpl();
		testee.setWeatherService(this.weatherService);
	}

	@Test
	public void testSunsetYesNo() throws Exception
	{
		// define the weather mock
		this.defineWeatherMock();

		final Date timeToCheck = this.timeToday(14, 0);

		final boolean isItDark = testee.isItDarkAt(timeToCheck);

		Assert.assertFalse("should not be dark at 2:00 pm", isItDark);
	}

	private void defineWeatherMock()
	{
		final CurrentWeather mockedCurrentWeather = new CurrentWeather();

		mockedCurrentWeather.setSunrise(this.timeToday(6, 10));
		mockedCurrentWeather.setSunset(this.timeToday(20, 20));

		Mockito.when(weatherService.loadCurrentWeather()).thenReturn(
				mockedCurrentWeather);

	}

	private Date timeToday(int hour, int minutes)
	{
		final Calendar cal = Calendar.getInstance();

		Calendar working = GregorianCalendar.getInstance();
		working.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE), hour, minutes);

		return working.getTime();
	}

}
