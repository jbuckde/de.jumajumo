package de.jumajumo.homecontrol.service.filestore;

import java.io.InputStream;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import de.jumajumo.core.service.filestore.FileHandle;
import de.jumajumo.core.service.filestore.FileNotFoundException;
import de.jumajumo.core.service.filestore.FileStorageService;

/**
 * the FileStorageMongoDBServiceImpl is the implementation of the
 * FileStorageService to save a file in the local mongo db
 *
 */
public class FileStorageMongoDBServiceImpl implements FileStorageService
{
	private String hostName;
	private String dbName;

	private Mongo mongo;

	@Override
	public void storeFile(InputStream fileStream, FileHandle fileHandle)
	{
		final GridFS fs = this.getMongoFileSystem();

		// remove the file if it already exists
		fs.remove(new ObjectId(fileHandle.getFileName().getBytes()));

		// save the file to the database
		GridFSInputFile in = fs.createFile(fileStream);
		in.setId(new ObjectId(fileHandle.getFileName().getBytes()));
		in.save();
	}

	@Override
	public InputStream loadFile(FileHandle fileHandle)
	{
		final GridFS fs = this.getMongoFileSystem();

		final InputStream fileStream;

		try
		{
			// load the file
			GridFSDBFile out = fs.findOne(new BasicDBObject("_id",
					new ObjectId(fileHandle.getFileName().getBytes())));

			if (null != out)
			{
				fileStream = out.getInputStream();
			} else
			{
				throw new FileNotFoundException(fileHandle);
			}
		} catch (IllegalArgumentException e)
		{
			throw new FileNotFoundException(fileHandle);
		}

		return fileStream;
	}

	@Override
	public void removeFile(FileHandle fileHandle)
	{
		final GridFS fs = this.getMongoFileSystem();

		// remove the file if it already exists
		fs.remove(new ObjectId(fileHandle.getFileName().getBytes()));
	}

	private GridFS getMongoFileSystem()
	{
		if (null == this.mongo)
		{
			this.mongo = new MongoClient(this.getHostName());
		}

		String dbName = this.getDbName();

		DB db = this.mongo.getDB(dbName);
		GridFS fs = new GridFS(db);

		return fs;
	}

	public String getHostName()
	{
		return hostName;
	}

	public void setHostName(String hostName)
	{
		this.hostName = hostName;
	}

	public String getDbName()
	{
		return dbName;
	}

	public void setDbName(String dbName)
	{
		this.dbName = dbName;
	}

}
