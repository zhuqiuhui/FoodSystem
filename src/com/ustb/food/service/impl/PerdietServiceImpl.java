package com.ustb.food.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.BaseDao2;
import com.ustb.food.dao.PerdietDao;
import com.ustb.food.entity.Perdiet;
import com.ustb.food.service.PerdietService;
@Component("perdietService")
public class PerdietServiceImpl extends BaseService2Impl<Perdiet,Integer> implements PerdietService{
	
	@Autowired PerdietDao perdietDao;

	@Override
	@Resource
	public void setBaseDao(BaseDao2<Perdiet,Integer> perdietDao) {
		// TODO Auto-generated method stub
		this.baseDao2 = perdietDao;
	}
	@Override
	@Resource
	public void setBaseDao(BaseDao<Perdiet,Integer> perdietDao) {
		// TODO Auto-generated method stub
		this.baseDao= perdietDao;
	}

	@Override
	public Map findbytime(int userId) {
		// TODO Auto-generated method stub
		return perdietDao.findbytime(userId);
	}

	@Override
	public TreeMap jisuanMap(Map perdietMap) {
		// TODO Auto-generated method stub
		return perdietDao.jisuanMap(perdietMap);
	}

	@Override
	public Map<Date, List<Perdiet>> findbytime(int userId, String startDate, String endDate) {
		return perdietDao.findbytime(userId, startDate, endDate);
	}
		
	@Override
	public TreeMap findbytime_c(int userId, String startDate, String endDate) {
		return perdietDao.findbytime_c(userId, startDate, endDate);
	}
	
	@Override
	public List<Perdiet> findbytime(int userId, String insertdate) {
		return perdietDao.findbytime(userId, insertdate);
	}

	@Override
	public List retrieveAndDelete(int userId, String insertdate) {
		// TODO Auto-generated method stub
		return perdietDao.retrieveAndDelete(userId, insertdate);
	}





}
