package com.ustb.food.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.TcmCaipuDao;
import com.ustb.food.entity.TcmCaipu;
import com.ustb.food.service.TcmCaipuService;

@Component("tcmCaipuService")
public class TcmCaipuServiceImpl extends BaseServiceImpl<TcmCaipu, Integer> implements TcmCaipuService {

	@Resource
	private TcmCaipuDao tcmCaipuDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<TcmCaipu, Integer> tcmCaipuDao) {
		this.baseDao = tcmCaipuDao;
	}
	
}
