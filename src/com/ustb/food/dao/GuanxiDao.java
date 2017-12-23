package com.ustb.food.dao;

import java.util.List;

import com.ustb.food.entity.Guanxi;

public interface GuanxiDao extends BaseDao<Guanxi, Integer> {
	public List<Guanxi> getC(int viewId, int maId);
}
