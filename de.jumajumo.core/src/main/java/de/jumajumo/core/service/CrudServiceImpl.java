package de.jumajumo.core.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.jumajumo.core.persistence.CrudDAO;
import de.jumajumo.core.persistence.EntityObject;

@Service
@Transactional
public class CrudServiceImpl<T extends EntityObject> implements CrudService<T>
{
	@Autowired
	private UserService userService;

	@Autowired
	private CrudDAO<T> crudDAO;

	@Override
	public T createOrUpdate(T bo)
	{
		return crudDAO.createOrUpdate(bo);
	}

	@Override
	public List<T> loadAll(final Class<T> entityObjectClass)
	{
		return crudDAO.loadAll(entityObjectClass);
	}

	@Override
	public List<T> queryResultList(final Class<T> entityObjectClass,
			final String queryName, final Map<String, Object> parameters)
	{
		return this.crudDAO.queryResultList(entityObjectClass, queryName,
				parameters);
	}

	@Override
	public T querySingleResult(Class<T> entityObjectClass, String queryName,
			Map<String, Object> parameters)
	{
		return this.crudDAO.querySingleResult(entityObjectClass, queryName,
				parameters);
	}

	@Override
	public T findByUUID(final Class<T> entityObjectClass, final UUID uuid)
	{
		// TODO Auto-generated method stub
		return crudDAO.findByUuid(entityObjectClass, uuid);
	}

}
