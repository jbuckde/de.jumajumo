package de.jumajumo.homecontrol.service;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageCollectionServiceImpl implements ImageCollectionService
{
	@Autowired
	private List<ImageStoreClientService> imageStores;

	@Override
	public void collectGarbage(final ECameraInformation cameraInformation)
			throws SocketException, IOException
	{
		final List<String> fileNamesToDelete = new ArrayList<String>();

		for (final ImageGroup imageGroup : this
				.getImageCollection(cameraInformation))
		{
			final Days days = Days.daysBetween(
					new DateTime(imageGroup.getShotAt()), new DateTime());

			if (days.isGreaterThan(Days.FIVE))
			{
				fileNamesToDelete.addAll(imageGroup.getFileNames());
			}
		}

		this.getImageStore(cameraInformation).deleteFiles(fileNamesToDelete);
	}

	@Override
	public void refreshCollection(final ECameraInformation cameraInformation)
	{
		this.getImageCollection(cameraInformation).clear();
		this.getImageCollection(cameraInformation)
				.addAll(this.getImageStore(cameraInformation).collectFiles());

		Collections.sort(this.getImageCollection(cameraInformation),
				new Comparator<ImageGroup>()
				{
					@Override
					public int compare(ImageGroup o1, ImageGroup o2)
					{
						if (o1.getShotAt() > o2.getShotAt())
						{
							return -1;
						} else if (o1.getShotAt() < o2.getShotAt())
						{
							return 1;
						} else
						{
							return 0;
						}
					}
				});
	}

	@Override
	public void deleteImageGroup(final ECameraInformation cameraInformation,
			long shotAt) throws SocketException, IOException
	{
		for (final ImageGroup imageGroup : getImageCollection(
				cameraInformation))
		{
			if (Long.compare(shotAt, imageGroup.getShotAt()) == 0)
			{
				final List<String> fileNamesToDelete = new ArrayList<String>();
				fileNamesToDelete.addAll(imageGroup.getFileNames());

				this.getImageStore(cameraInformation)
						.deleteFiles(fileNamesToDelete);

				this.getImageCollection(cameraInformation).remove(imageGroup);
				break;
			}
		}
	}

	@Override
	public byte[] loadImage(final ECameraInformation cameraInformation,
			String imageName) throws IOException
	{
		return this.getImageStore(cameraInformation).loadFile(imageName);
	}

	private ImageStoreClientService getImageStore(
			final ECameraInformation cameraInformation)
	{
		for (ImageStoreClientService imageStore : this.imageStores)
		{
			if (imageStore.getCameraInformation().equals(cameraInformation))
			{
				return imageStore;
			}
		}

		throw new IllegalArgumentException(
				"no image store found for camera <" + cameraInformation + ">");
	}

	@Override
	public List<ImageGroup> getImageCollection(
			final ECameraInformation cameraInformation)
	{
		return this.getImageStore(cameraInformation).getImageCollection();
	}

}
