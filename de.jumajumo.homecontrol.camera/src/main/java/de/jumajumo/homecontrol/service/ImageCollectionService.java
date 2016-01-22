package de.jumajumo.homecontrol.service;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

public interface ImageCollectionService
{
	void collectGarbage() throws SocketException, IOException;

	void refreshCollection();

	List<ImageGroup> getImageCollection();

	void deleteImageGroup(long shotAt) throws SocketException, IOException;

}
