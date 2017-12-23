package com.ustb.food.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.MaterialDao;
import com.ustb.food.entity.Material;
import com.ustb.food.service.MaterialService;
@Component("materialService")
public class MaterialServiceImpl extends BaseServiceImpl<Material,Integer> implements MaterialService{
	
	@Autowired MaterialDao materialDao;
	@Override
	
	@Resource
	public void setBaseDao(BaseDao<Material, Integer> materialDao) {
		// TODO Auto-generated method stub
		this.baseDao =  materialDao;
	}

}
