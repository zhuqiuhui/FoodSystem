package com.ustb.food.tool;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.Connection;
/*
 *  是计算某种原料在菜谱中与其他原料出现次数的统计。
 * （完整：考虑他们是否属于一大类，和去除调料的问题）
  */
public class newCountNum {
	public void count(int maId) throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.getConnection();
		List sameId = new findSame().findSameId(maId);
		//得到对应maId所对应的名字
		String name=new findSame().name(maId);
		ArrayList list2 = new ArrayList();
		int num;//表示循环的次数，用于解决在大类型表中没有找到类型
		//如何处理循环次数的问题
		if(sameId.size()==0){
			 num=1;
		}else{
			 num=sameId.size();
		}
		for(int i=0;i<num;i++){
		Statement statement = connection.createStatement();
		String sql1;
		if(sameId.size()==0){
			 sql1="select viewId from guanxi where maId="+maId+"";
		}else{
			 sql1="select viewId from guanxi where maId="+sameId.get(i)+"";
		}
    	ResultSet set2 = statement.executeQuery(sql1);
   	 	while(set2.next()){
   		 int caipuId=set2.getInt(1);
   		 String sql2="select maId from guanxi where viewId="+caipuId+"";
   		 Statement st=connection.createStatement();
   		 ResultSet set3 = st.executeQuery(sql2);
   		 while(set3.next()){
   			 //list2是得到了所有与羊肉匹配的元素的集合，不管是否重复。
   			int MaId=set3.getInt(1);
   			Statement sts=connection.createStatement();
   			if(sameId.contains(MaId)){
   				continue;
   			}else{
   			String sql3="select mName from yuanliao where maId="+MaId+"";
      		ResultSet set4 = sts.executeQuery(sql3);
      		while(set4.next()){
      			String mName = set4.getString(1);
      			list2.add(mName);
      		}
   			}
   		 }
   	 }
	}
	
   	 //去除作料 （list2是全部原料的集合）
   	 List partList = new DelTiaoLiao().deleteTiaoLiao(list2);
   	 //partList是去除完作料的集合
   	 //将list中出现重复的值去除
   	 Set<String> uniqueSet1 = new HashSet<String>(partList);  
   	 System.out.println("uniqueSet:"+uniqueSet1);
		 //统计list中出现相同元素的个数。
		 HashMap<String,Integer> map1 = new HashMap<String,Integer>();
		 HashMap hashmap = new HashMap();
	        for (String temp : uniqueSet1) {
	        	int j = Collections.frequency(list2, temp);
	            map1.put(temp, j);
	            Statement stsn=connection.createStatement();
	            String sql="select type from yuanliao where mName='"+temp+"'";
	            ResultSet set = stsn.executeQuery(sql);
	            while(set.next()){
	      			String type = set.getString(1);
	      			hashmap.put(temp, type);
	      		}
	        }
	        
	        Set<String> keys = map1.keySet();      // 得到全部的key变成Set集合  
	        Iterator<String> iter = keys.iterator();      // 实例化Iterator对象  
	        while (iter.hasNext()){  
	            String key = iter.next();   // 取出key  
	            Integer value = map1.get(key);
	            if(value>=3){
	            System.out.println(name+"\t"+key+"\t"+value);
	            }// 输出key  
	        } 
		TxtWriter writer = new TxtWriter();
		writer.Writer(map1,hashmap, name);
	}
	public static void main(String[] args) throws Exception {
		 new newCountNum().count(513);//在findSame中可以找到大类别的原料Id羊肉
		 new newCountNum().count(434);//在findSame中可以找到大类别的原料Id猪肉
		 new newCountNum().count(1641);//在findSame中可以找到大类别的原料Id牛肉
		 new newCountNum().count(180);//在findSame中可以找到大类别的原料Id鸡肉
		// new newCountNum().count(62);//在findSame中可以找不到大类别的原料Id
	}
}
