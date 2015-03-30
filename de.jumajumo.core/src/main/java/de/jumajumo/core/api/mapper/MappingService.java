package de.jumajumo.core.api.mapper;

import de.jumajumo.core.api.facade.DataTransferObject;
import de.jumajumo.core.persistence.EntityObject;

public interface MappingService
{
	<DTO extends DataTransferObject> DTO mapBOToDTO(EntityObject entityObject,
			DTO dataTransferObject);
}
