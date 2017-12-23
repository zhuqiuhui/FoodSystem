package com.ustb.food.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.TcmPerdietDao;
import com.ustb.food.entity.TcmPerdiet;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月1日 下午5:40:10
 */
@Component("tcmPerdietDao")
public class TcmPerdietDaoImpl  extends BaseDaoImpl<TcmPerdiet, Integer> implements TcmPerdietDao {

	@Override
	public List<TcmPerdiet> findbytime(int userId, String insertdate) {
		List<TcmPerdiet> tcmPerdiets = new ArrayList<TcmPerdiet>();
		String hql = "FROM TcmPerdiet WHERE userId="+userId+" AND insertdate = '"+insertdate+"'";
		tcmPerdiets = list(hql, 0, 0);
		return tcmPerdiets;
	}

	@Override
	public Map<Date, List<TcmPerdiet>> findbytime(int userId, String startDate, String endDate) {
		Map<Date, List<TcmPerdiet>> tcmPerdietMap = new HashMap<Date, List<TcmPerdiet>>();
		String hql = "select insertdate from TcmPerdiet WHERE userId="+userId+" AND insertdate BETWEEN '"+startDate+"' AND '"+endDate+"' GROUP BY insertdate";
		List<Date> insertdates = list(hql, 0, 0);	
		List<TcmPerdiet> tcmPerdiets = new ArrayList<TcmPerdiet>();
		for(int i=0;i<insertdates.size();i++){
			String findHql = "FROM TcmPerdiet WHERE insertdate='"+insertdates.get(i)+"' and userId="+userId;
			tcmPerdiets = list(findHql, 0, 0);
			tcmPerdietMap.put(insertdates.get(i), tcmPerdiets);
		}
		return tcmPerdietMap;
	}
}
