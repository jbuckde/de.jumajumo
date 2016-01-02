package de.jumajumo.homecontrol.service;

import java.io.IOException;
import java.util.List;

public interface ImageStoreClientService
{

	List<ImageGroup> collectFiles();

	byte[] loadFile(final String fileName) throws IOException;
}
