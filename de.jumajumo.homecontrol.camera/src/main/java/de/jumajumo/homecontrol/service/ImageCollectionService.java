package de.jumajumo.homecontrol.service;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

public interface ImageCollectionService
{
	void collectGarbage(final ECameraInformation cameraInformation)
			throws SocketException, IOException;

	void refreshCollection(final ECameraInformation cameraInformation);

	void deleteImageGroup(final ECameraInformation cameraInformation,
			long shotAt) throws SocketException, IOException;

	byte[] loadImage(final ECameraInformation cameraInformation,
			final String imageName) throws IOException;

	List<ImageGroup> getImageCollection(
			final ECameraInformation cameraInformation);
}
