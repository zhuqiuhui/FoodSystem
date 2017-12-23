package com.ustb.food.dao;

import java.util.List;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.entity.Yuanliao;

public interface YuanliaoDao extends BaseDao<Yuanliao, Integer> {

	public List<Yuanliao> findSame(String mName);
	public List<Yuanliao> findByName(String mName);
	public List<Yuanliao> findbyList(List<String> nameLike);
	public List<Yuanliao> bohe();
	public List<Yuanliao> standofcountry();
	public List<Yuanliao> findTiaoliao();
}
