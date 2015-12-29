package de.jumajumo.homecontrol.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

}
