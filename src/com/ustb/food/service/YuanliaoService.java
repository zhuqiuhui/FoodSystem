package com.ustb.food.service;

import java.util.List;

import com.ustb.food.entity.Yuanliao;

public interface YuanliaoService extends BaseService<Yuanliao,Integer>{
	public List<Yuanliao> findSame(String mName);
	public List<Yuanliao> findByName(String mName);
	public List<Yuanliao> findbyList(List<String> nameLike);
	public List<Yuanliao> bohe();
	public List<Yuanliao> standofcountry();
	public List<Yuanliao> findTiaoliao();
}
