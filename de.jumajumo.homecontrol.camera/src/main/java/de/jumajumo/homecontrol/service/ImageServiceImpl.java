package de.jumajumo.homecontrol.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService
{
	@Autowired
	private ImageStoreClientService imageStore;

	@Override
	public byte[] loadImage(String imageName) throws IOException
	{
		return this.imageStore.loadFile(imageName);
	}

}
