package de.jumajumo.core.service.filestore;

/**
 * the FileNotFoundException has to be thrown by the FileStorageService if a
 * requested file could not be found in the storage
 *
 */
public class FileNotFoundException extends RuntimeException
{

	/**
	 * the generated serial version uuid
	 */
	private static final long serialVersionUID = -251750004311987873L;

	private final FileHandle fileHandle;

	public FileNotFoundException(final FileHandle fileHandle)
	{
		this.fileHandle = fileHandle;
	}

	public FileHandle getFileHandle()
	{
		return fileHandle;
	}
}
