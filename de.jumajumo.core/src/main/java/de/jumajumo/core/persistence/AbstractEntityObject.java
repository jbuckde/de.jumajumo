package de.jumajumo.core.persistence;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Converter(name = "uuidConverter", converterClass = de.jumajumo.core.persistence.UuidConverter.class)
public abstract class AbstractEntityObject implements EntityObject
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "NODE_SEQ", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "NODE_SEQ", sequenceName = "NODE_SEQ", initialValue = 100, allocationSize = 100)
	private long id;

	@Version
	@Column
	private int version;

	@Column
	@Convert("uuidConverter")
	private UUID uuid;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntityObject other = (AbstractEntityObject) obj;
		if (id != other.id)
			return false;
		if (uuid == null)
		{
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

}
