package com.ustb.food.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.CaipuaucDao;
import com.ustb.food.entity.Caipuauc;
import com.ustb.food.service.CaipuaucService;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月30日 下午3:47:36
 */
@Component("caipuaucService")
public class CaipuaucServiceImpl extends BaseServiceImpl<Caipuauc, Integer> implements CaipuaucService {

	@Autowired
	CaipuaucDao caipuaucDao;
	
	@Override
	@Resource
	public void setBaseDao(BaseDao<Caipuauc, Integer> caipuaucDao) {
		this.baseDao = caipuaucDao;
	}

	@Override
	public List<Caipuauc> getBestPerdietAucList(String paraName1, float v1, String paraName2, float v2,
			String paraName3, float v3) {
		return caipuaucDao.getBestPerdietAucList(paraName1, v1, paraName2, v2, paraName3, v3);
	}

}
