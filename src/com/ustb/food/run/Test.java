package com.ustb.food.run;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ustb.food.entity.Guanxi;
import com.ustb.food.entity.GuanxiId;

public class Test {

	public static void main(String[] args) {
		GuanxiId gxi1 = new GuanxiId();
//		GuanxiId gxi2 = new GuanxiId();
		gxi1.setMaId(1);
		gxi1.setViewId(1);
//		gxi2.setMaId(1);
//		gxi2.setViewId(1);
		Guanxi g1 =new Guanxi();
		Guanxi g2 =new Guanxi();
		g1.setId(gxi1);
		g2.setId(gxi1);
		System.out.println(g1.equals(g2));
		
		Set<Guanxi> guanxiSet = new HashSet<Guanxi>();
		guanxiSet.add(g1);
		guanxiSet.add(g2);
		
		System.out.println(guanxiSet.size());
		
		Map<Guanxi,String> guanxiMap = new HashMap<Guanxi,String>();
		guanxiMap.put(g1, "t");
		guanxiMap.put(g2, "t");
		System.out.println(guanxiMap.size());
		
		String str1 = new String("1");
		String str2 = new String("1");
		Set<String> strSet = new HashSet<String>();
		strSet.add(str1);
		strSet.add(str2);
		System.out.println(strSet.size());
	}
}
