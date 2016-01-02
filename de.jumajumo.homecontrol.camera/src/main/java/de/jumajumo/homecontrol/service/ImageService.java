package de.jumajumo.homecontrol.service;

import java.io.IOException;

public interface ImageService
{
	byte[] loadImage(final String imageName) throws IOException;
}
