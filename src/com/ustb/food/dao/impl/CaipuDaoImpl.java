package com.ustb.food.dao.impl;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.CaipuDao;
import com.ustb.food.entity.Caipu;

@Component("caipuDao")
public class CaipuDaoImpl extends BaseDaoImpl<Caipu, Integer> implements
		CaipuDao {
	@Override
	public int getMax() {
		String hql = "select max(r.viewId) from Caipu r";
		return (Integer) list(hql, 0, 0).get(0);
	}
}
