package com.ustb.food.service;

import java.util.List;

public interface BaseService<M extends java.io.Serializable, PK extends java.io.Serializable> {

	public void clear();

	public int countAll();

	public int countAll(String propertyName, Object value);

	public int countAll(String propertyName1, Object value1,
			String propertyName2, Object value2);

	public int countAll(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3);

	public int countAll(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, String propertyName4, Object value4);

	public void delete(PK id);

	public void deleteObject(M model);

	boolean exists(PK id);

	public void flush();

	public M get(PK id);

	public List<M> getList();

	public List<M> getList(int pn, int pageSize);

	public List<M> getList(String propertyName, Object value);

	public List<M> getList(String propertyName, Object value, int pn,
			int pageSize);

	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2);

	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, int pn, int pageSize);

	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3);

	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, int pn, int pageSize);

	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, String propertyName4, Object value4);

	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, String propertyName4, Object value4, int pn,
			int pageSize);
	
	public List<M> getAroundList(String idName, int startId, int endId);

	public void merge(M model);

	public List<M> propertyIsNullList(String propertyName);

	public List<M> next(PK pk, int pn, int pageSize);

	public List<M> pre(PK pk, int pn, int pageSize);

	public PK save(M model);

	public void saveOrUpdate(M model);

	public void update(M model);

	public int getMax();

}
