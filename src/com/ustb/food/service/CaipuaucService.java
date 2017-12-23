package com.ustb.food.service;

import java.util.List;

import com.ustb.food.entity.Caipuauc;

public interface CaipuaucService extends BaseService<Caipuauc, Integer> {
	
	List<Caipuauc> getBestPerdietAucList(String paraName1, float v1, String paraName2, float v2, String paraName3, float v3);
}
