package com.ustb.food.tool;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
   public static void main(String[] args) {
	   Map m = new HashMap();   
	    m.put("东莞", "11");
	    m.put("北京", "北京");
	    m.put("东莞", "东莞");
	    m.put("北京", "北京");
	    m.put("上海", "上海");
	    m.put("北京", "北京");
	    m.put("上海", "上海");
	    for(Object o : m.keySet()){   
	       System.out.println("HashMap 输出随机*"+m.get(o));  
	     }
	    System.out.println(m.size());
}
}
