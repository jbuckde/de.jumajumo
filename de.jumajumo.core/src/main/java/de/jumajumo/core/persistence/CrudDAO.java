package de.jumajumo.core.persistence;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CrudDAO<T extends EntityObject>
{
	T createOrUpdate(T bo);

	List<T> loadAll(Class<T> entityObjectClass);

	List<T> queryResultList(Class<T> entityObjectClass, String queryName,
			Map<String, Object> parameters);

	T querySingleResult(Class<T> entityObjectClass, String queryName,
			Map<String, Object> parameters);

	T findByUuid(Class<T> entityObjectClass, UUID uuid);

}
