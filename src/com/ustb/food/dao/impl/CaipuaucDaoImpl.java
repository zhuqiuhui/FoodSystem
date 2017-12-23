package com.ustb.food.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.CaipuaucDao;
import com.ustb.food.entity.Caipuauc;

@Component("caipuaucDao")
public class CaipuaucDaoImpl extends BaseDaoImpl<Caipuauc, Integer> implements
		CaipuaucDao {

	@Override
	public List<Caipuauc> getBestPerdietAucList(String paraName1, float v1, String paraName2, float v2,
			String paraName3, float v3) {
		String hql = "from Caipuauc as model where model."
				+ paraName1 + ">= ? and model." + paraName2 + ">= ? and model." + paraName3 +">=? order by id desc";
		return list(hql, 0, 0, v1, v2, v3);
	}
}
