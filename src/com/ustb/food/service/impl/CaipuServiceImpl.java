package com.ustb.food.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.CaipuDao;
import com.ustb.food.entity.Caipu;
import com.ustb.food.service.CaipuService;

@Component("caipuService")
public class CaipuServiceImpl extends BaseServiceImpl<Caipu, Integer> implements
		CaipuService {

	@Autowired
	CaipuDao caipuDao;

	@Override
	@Resource
	public void setBaseDao(BaseDao<Caipu, Integer> caipuDao) {
		this.baseDao = caipuDao;
	}

}
