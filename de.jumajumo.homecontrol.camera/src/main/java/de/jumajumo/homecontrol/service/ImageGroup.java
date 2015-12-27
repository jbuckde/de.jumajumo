package de.jumajumo.homecontrol.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageGroup
{
	private long shotAt;
	private List<String> files = new ArrayList<String>();

	public void addNewFile(final String fileName)
	{
		this.files.add(fileName);
	}

	public long getShotAt()
	{
		return shotAt;
	}

	public void setShotAt(final long shotAt)
	{
		this.shotAt = shotAt;
	}

	public List<String> getFiles()
	{
		return Collections.unmodifiableList(this.files);
	}

}
