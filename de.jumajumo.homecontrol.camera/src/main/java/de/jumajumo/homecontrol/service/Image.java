package de.jumajumo.homecontrol.service;

public class Image
{
	private long timestamp;
	private String fileName;
	private long size;

	public Image(final String fileName, final long timestamp, final long size)
	{
		super();

		this.fileName = fileName;
		this.timestamp = timestamp;
		this.size = size;
	}

	public long getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(long timestamp)
	{
		this.timestamp = timestamp;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public long getSize()
	{
		return size;
	}

	public void setSize(long size)
	{
		this.size = size;
	}
}
