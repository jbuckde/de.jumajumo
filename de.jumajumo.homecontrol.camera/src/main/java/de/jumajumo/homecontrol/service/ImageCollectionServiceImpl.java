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
	private ImageStoreClientService imageStore;

	private List<ImageGroup> imageCollection = Collections
			.synchronizedList(new ArrayList<ImageGroup>());

	@Override
	public void collectGarbage() throws SocketException, IOException
	{
		final List<String> fileNamesToDelete = new ArrayList<String>();

		for (final ImageGroup imageGroup : this.imageCollection)
		{
			final Days days = Days.daysBetween(
					new DateTime(imageGroup.getShotAt()), new DateTime());

			if (days.isGreaterThan(Days.FIVE))
			{
				fileNamesToDelete.addAll(imageGroup.getFileNames());
			}
		}

		this.imageStore.deleteFiles(fileNamesToDelete);
	}

	@Override
	public void refreshCollection()
	{
		this.imageCollection.clear();
		this.imageCollection.addAll(this.imageStore.collectFiles());

		Collections.sort(this.imageCollection, new Comparator<ImageGroup>()
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

	public List<ImageGroup> getImageCollection()
	{
		return imageCollection;
	}

	@Override
	public void deleteImageGroup(long shotAt) throws SocketException,
			IOException
	{
		for (final ImageGroup imageGroup : imageCollection)
		{
			if (Long.compare(shotAt, imageGroup.getShotAt()) == 0)
			{
				final List<String> fileNamesToDelete = new ArrayList<String>();
				fileNamesToDelete.addAll(imageGroup.getFileNames());

				this.imageStore.deleteFiles(fileNamesToDelete);

				this.imageCollection.remove(imageGroup);
				break;
			}
		}
	}
}
