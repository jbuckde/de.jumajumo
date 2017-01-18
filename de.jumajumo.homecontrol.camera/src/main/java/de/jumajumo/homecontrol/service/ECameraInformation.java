package de.jumajumo.homecontrol.service;

import java.util.Objects;

public enum ECameraInformation
{
	DOOR("door", "images", "81.169.145.47", 21,
			"56324323.swh.strato-hosting.eu", "SamsungGalaxy1"), //
	BACK("back", "backimg", "81.169.145.47", 21,
			"56324323.swh.strato-hosting.eu", "SamsungGalaxy1");

	private final String cameraName;
	private final String folderName;
	private final String ftpHost;
	private final int ftpPort;
	private final String ftpUser;
	private final String ftpPwd;

	ECameraInformation(final String cameraName, final String folderName,
			final String ftpHost, final int ftpPort, final String ftpUser,
			final String ftpPwd)
	{
		this.cameraName = cameraName;
		this.folderName = folderName;
		this.ftpHost = ftpHost;
		this.ftpPort = ftpPort;
		this.ftpUser = ftpUser;
		this.ftpPwd = ftpPwd;
	}

	public static ECameraInformation fromCameraName(final String cameraName)
	{
		final String operationCameraName = Objects.toString(cameraName, "");
		for (final ECameraInformation o : ECameraInformation.values())
		{
			if (o.toString().equals(operationCameraName))
			{
				return o;
			}
		}
		throw new IllegalArgumentException(
				"Unknown camera name: " + cameraName);
	}

	@Override
	public String toString()
	{
		return this.getCameraName();
	}

	public String getCameraName()
	{
		return cameraName;
	}

	public String getFolderName()
	{
		return this.folderName;
	}

	public String getFtpHost()
	{
		return ftpHost;
	}

	public int getFtpPort()
	{
		return ftpPort;
	}

	public String getFtpUser()
	{
		return ftpUser;
	}

	public String getFtpPwd()
	{
		return ftpPwd;
	}
}
