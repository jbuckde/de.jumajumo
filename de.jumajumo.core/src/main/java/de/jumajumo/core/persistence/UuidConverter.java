package de.jumajumo.core.persistence;

import java.util.UUID;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

public class UuidConverter implements Converter
{

	/**
	 * serial version uuid
	 */
	private static final long serialVersionUID = -7999652597511947215L;

	@Override
	public Object convertDataValueToObjectValue(final Object databaseValue,
			final Session session)
	{
		final String databaseValueString = (String) databaseValue;
		if (null != databaseValueString)
		{
			return UUID.fromString(databaseValueString);
		} else
		{
			return null;
		}
	}

	@Override
	public Object convertObjectValueToDataValue(final Object objectValue,
			final Session session)
	{
		if (null != objectValue)
		{
			final UUID uuid = (UUID) objectValue;

			return uuid.toString();
		} else
		{
			return null;
		}
	}

	@Override
	public void initialize(final DatabaseMapping mapping, final Session session)
	{
		// unused
	}

	@Override
	public boolean isMutable()
	{
		return false;
	}

}
