package com.ustb.food.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.PerrecordDao;
import com.ustb.food.entity.Perdiet;
import com.ustb.food.entity.Perrecord;
import com.ustb.food.service.PerrecordService;
@Component("perrecordService")
public class PerrecordServiceImpl extends BaseServiceImpl<Perrecord,Integer> implements PerrecordService{

	@Autowired PerrecordDao perrecordDao;

	@Override
	@Resource
	public void setBaseDao(BaseDao<Perrecord, Integer> perrecordDao) {
		// TODO Auto-generated method stub
		this.baseDao = perrecordDao;
	}

	@Override
	public Map findbytime(int userId) {
		// TODO Auto-generated method stub
		return perrecordDao.findbytime(userId);
	}

	@Override
	public List<Perrecord> perrecordList(int userId, String insertDate) {
		// TODO Auto-generated method stub
		return perrecordDao.perrecordList(userId, insertDate);
	}

	@Override
	public Perdiet calculate(List<Perrecord> perrecordList)
			throws SecurityException, NoSuchMethodException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return perrecordDao.calculate(perrecordList);
	}

	@Override
	public List<Perrecord> perrecordListAll(int userId) {
		// TODO Auto-generated method stub
		return perrecordDao.perrecordListAll(userId);
	}

	@Override
	public Map<Date, List<String>> getAll(int userId) {
		// TODO Auto-generated method stub
		return perrecordDao.getAll(userId);
	}

}
