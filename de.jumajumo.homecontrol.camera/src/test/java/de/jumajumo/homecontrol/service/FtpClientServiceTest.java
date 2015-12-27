package de.jumajumo.homecontrol.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FtpClientServiceTest
{
	private FtpClientServiceImpl testee;

	@Before
	public void setup()
	{
		testee = new FtpClientServiceImpl();
	}

	@Test
	public void testGetFiles() throws Exception
	{
		final List<ImageGroup> groups = testee.collectFiles();

		for (final ImageGroup imageGroup : groups)
		{
			System.out.println(DateFormat.getInstance().format(
					new Date(imageGroup.getShotAt()))
					+ " / (" + imageGroup.getFiles().size() + ")");
		}
	}
}
