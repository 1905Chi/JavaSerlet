package vn.iotstar.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import vn.iotstar.JPAConfig.JpaConfig;

public abstract class AbstractDao<T> {
	private Class<T> entityClass;

	public AbstractDao(Class<T> cls) {
		this.entityClass = cls;
	}

	public void insert(T entity) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// thêm
			enma.persist(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public void update(T entity) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// update
			enma.merge(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public void delete(Object id) throws Exception {

		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// TÌm cate
			T entity = enma.find(entityClass, id);
			if (entity != null) {
				// delete
				enma.remove(entity);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public T findById(Object id) {

		EntityManager enma = JpaConfig.getEntityManager();
		T entity = enma.find(entityClass, id);
		return entity;
	}

	public List<T> findAll() {

		EntityManager enma = JpaConfig.getEntityManager();
		try {
			CriteriaQuery cq = enma.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			return enma.createQuery(cq).getResultList();
		} finally {
			enma.close();
		}
	}

	public Long countAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			CriteriaQuery cq = enma.getCriteriaBuilder().createQuery();
			Root<T> rt = cq.from(entityClass);
			cq.select(enma.getCriteriaBuilder().count(rt));
			Query q = enma.createQuery(cq);
			return (Long) q.getSingleResult();
		} finally {
			enma.close();
		}
	}

	public List<T> findAll(boolean all, int firstResult, int maxResult) {

		EntityManager enma = JpaConfig.getEntityManager();
		try {
			CriteriaQuery cq = enma.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			Query q = enma.createQuery(cq);
			if (!all) {
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
			}
			return q.getResultList();
		} finally {
			enma.close();
		}
	}
}
