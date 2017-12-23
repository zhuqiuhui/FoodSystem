package com.ustb.food.dao;

import java.util.List;

import com.ustb.food.entity.Caipuauc;

public interface CaipuaucDao extends BaseDao<Caipuauc, Integer> {
	List<Caipuauc> getBestPerdietAucList(String paraName1, float v1, String paraName2, float v2, String paraName3, float v3);
}
