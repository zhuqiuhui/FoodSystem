package com.ustb.food.dao;

import java.util.Map;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.entity.Perdietcm;

public interface PerdietcmDao  extends BaseDao<Perdietcm, Integer> {
	public Map findbytime(int userId);
}
