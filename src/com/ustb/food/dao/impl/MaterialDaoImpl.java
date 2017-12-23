package com.ustb.food.dao.impl;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.impl.BaseDaoImpl;

import com.ustb.food.dao.MaterialDao;
import com.ustb.food.entity.Material;

@Component("materialDao")
public class MaterialDaoImpl  extends BaseDaoImpl<Material, Integer> implements MaterialDao {

	

}
