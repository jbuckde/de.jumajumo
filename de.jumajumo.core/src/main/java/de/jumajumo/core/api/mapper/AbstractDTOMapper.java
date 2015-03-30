package de.jumajumo.core.api.mapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.jumajumo.core.api.facade.DataTransferObject;
import de.jumajumo.core.persistence.EntityObject;

public abstract class AbstractDTOMapper<EO extends EntityObject, DTO extends DataTransferObject>
		implements DTOMapper<EO, DTO>
{

	@PersistenceContext
	private EntityManager entityManager;

	protected EO flush(EO entityObject)
	{
		this.entityManager.flush();

		return (EO) this.entityManager.find(entityObject.getClass(),
				entityObject.getId());
	}

}
