package de.jumajumo.homecontrol.service.condition;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.jumajumo.homecontrol.service.sunset.SunsetService;

public class ConditionIsItDarkTest
{
	private CondtitionIsItDark testee;

	@Mock
	private SunsetService sunsetService;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);

		testee = new CondtitionIsItDark();
		testee.setSunsetService(this.sunsetService);
	}

	@Test
	public void testIsItDark()
	{
		// define sunset service mock
		Mockito.when(this.sunsetService.isItDarkAt(Mockito.any(Date.class)))
				.thenReturn(true);

		Assert.assertTrue(testee.checkCondition());
	}
}
