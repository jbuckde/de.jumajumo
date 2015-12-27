package de.jumajumo.homecontrol.service;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class FtpClientServiceImpl implements FtpClientService
{

	private final static Log LOGGER = LogFactory.getLog(FtpClientService.class);
	private final static Duration GROUP_DURATION = Duration.standardMinutes(5);

	public List<ImageGroup> collectFiles()
	{
		try
		{
			final List<FTPFile> files = this.getFiles();

			ImageGroup currentGroup = null;
			final List<ImageGroup> result = new ArrayList<ImageGroup>();

			for (final FTPFile ftpFile : files)
			{
				if ("..".equals(ftpFile.getName()))
				{
					continue;
				}

				if (null == currentGroup)
				{
					currentGroup = this.createNewGroup(ftpFile);
				} else
				{
					final Duration duration = new Duration(new Instant(
							currentGroup.getShotAt()), new Instant(ftpFile
							.getTimestamp().getTimeInMillis()));

					if (duration.isLongerThan(GROUP_DURATION))
					{
						result.add(currentGroup);
						currentGroup = this.createNewGroup(ftpFile);
					}
				}

				currentGroup.addNewFile(ftpFile.getName());
			}

			if (null != currentGroup)
			{
				result.add(currentGroup);
			}

			return result;
		}

		catch (IOException e)
		{
			LOGGER.error("problems on resolving files from ftp connection", e);
		}

		return null;
	}

	private ImageGroup createNewGroup(final FTPFile ftpFile)
	{
		final ImageGroup newGroup = new ImageGroup();
		newGroup.setShotAt(ftpFile.getTimestamp().getTimeInMillis());

		return newGroup;
	}

	private List<FTPFile> getFiles() throws IOException
	{
		final FTPClient ftpClient = this.getFtpClient();

		try
		{
			this.connectFtpClient(ftpClient);

			final FTPFile[] listFiles = ftpClient.listFiles("images");
			return Arrays.asList(listFiles);
		} finally
		{
			ftpClient.logout();
			ftpClient.disconnect();
		}
	}

	private FTPClient getFtpClient()
	{
		return new FTPClient();
	}

	private void connectFtpClient(final FTPClient ftpClient)
			throws SocketException, IOException
	{
		ftpClient.connect("81.169.145.47", 21);
		ftpClient.login("56324323.swh.strato-hosting.eu", "SamsungGalaxy1");
	}

}
