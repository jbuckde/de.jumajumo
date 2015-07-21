package de.jumajumo.core.service.filestore;

import java.io.InputStream;

/**
 * the FileStroageService is used to store and retrieve files from a file store
 * defined on the server
 * 
 * @author d054910
 *
 */
public interface FileStorageService
{
	void storeFile(final InputStream fileStream, final FileHandle fileHandle);

	void removeFile(final FileHandle fileHandle);

	InputStream loadFile(final FileHandle fileHandle);
}
