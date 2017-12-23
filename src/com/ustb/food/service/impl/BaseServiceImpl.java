package com.ustb.food.service.impl;

import java.util.List;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.service.BaseService;

public abstract class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable>
		implements BaseService<M, PK> {

	BaseDao<M, PK> baseDao;

	public abstract void setBaseDao(BaseDao<M, PK> baseDao);

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		baseDao.clear();
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return baseDao.countAll();
	}

	@Override
	public int countAll(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return baseDao.countAll(propertyName, value);
	}

	@Override
	public int countAll(String propertyName1, Object value1,
			String propertyName2, Object value2) {
		// TODO Auto-generated method stub
		return baseDao.countAll(propertyName1, value1, propertyName2, value2);
	}

	@Override
	public int countAll(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3) {
		// TODO Auto-generated method stub
		return baseDao.countAll(propertyName1, value1, propertyName2, value2,
				propertyName3, value3);
	}

	@Override
	public int countAll(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, String propertyName4, Object value4) {
		// TODO Auto-generated method stub
		return baseDao.countAll(propertyName1, value1, propertyName2, value2,
				propertyName3, value3, propertyName4, value4);
	}

	@Override
	public void delete(PK id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);
	}

	@Override
	public void deleteObject(M model) {
		// TODO Auto-generated method stub
		baseDao.deleteObject(model);
	}

	@Override
	public boolean exists(PK id) {
		// TODO Auto-generated method stub
		return baseDao.exists(id);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		baseDao.flush();
	}

	@Override
	public M get(PK id) {
		// TODO Auto-generated method stub
		return baseDao.get(id);
	}

	@Override
	public List<M> getList() {
		// TODO Auto-generated method stub
		return baseDao.getList();
	}

	@Override
	public List<M> getList(int pn, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.getList(pn, pageSize);
	}

	@Override
	public List<M> getList(String propertyName, Object value) {
		return baseDao.getList(propertyName, value);
	}

	@Override
	public List<M> getList(String propertyName, Object value, int pn,
			int pageSize) {
		return baseDao.getList(propertyName, value, pn, pageSize);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2) {
		// TODO Auto-generated method stub
		return baseDao.getList(propertyName1, value1, propertyName2, value2);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.getList(propertyName1, value1, propertyName2, value2,
				pn, pageSize);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3) {
		// TODO Auto-generated method stub
		return baseDao.getList(propertyName1, value1, propertyName2, value2,
				propertyName3, value3);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.getList(propertyName1, value1, propertyName2, value2,
				propertyName3, value3, pn, pageSize);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, String propertyName4, Object value4) {
		// TODO Auto-generated method stub
		return baseDao.getList(propertyName1, value1, propertyName2, value2,
				propertyName3, value3, propertyName4, value4);
	}

	@Override
	public List<M> getList(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3, String propertyName4, Object value4, int pn,
			int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.getList(propertyName1, value1, propertyName2, value2,
				propertyName3, value3, propertyName4, value4, pn, pageSize);
	}

	@Override
	public void merge(M model) {
		// TODO Auto-generated method stub
		baseDao.merge(model);
	}

	@Override
	public List<M> next(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.next(pk, pn, pageSize);
	}

	@Override
	public List<M> propertyIsNullList(String propertyName) {
		// TODO Auto-generated method stub
		return baseDao.propertyIsNullList(propertyName);
	}

	@Override
	public List<M> pre(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.pre(pk, pn, pageSize);
	}

	@Override
	public PK save(M model) {
		System.out.println(model.toString());
		return baseDao.save(model);
	}

	@Override
	public void saveOrUpdate(M model) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(model);
	}

	@Override
	public void update(M model) {
		// TODO Auto-generated method stub
		baseDao.update(model);
	}

	@Override
	public int getMax() {
		return baseDao.getMax();
	}
	
	@Override
	public List<M> getAroundList(String idName, int startId, int endId) {
		// TODO Auto-generated method stub
		return baseDao.getAroundList(idName, startId, endId);
	}
}