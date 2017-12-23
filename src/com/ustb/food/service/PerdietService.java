package com.ustb.food.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ustb.food.entity.Perdiet;

public interface PerdietService extends BaseService2< Perdiet,Integer>{
	public Map findbytime(int userId);
	public TreeMap jisuanMap(Map perdietMap);
	public Map<Date, List<Perdiet>> findbytime(int userId,String startDate,String endDate);
	//public Map findbytime(int userId,String insertdate);
	public List<Perdiet> findbytime(int userId,String insertdate);
	public List retrieveAndDelete(int userId,String insertdate);
	public TreeMap findbytime_c(int userId, String startDate, String endDate);
}
