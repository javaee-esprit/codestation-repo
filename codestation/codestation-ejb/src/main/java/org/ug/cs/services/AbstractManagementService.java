package org.ug.cs.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractManagementService<T, K> {

	private Class<T> entityClass;

	public AbstractManagementService(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public abstract EntityManager getEntityManager();

	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	public T findById(K id) {
		return getEntityManager().find(entityClass, id);
	}

	public void update(T entity) {
		getEntityManager().merge(entity);
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public List<T> findAll() {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	public Long count() {
		CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(Long.class);
		Root<T> root = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(root));
		return getEntityManager().createQuery(cq).getSingleResult();
	}
}
