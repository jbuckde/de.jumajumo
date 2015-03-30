package de.jumajumo.core.api.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.jumajumo.core.api.facade.DataTransferObject;
import de.jumajumo.core.persistence.EntityObject;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MapperServiceImpl implements MappingService
{
	@Autowired
	List<DTOMapper> mappers;

	@Override
	public <DTO extends DataTransferObject> DTO mapBOToDTO(
			EntityObject entityObject, DTO dataTransferObject)
	{
		// try to find a mapper instance to do this mapping job
		final DTOMapper<EntityObject, DTO> mapper = this.resolveMapper(
				(Class<EntityObject>) entityObject.getClass(),
				(Class<DataTransferObject>) dataTransferObject.getClass());

		// map...
		return mapper.mapBOToDTO(entityObject, dataTransferObject);
	}

	/**
	 * Resolve mapper.
	 * 
	 * @param businessObjectClass
	 *            the business object class
	 * @param dtoClass
	 *            the dto class
	 * @return the DTO mapper
	 */
	private DTOMapper resolveMapper(Class<EntityObject> entityObjectClass,
			Class<DataTransferObject> dtoClass)
	{
		for (final DTOMapper mapper : mappers)
		{
			if (mapper.canMap(entityObjectClass, dtoClass))
			{
				return mapper;
			}
		}

		throw new IllegalArgumentException("no mapper found.");
	}
}
