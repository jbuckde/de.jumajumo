package de.jumajumo.homecontrol.service;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

public interface ImageStoreClientService
{

	List<ImageGroup> collectFiles();

	byte[] loadFile(final String fileName) throws IOException;

	void deleteFiles(List<String> fileNamesToDelete)
			throws SocketException, IOException;

	ECameraInformation getCameraInformation();

	List<ImageGroup> getImageCollection();
}
