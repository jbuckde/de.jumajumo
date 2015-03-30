package de.jumajumo.core.persistence;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

@Service
public class CrudDAOImpl<T extends EntityObject> implements CrudDAO<T>
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<T> loadAll(final Class<T> entityObjectClass)
	{
		final TypedQuery<T> query = entityManager.createQuery("select a from "
				+ entityObjectClass.getSimpleName() + " a", entityObjectClass);

		return query.getResultList();
	}

	@Override
	public List<T> queryResultList(final Class<T> entityObjectClass,
			final String queryName, final Map<String, Object> parameters)
	{
		final TypedQuery<T> query = entityManager.createNamedQuery(queryName,
				entityObjectClass);

		for (Entry<String, Object> parameter : parameters.entrySet())
		{
			query.setParameter(parameter.getKey(), parameter.getValue());
		}

		return query.getResultList();
	}

	@Override
	public T querySingleResult(Class<T> entityObjectClass, String queryName,
			Map<String, Object> parameters)
	{
		final TypedQuery<T> query = entityManager.createNamedQuery(queryName,
				entityObjectClass);

		for (Entry<String, Object> parameter : parameters.entrySet())
		{
			query.setParameter(parameter.getKey(), parameter.getValue());
		}

		return query.getSingleResult();
	}

	@Override
	public T createOrUpdate(T bo)
	{
		// check the uuid is defined
		if (null == bo.getUuid())
		{
			bo.setUuid(UUID.randomUUID());
		}

		if ((bo.getId() > 0)
				&& (null != entityManager.find(bo.getClass(), bo.getId())))
		{
			entityManager.merge(bo);
		} else
		{
			entityManager.persist(bo);
		}

		return bo;
	}

	@Override
	public T findByUuid(final Class<T> entityObjectClass, final UUID uuid)
	{
		final TypedQuery<T> query = entityManager.createQuery(
				"select a from " + entityObjectClass.getSimpleName()
						+ " a where a.uuid = :uuid", entityObjectClass);

		query.setParameter("uuid", uuid);

		try
		{
			final T result = query.getSingleResult();

			return result;
		} catch (NoResultException e)
		{
			return null;
		}
	}

}
