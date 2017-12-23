package com.ustb.food.service;

import java.util.Map;

import com.ustb.food.entity.Perdietcm;

public interface PerdietcmService extends BaseService< Perdietcm,Integer>{
	public Map findbytime(int userId);
}
