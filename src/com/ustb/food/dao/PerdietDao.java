package com.ustb.food.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ustb.food.entity.Perdiet;

public interface PerdietDao extends BaseDao2<Perdiet,Integer> {
	
	/**
	 * 根据 userId 和 日期 查询个人饮食
	 * @author zhuqiuhui
	 * @param userId
	 * @param insertdate
	 * @return
	 */
	public List<Perdiet> findbytime(int userId,String insertdate);
	
	
	public Map findbytime(int userId);
	public TreeMap jisuanMap(Map perdietMap);
	public Map<Date, List<Perdiet>> findbytime(int userId,String startDate,String endDate);
	
	public List retrieveAndDelete(int userId,String insertdate);
	public TreeMap findbytime_c(int userId, String startDate, String endDate);
}
