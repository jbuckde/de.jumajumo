package de.jumajumo.core.service.filestore;

/**
 * the FileHandle is used to identify a file for the FileStorageService
 * 
 */
public class FileHandle
{
	private final String fileName;

	public FileHandle(final String fileName)
	{
		this.fileName = fileName;
	}

	public String getFileName()
	{
		return fileName;
	}

}
