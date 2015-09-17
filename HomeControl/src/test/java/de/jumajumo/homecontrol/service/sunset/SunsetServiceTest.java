package de.jumajumo.homecontrol.service.sunset;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.jumajumo.core.service.weather.WeatherService;
import de.jumajumo.core.type.CurrentWeather;

@RunWith(MockitoJUnitRunner.class)
public class SunsetServiceTest
{
	@InjectMocks
	private SunsetServiceImpl testee;

	@Mock
	private WeatherService weatherService;

	@Before
	public void setup()
	{
		// define the weather mock
		this.defineWeatherMock();
	}

	@Test
	public void testSunsetYesNo() throws Exception
	{
		final Date timeToCheck = this.timeToday(14, 0);

		final boolean isItDark = this.testee.isItDarkAt(timeToCheck);

		Assert.assertFalse("should not be dark at 2:00 pm", isItDark);
	}

	@Test
	public void testSunriseYesNo() throws Exception
	{
		final Date timeToCheck = this.timeToday(4, 0);

		final boolean isItDark = this.testee.isItDarkAt(timeToCheck);

		Assert.assertTrue("should be dark at 4:00 am", isItDark);
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
