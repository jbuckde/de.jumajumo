package de.jumajumo.core.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.jumajumo.core.persistence.EntityObject;

public interface CrudService<T extends EntityObject>
{
	T createOrUpdate(T bo);

	List<T> loadAll(Class<T> entityObjectClass);

	List<T> queryResultList(Class<T> entityObjectClass, String queryName,
			Map<String, Object> parameters);

	T querySingleResult(Class<T> entityObjectClass, String queryName,
			Map<String, Object> parameters);

	T findByUUID(Class<T> entityObjectClass, UUID uuid);

}
