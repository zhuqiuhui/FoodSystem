package com.ustb.food.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.TcmGuanxiDao;
import com.ustb.food.entity.TcmGuanxi;
import com.ustb.food.service.TcmGuanxiService;

@Component("tcmGuanxiService")
public class TcmGuanxiServiceImpl extends BaseServiceImpl<TcmGuanxi, Integer> implements TcmGuanxiService {
	@Resource
	private TcmGuanxiDao tcmGuanxiDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<TcmGuanxi, Integer> tcmGuanxiDao) {
		this.baseDao = tcmGuanxiDao;
	}
}
