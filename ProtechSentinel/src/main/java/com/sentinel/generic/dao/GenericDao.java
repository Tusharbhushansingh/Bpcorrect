package com.sentinel.generic.dao;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class GenericDao<T extends Object> {

	@Autowired
	public SessionFactory sessionFactory;

	public Session getSession() {

		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	/**
	 * save() method use for save the data in database
	 */
	public T save(Session session, T object) {
		try {

			T obj = (T) session.save(object);

			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * update() method use for update row the data in database
	 */
	public Object update(Session session, T object) {
		try {
			session.update(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * saveAndUpdate() method use for save and update row in database
	 */
	public Object saveAndUpdate(Session session, T object) {
		try {
			session.saveOrUpdate(object);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * saveOrUpdateAll() method use for save and update all data in database
	 */
	public Object saveOrUpdateAll(Session session, Collection<T> collection) {
		try {

			for (T object : collection) {
				session.saveOrUpdate(object);

			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * delete() method use for delete data in database
	 */
	public Object delete(Session session, T object) {
		try {
			session.delete(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * findAll() method use for find all data in database
	 */
	public T findAll(Session session, Class clz, T... columnDetails) {
		try {

			Criteria criteria = session.createCriteria(clz);

			int l = 2;
			int u = l;

			for (int i = 0; i < columnDetails.length; i++) {

				if (i == (u - 1)) {

					System.out.println("colun details:- "
							+ columnDetails[i - 1] + "/" + columnDetails[i]);

					criteria.add(Restrictions.eq(
							columnDetails[i - 1].toString(), columnDetails[i]));

					u = u + l;
				}

			}

			T object = (T) criteria.list();

			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * findByID() method use for find data by id in database
	 */
	public T findByID(Session session, Class clz, T value) {
		try {

			Criteria criteria = session.createCriteria(clz).add(
					Restrictions.idEq(value));

			T object = (T) criteria.uniqueResult();

			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * findByColumn() method use for find data by column in database
	 */
	public T findByColumn(Session session, Class clz, String key, T value) {
		try {

			Criteria criteria = session.createCriteria(clz).add(
					Restrictions.eq(key, value));

			T object = (T) criteria.list();

			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * findByColumn() method use for find data by column in database
	 */
	public T findByColumnUnique(Session session, Class clz, String key, T value) {
		try {

			Criteria criteria = session.createCriteria(clz).add(
					Restrictions.eq(key, value));

			T object = (T) criteria.uniqueResult();

			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @param Session
	 * @param sql
	 * @return
	 */
	public T executeSqlQuery(Session session, String sql) {
		try {
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			T results = (T) query.list();
			return results;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param Session
	 * @param sql
	 * @return
	 */
	public Object executeQuery(Session session, String sql) {
		try {
			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();
			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			// Object results = query.list();
			return true;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param Session
	 * @param sql
	 * @param clz
	 * @return
	 */
	public T executeSqlQuery(Session session, String sql, Class clz) {
		try {

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(clz);
			T results = (T) query.list();

			return results;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param Session
	 * @param sql
	 * @param clz
	 * @return
	 */
	public T executeSqlQueryUnique(Session session, String sql, Class clz) {
		try {

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(clz);
			T results = (T) query.uniqueResult();

			return results;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param Session
	 * @param clz
	 * @param storeProcedure
	 * @param values
	 * @return
	 */
	public T executeStoreProcedure(Session session, Class clz,
			String storeProcedure, T... values) {
		try {

			Query query = session.createSQLQuery(storeProcedure);

			for (int i = 0; i < values.length; i++) {
				System.out.println(i+"parameter:-"+values[i]);
				query.setParameter(i, values[i]);
			}
			if (clz != null) {
				query.setResultTransformer(Transformers.aliasToBean(clz));
			} else {

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			}

			return (T) query.list();

		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public T executemappingStoreProcedure(Session session, Class clz,
			String storeProcedure, T... values) {
		try {

			Query query = session.createSQLQuery(storeProcedure);

			for (int i = 0; i < values.length; i++) {
				System.out.println(i+"parameter:-"+values[i]);
				query.setParameter(i, values[i]);
			}
			if (clz != null) {
				query.setResultTransformer(Transformers.aliasToBean(clz));
			} else {

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			}

			return (T) query.list();

		} catch (Exception e) {
			throw e;
		}
	}
	
}
