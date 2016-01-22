package de.jumajumo.homecontrol.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageGroup
{
	private long shotAt;
	private List<Image> images = new ArrayList<Image>();

	public void addNewImage(final Image image)
	{
		this.images.add(image);
	}

	public long getShotAt()
	{
		return shotAt;
	}

	public void setShotAt(final long shotAt)
	{
		this.shotAt = shotAt;
	}

	public List<Image> getImages()
	{
		return Collections.unmodifiableList(this.images);
	}

	public List<String> getFileNames()
	{
		final List<String> fileNames = new ArrayList<String>();

		for (final Image image : this.images)
		{
			fileNames.add(image.getFileName());
		}

		return Collections.unmodifiableList(fileNames);
	}
}
