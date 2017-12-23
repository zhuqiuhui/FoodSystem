package com.ustb.food.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/*
 * 是计算某种原料在菜谱中与其他原料出现次数的统计。
 * （不完整：没有考虑他们是否属于一大类，和去除调料的问题）
 * 完整的代码是newCountNum类
 * */
public class countNum {
	public static void main(String[] args) {
		String driverClassName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/FoodS";
		String user="root";
		String password="940801";
		//表示list中元素重复的次数
		int pinglv=100;
		Connection connection=null;
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			String sql="select guanxi.maId from guanxi,yuanliao where type<>'调味品类'and type<>'油脂类' and type<>'糖、蜜饯类' and type<>'软饮料' and guanxi.maId=yuanliao.maId";
			ResultSet set = statement.executeQuery(sql);
			ArrayList list = new ArrayList();
			//将所有出现的原料全部放入list中
			while(set.next()){
				list.add(set.getInt(1));
			}
			//得到list中的唯一项
			 Set<Integer> uniqueSet = new HashSet<Integer>(list);  
			 //得到每一项的出现的次数，若次数大于100，则将其放入map中，key为原料，value为原料出现的次数
			 //统计list中出现相同元素的个数。
			 ArrayList yuaniaoId = new ArrayList();
		        for (int temp : uniqueSet) {
		        	int i = Collections.frequency(list, temp);
		        	if(i>pinglv){
		            list.add(temp);
		        	}
		        }
			//System.out.println(map.size());
		     Statement statement1 = connection.createStatement();
		     ArrayList list2 = null;
		    // for(int i=0;i<list.size();i++){
		    	 //得到每一种原料的所在菜谱。。
		    	 //String sql1="select viewId from guanxi where maId="+list.get(i)+"";
		    	 String sql1="select viewId from guanxi where maId=513";
		    	 ResultSet set2 = statement1.executeQuery(sql1);
		    	 list2 = new ArrayList();
		    	 while(set2.next()){
		    		 int caipuId=set2.getInt(1);
		    		 System.out.println(caipuId);
		    		 String sql2="select maId from guanxi where viewId="+caipuId+"";
		    		 Statement st=connection.createStatement();
		    		 ResultSet set3 = st.executeQuery(sql2);
		    		 while(set3.next()){
		    			 list2.add(set3.getInt(1));
		    		 }
		    	 }
		    	 //list2是得到了所有与羊肉匹配的元素的集合，不管是否重复。
		    	 Set<Integer> uniqueSet1 = new HashSet<Integer>(list2);  
    			 //得到每一项的出现的次数，若次数大于100，则将其放入map中，key为原料，value为原料出现的次数
    			 //统计list中出现相同元素的个数。
    			 HashMap map1 = new HashMap();
    			 HashMap map2 = new HashMap();
    		        for (int temp : uniqueSet1) {
    		        	int j = Collections.frequency(list2, temp);
    		            map1.put(temp, j);
    		            //map2.put(list.get(i), map1);
    		            map2.put(4, map1);
    		        }
    		        Set<Integer> keys = map1.keySet();      // 得到全部的key变成Set集合  
    		        Iterator<Integer> iter = keys.iterator();      // 实例化Iterator对象  
    		  
    		        while (iter.hasNext()){  
    		            Integer key = iter.next();   // 取出key  
    		            Integer value = (Integer)map1.get(key);
    		            System.out.println(key+" "+value);    // 输出key  
    		        } 
		    	 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(connection!= null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}
	}
	
}
