package com.ustb.food.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.GuanxiDao;
import com.ustb.food.entity.Guanxi;

@Component("guanxiDao")
public class GuanxiDaoImpl extends BaseDaoImpl<Guanxi, Integer> implements
		GuanxiDao {

	@Override
	public List<Guanxi> getC(int viewId, int maId) {
		String hql = "from guanxi where viewId=" + viewId + " and maId=" + maId
				+ "";
		return list(hql, 0, 0);
	}
}
