package de.jumajumo.homecontrol.service;

import java.util.List;

public interface ImageCollectionService
{
	void refreshCollection();

	List<ImageGroup> getImageCollection();

}
