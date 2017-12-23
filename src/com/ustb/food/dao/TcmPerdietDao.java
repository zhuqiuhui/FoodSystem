package com.ustb.food.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ustb.food.entity.TcmPerdiet;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月1日 下午5:38:37
 */
public interface TcmPerdietDao extends BaseDao<TcmPerdiet, Integer> {
	
	public List<TcmPerdiet> findbytime(int userId,String insertdate);
	
	public Map<Date, List<TcmPerdiet>> findbytime(int userId,String startDate,String endDate);
}
