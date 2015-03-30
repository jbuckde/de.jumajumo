package de.jumajumo.core.api.mapper;

import de.jumajumo.core.api.facade.DataTransferObject;
import de.jumajumo.core.persistence.EntityObject;

public interface DTOMapper<EO extends EntityObject, DTO extends DataTransferObject>
{

	boolean canMap(Class<EntityObject> businessObjectClass,
			Class<DataTransferObject> dtoClass);

	DTO mapBOToDTO(EO entityObject, DTO dataTransferObject);
}
