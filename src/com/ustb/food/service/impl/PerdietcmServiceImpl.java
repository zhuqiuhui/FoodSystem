package com.ustb.food.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.PerdietcmDao;
import com.ustb.food.entity.Perdietcm;
import com.ustb.food.service.PerdietcmService;
@Component("perdietcmService")
public class PerdietcmServiceImpl extends BaseServiceImpl<Perdietcm,Integer> implements PerdietcmService{
	
	@Autowired PerdietcmDao perdietcmDao;

	@Override
	@Resource
	public void setBaseDao(BaseDao<Perdietcm, Integer> perdietcmDao) {
		// TODO Auto-generated method stub
		this.baseDao = perdietcmDao;
	}

	@Override
	public Map findbytime(int userId) {
		// TODO Auto-generated method stub
		return perdietcmDao.findbytime(userId);
	}


}
