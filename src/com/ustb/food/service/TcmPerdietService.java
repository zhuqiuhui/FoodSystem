package com.ustb.food.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ustb.food.entity.TcmPerdiet;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月1日 下午5:34:58
 */
public interface TcmPerdietService extends BaseService<TcmPerdiet,Integer>{
	
	public List<TcmPerdiet> findbytime(int userId, String insertdate);
	
	public Map<Date, List<TcmPerdiet>> findbytime(int userId, String startDate, String endDate);
}
