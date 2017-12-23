package com.ustb.food.dao;

import com.ustb.food.entity.Caipu;

public interface CaipuDao extends BaseDao<Caipu, Integer> {
	@Override
	public int getMax();
}
