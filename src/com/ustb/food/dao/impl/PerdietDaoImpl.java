package com.ustb.food.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.PerdietDao;
import com.ustb.food.entity.Perdiet;
import com.ustb.food.entity.Perrecord;

@Component("perdietDao")
public class PerdietDaoImpl extends  BaseDao2Impl<Perdiet,Integer> implements PerdietDao {

	@Override
	public Map findbytime(int userId) {
		// TODO Auto-generated method stub
		//Map perdietMap = new HashMap();
		Map perdietMap = new HashMap();
		String hql = "select insertdate from perdiet WHERE userId="+userId+" GROUP BY insertdate";
		List<Date> insertdates = list(hql);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Perdiet> perdiets = new ArrayList<Perdiet>();
		for(int i=0;i<insertdates.size();i++){

			String otherhql;	
			otherhql = "FROM perdiet WHERE insertdate='"+insertdates.get(i)+"' and userId="+userId;
			perdiets = list(otherhql);
			perdietMap.put(insertdates.get(i), perdiets);
		}
		return perdietMap;		
	}

	@Override
	//计算菜谱的全部营养成分
	public TreeMap jisuanMap(Map perdietMap) {
		TreeMap sumMap = new TreeMap();
		Double calories = 0.0;
		Double carbohydrate = 0.0;
		Double fat = 0.0;
		Double protein = 0.0;
		Double vitamine = 0.0;
		Double vta = 0.0;
		Double vtc = 0.0;
		Double vte = 0.0;
		Double carotene = 0.0;
		Double thiamine = 0.0;
		Double riboflavin = 0.0;
		Double yansuan = 0.0;
		Double cholesterol = 0.0;
		Double mg = 0.0;
		Double ca = 0.0;
		Double iron = 0.0;
		Double zinc = 0.0;
		Double copper = 0.0;
		Double mn = 0.0;
		Double k = 0.0;
		Double p = 0.0;
		Double na = 0.0;
		Double se = 0.0;
		Iterator it = perdietMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			List<Perdiet> perdiets = (List<Perdiet>) entry.getValue();
			Date key = (Date) entry.getKey();
			calories = 0.0;
			carbohydrate = 0.0;
			fat = 0.0;
			protein = 0.0;
			na = 0.0;
			for (int i = 0; i < perdiets.size(); i++) {
				calories += perdiets.get(i).getCalories();
				carbohydrate += perdiets.get(i).getCarbohydrate();
				fat += perdiets.get(i).getFat();
				protein += perdiets.get(i).getProtein();
				vitamine = perdiets.get(i).getVitamine();
				vta = perdiets.get(i).getVta();
				vtc = perdiets.get(i).getVtc();
				vte = perdiets.get(i).getVte();
				carotene = perdiets.get(i).getCarotene();
				thiamine = perdiets.get(i).getThiamine();
				riboflavin = perdiets.get(i).getRiboflavin();
				yansuan = perdiets.get(i).getYansuan();
				cholesterol = perdiets.get(i).getCholesterol();
				mg = perdiets.get(i).getMg();
				ca = perdiets.get(i).getCa();
				iron = perdiets.get(i).getIron();
				zinc = perdiets.get(i).getZinc();
				copper = perdiets.get(i).getCopper();
				mn = perdiets.get(i).getMn();
				k = perdiets.get(i).getK();
				p = perdiets.get(i).getP();
				na = perdiets.get(i).getNa();
				se = perdiets.get(i).getSe();
				na += perdiets.get(i).getNa();
			}
			Perdiet sumperdiet = new Perdiet();
			sumperdiet.setInsertdate(key);
			sumperdiet.setCalories(calories);
			sumperdiet.setCarbohydrate(carbohydrate);
			sumperdiet.setFat(fat);
			sumperdiet.setProtein(protein);
			sumperdiet.setVitamine(vitamine);
			sumperdiet.setVta(vta);
			sumperdiet.setVtc(vtc);
			sumperdiet.setVte(vte);
			sumperdiet.setNa(na);
			sumperdiet.setCarotene(carotene);
			sumperdiet.setThiamine(thiamine);
			sumperdiet.setRiboflavin(riboflavin);
			sumperdiet.setYansuan(yansuan);
			sumperdiet.setCholesterol(cholesterol);
			sumperdiet.setMg(mg);
			sumperdiet.setCa(ca);
			sumperdiet.setIron(iron);
			sumperdiet.setZinc(zinc);
			sumperdiet.setCopper(copper);
			sumperdiet.setMn(mn);
			sumperdiet.setK(k);
			sumperdiet.setP(p);
			sumperdiet.setSe(se);
			sumMap.put(key, sumperdiet);

		}
		return sumMap;
	}

	/**
	 * 已经修改正常
	 */
	@Override
	public Map<Date, List<Perdiet>> findbytime(int userId, String startDate, String endDate) {
		Map<Date, List<Perdiet>> perdietMap = new HashMap<Date, List<Perdiet>>();
		String hql = "select insertdate from Perdiet WHERE userId="+userId+" AND insertdate BETWEEN '"+startDate+"' AND '"+endDate+"' GROUP BY insertdate";
		List<Date> insertdates = list(hql, 0, 0);	
		List<Perdiet> perdiets = new ArrayList<Perdiet>();
		for(int i=0;i<insertdates.size();i++){
			String findHql = "FROM Perdiet WHERE insertdate='"+insertdates.get(i)+"' and userId="+userId;
			perdiets = list(findHql, 0, 0);
			perdietMap.put(insertdates.get(i), perdiets);
		}
		return perdietMap;
	}
	
	@Override
	public TreeMap findbytime_c(int userId, String startDate, String endDate) {
		TreeMap perdietMap = new TreeMap();
		String hql = "select insertdate from perdiet WHERE userId="+userId+" AND insertdate BETWEEN '"+startDate+"' AND '"+endDate+"' GROUP BY insertdate";
		List<Date> insertdates = list(hql);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Perdiet> perdiets = new ArrayList<Perdiet>();
		for(int i=0;i<insertdates.size();i++){

			String otherhql;	
			otherhql = "FROM perdiet WHERE insertdate='"+insertdates.get(i)+"' and userId="+userId;
			perdiets = list(otherhql);
			perdietMap.put(insertdates.get(i), perdiets);
		}
		return perdietMap;
	}
	
	
	/**
	 * 已经修改正常
	 */
	@Override
	public List<Perdiet> findbytime(int userId,String insertdate) {
		List<Perdiet> perdiets = new ArrayList<Perdiet>();
		String hql = "FROM Perdiet WHERE userId="+userId+" AND insertdate = '"+insertdate+"'";
		perdiets = list(hql, 0, 0);
		return perdiets;
	}
	
	@Override
	public List retrieveAndDelete(int userId,String insertdate) {
		// TODO Auto-generated method stub
		List<Perdiet> perdiets = new ArrayList<Perdiet>();
		String hql = "FROM perdiet WHERE userId="+userId+" AND insertdate = '"+insertdate+"'";
		perdiets = list(hql);
		return perdiets;
	}
}
