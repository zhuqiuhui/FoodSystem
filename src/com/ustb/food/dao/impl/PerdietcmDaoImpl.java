package com.ustb.food.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.impl.BaseDaoImpl;

import com.ustb.food.dao.PerdietcmDao;
import com.ustb.food.entity.Perdietcm;

@Component("perdietcmDao")
public class PerdietcmDaoImpl  extends BaseDaoImpl<Perdietcm, Integer> implements PerdietcmDao {

	@Override
	public Map findbytime(int userId) {
		// TODO Auto-generated method stub
		Map perdietcmMap = new HashMap();
		String hql = "select insertdate from Perdietcm WHERE userId="+userId+" GROUP BY insertdate";
		List<Date> insertdates = list(hql);		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Perdietcm> perdietcms = new ArrayList<Perdietcm>();
		for(int i=0;i<insertdates.size();i++){
			String otherhql;	
			otherhql = "FROM Perdietcm WHERE insertdate='"+insertdates.get(i)+"' and userId="+userId;
			perdietcms = list(otherhql);
			perdietcmMap.put(insertdates.get(i), perdietcms);
		}
		return perdietcmMap;
	}
	public static void main(String[] args) {
		Map map = new PerdietDaoImpl().findbytime(1);
		System.out.println(map.size());
	}
}
