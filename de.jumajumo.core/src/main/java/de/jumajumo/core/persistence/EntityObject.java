package de.jumajumo.core.persistence;

import java.util.UUID;

public interface EntityObject
{
	long getId();

	int getVersion();

	UUID getUuid();

	void setUuid(UUID uuid);
}
