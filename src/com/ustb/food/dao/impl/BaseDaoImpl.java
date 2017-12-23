package com.ustb.food.dao.impl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.persistence.Id;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ustb.food.dao.BaseDao;

public abstract class BaseDaoImpl<M extends java.io.Serializable, PK extends java.io.Serializable>
		implements BaseDao<M, PK> {
	/* @Autowired */
	private SessionFactory sessionFactory;
	private final Class<M> entityClass;
	private final String HQL_LIST_ALL;
	private final String HQL_COUNT_ALL;
	private final String HQL_OPTIMIZE_PRE_LIST_ALL;
	private final String HQL_OPTIMIZE_NEXT_LIST_ALL;
	private String pkName = null;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		/*
		 * getClass().getGenericSuperclass()返回表示此 Class的泛型
		 * 然后将其转换ParameterizedType。。 getActualTypeArguments()返回表示此泛型的实际类型。
		 * [0]就是这个数组中第一个了。。 简而言之就是获得超类的泛型参数的实际类型。。
		 */
		this.entityClass = (Class<M>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		// 在Field上添加Annotation获取主键名
		/*
		 * Field[] fields = this.entityClass.getDeclaredFields();
		 * 
		 * for (Field f : fields) { if (f.isAnnotationPresent(Id.class)) {
		 * this.pkName = f.getName(); } }
		 */

		// 在Method上添加Annotation获取主键名,获取每个方法的注解，并且得到注解为ID的后将方法的名字的第四个变成小写之后，在进行拼接就得到了主键
		Method[] Methods = this.entityClass.getDeclaredMethods();
		for (Method m : Methods) {
			/* Id.class表示的是注解为Id的值 */
			if (m.isAnnotationPresent(Id.class)) {
				this.pkName = m.getName().substring(3, 4).toLowerCase()
						+ m.getName().substring(4);
				break;
			}
		}
		// TODO @Entity name not null
		HQL_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " order by " + pkName + " asc";
		HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " where " + pkName + " > ? order by " + pkName + " asc";
		HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " where " + pkName + " < ? order by " + pkName + " desc";
		HQL_COUNT_ALL = " select count(*) from "
				+ this.entityClass.getSimpleName();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public PK save(M model) {
		System.out.println("-------");
		System.out.println(model.toString());
		return (PK) getSession().save(model);
	}

	@Override
	public void saveOrUpdate(M model) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(model);
	}

	@Override
	public void update(M model) {
		getSession().update(model);

	}

	@Override
	public void merge(M model) {
		getSession().merge(model);

	}

	@Override
	public void delete(PK id) {
		getSession().delete(this.get(id));

	}

	@Override
	public void deleteObject(M model) {
		// TODO Auto-generated method stub
		getSession().delete(model);
	}

	@Override
	public M get(PK id) {
		// TODO Auto-generated method stub
		return (M) getSession().get(this.entityClass, id);
	}

	@Override
	public int countAll() {
		Long total = aggregate(HQL_COUNT_ALL);
		return total.intValue();
	}

	@Override
	public int countAll(String propertyName, Object value) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from " + entityClass.getName()
				+ " as model where model." + propertyName + " = ?";
		Long total = aggregate(hql, value);
		return total.intValue();
	}

	@Override
	public int countAll(String propertyName1, Object value1,
			String propertyName2, Object value2) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from " + entityClass.getName()
				+ " as model where model." + propertyName1 + " = ?"
				+ "and model." + propertyName2 + " = ?";
		Long total = aggregate(hql, value1, value2);
		return total.intValue();
	}

	@Override
	public int countAll(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from " + entityClass.getName()
				+ " as model where model." + propertyName1 + " = ?"
				+ "and model." + propertyName2 + " = ?" + "and model."
				+ propertyName3 + " = ?";
		Long total = aggregate(hql, value1, value2, value3);
		return total.intValue();
	}

	@Override
	public int countAll(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, String propertyName4, Object value4) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from " + entityClass.getName()
				+ " as model where model." + propertyName1 + " = ?"
				+ "and model." + propertyName2 + " = ?" + "and model."
				+ propertyName4 + " = ?" + "and model." + propertyName4
				+ " = ?";
		Long total = aggregate(hql, value1, value2, value3, value4);
		return total.intValue();
	}

	@Override
	public List<M> getList() {
		// TODO Auto-generated method stub
		return list(HQL_LIST_ALL, -1, -1);
	}

	@Override
	public List<M> getList(int pn, int pageSize) {
		// TODO Auto-generated method stub
		return list(HQL_LIST_ALL, pn, pageSize);
	}

	@Override
	public List<M> getList(String propertyName, Object value) {
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?" + " order by " + pkName + " desc";
		return list(hql, -1, -1, value);
	}

	@Override
	public List<M> getList(String propertyName, Object value, int pn,
			int pageSize) {
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?" + " order by " + pkName + " desc";
		return list(hql, pn, pageSize, value);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2) {
		// TODO Auto-generated method stub
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName1 + " = ?" + "and model." + propertyName2
				+ " = ?" + " order by " + pkName + " desc";
		// return getSession().createQuery(hql).setParameter(0, value).list();
		return list(hql, -1, -1, value1, value2);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, int pn, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName1 + " = ?" + "and model." + propertyName2
				+ " = ?" + " order by " + pkName + " desc";
		// return getSession().createQuery(hql).setParameter(0, value).list();
		return list(hql, pn, pageSize, value1, value2);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3) {
		// TODO Auto-generated method stub
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName1 + " = ?" + "and model." + propertyName2
				+ " = ?" + "and model." + propertyName3 + " = ?" + " order by "
				+ pkName + " desc";
		// return getSession().createQuery(hql).setParameter(0, value).list();
		return list(hql, -1, -1, value1, value2, value3);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, int pn, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName1 + " = ?" + "and model." + propertyName2
				+ " = ?" + "and model." + propertyName3 + " = ?" + " order by "
				+ pkName + " desc";
		// return getSession().createQuery(hql).setParameter(0, value).list();
		return list(hql, pn, pageSize, value1, value2, value3);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, String propertyName4, Object value4) {
		// TODO Auto-generated method stub
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName1 + " = ?" + "and model." + propertyName2
				+ " = ?" + "and model." + propertyName3 + " = ?" + "and model."
				+ propertyName4 + " = ?" + " order by " + pkName + " desc";
		// return getSession().createQuery(hql).setParameter(0, value).list();
		return list(hql, -1, -1, value1, value2, value3, value4);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, String propertyName4, Object value4, int pn,
			int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName1 + " = ?" + "and model." + propertyName2
				+ " = ?" + "and model." + propertyName3 + " = ?" + "and model."
				+ propertyName4 + " = ?" + " order by " + pkName + " desc";
		// return getSession().createQuery(hql).setParameter(0, value).list();
		return list(hql, pn, pageSize, value1, value2, value3, value4);
	}

	@Override
	public List<M> propertyIsNullList(String propertyName) {
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " is null";
		return list(hql, -1, -1, null);
	}

	@Override
	public List<M> pre(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		if (pk == null) {
			return list(HQL_LIST_ALL, pn, pageSize);
		}
		// 倒序，重排
		List<M> result = list(HQL_OPTIMIZE_PRE_LIST_ALL, 1, pageSize, pk);
		Collections.reverse(result);
		return result;
	}

	@Override
	public List<M> next(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		if (pk == null) {
			return list(HQL_LIST_ALL, pn, pageSize);
		}
		return list(HQL_OPTIMIZE_NEXT_LIST_ALL, 1, pageSize, pk);
	}

	@Override
	public boolean exists(PK id) {
		// TODO Auto-generated method stub
		return get(id) != null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		getSession().flush();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		getSession().clear();
	}

	@SuppressWarnings("unchecked")
	protected <T> T aggregate(final String hql,
			final Map<String, Collection<?>> map, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		if (paramlist != null) {
			setParameters(query, paramlist);
			for (Entry<String, Collection<?>> e : map.entrySet()) {
				query.setParameterList(e.getKey(), e.getValue());
			}
		}

		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	protected <T> T aggregate(final String hql, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		return (T) query.uniqueResult();
	}

	protected void setParameters(Query query, Object[] paramlist) {
		if (paramlist != null) {
			for (int i = 0; i < paramlist.length; i++) {
				if (paramlist[i] instanceof Date) {
					query.setTimestamp(i, (Date) paramlist[i]);
				} else {
					query.setParameter(i, paramlist[i]);
				}
			}
		}
	}

//	protected <T> List<T> list(final String sql, final Object... paramlist) {
//		return list(sql, -1, -1, paramlist);
//	}

	protected <T> List<T> list(final String hql, final int pn,
			final int pageSize, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		if (pn > -1 && pageSize > -1) {
			/* setMaxResults设置最大的结果集个数 */
			query.setMaxResults(pageSize);
			int start = (pn - 1) * pageSize;
			if (start != 0) {
				// setFirstResult设置从哪个算作第一个
				query.setFirstResult(start);
			}
		}
		if (pn < 0) {
			query.setFirstResult(0);
		}
		List<T> results = query.list();
		return results;
	}

	@Override
	public int getMax() {
		String hql = "select max(r.viewId) from Caipu r";
		return (Integer) list(hql).get(0);
	}

	@Override
	public List<M> getAroundList(String idName, int startId, int endId) {
		String hql = "from " + entityClass.getName() + " as model where model."
				+ idName + " >= ?" + "and model." + idName
				+ " <= ? order by "
				+ pkName + " desc";
		return list(hql, 0, 0, startId, endId);
//		 return getSession().createQuery(hql).setParameter(0, startId).setParameter(1, endId).list();
	}
}
