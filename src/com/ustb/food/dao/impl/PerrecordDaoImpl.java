package com.ustb.food.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.mail.internet.PreencodedMimeBodyPart;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.PerrecordDao;
import com.ustb.food.entity.Perdiet;
import com.ustb.food.entity.Perrecord;

@Component("perrecordDao")
public class PerrecordDaoImpl extends BaseDaoImpl<Perrecord, Integer> implements PerrecordDao{

	@Override
	public Map findbytime(int userId) {
		Map perrecordMap = new HashMap();
		String hql = "select insertdate from Perrecord WHERE userId="+userId+" GROUP BY insertdate";
		List<Date> insertdates = list(hql);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Perrecord> perrecords = new ArrayList<Perrecord>();
		for(int i=0;i<insertdates.size();i++){

			String otherhql;	
			otherhql = "FROM Perrecord WHERE insertdate='"+insertdates.get(i)+"' and userId="+userId;
			perrecords = list(otherhql);
			perrecordMap.put(insertdates.get(i), perrecords);
		}
		return perrecordMap;
	}
	@Override
	public List<Perrecord> perrecordList(int userId,String insertDate){
		List<Perrecord> perrecordList = new ArrayList<Perrecord>();
		String hql = "FROM Perrecord WHERE insertdate='"+insertDate+"' and userId="+userId;
		perrecordList=list(hql);
		return perrecordList;
	}
	
	@Override
	public List<Perrecord> perrecordListAll(int userId){
		List<Perrecord> perrecordList = new ArrayList<Perrecord>();
		String hql = "FROM Perrecord WHERE userId="+userId;
		perrecordList=list(hql);
		return perrecordList;
	}
	
	@Override
	public Map<Date, List<String>> getAll(int userId) {
		String hql = "FROM Perrecord WHERE userId="+ userId+" ORDER BY insertdate";
		Map<Date, List<String>> map = new HashMap<Date, List<String>>();
		List<Perrecord> perrecordList = new ArrayList<Perrecord>();
		perrecordList = list(hql);
		List<String> nameList = new ArrayList<String>();
		Date date = null;
		for(int i = 0;i<perrecordList.size();i++){
			Perrecord perrecord = perrecordList.get(i);
			if(i!=0&&!perrecord.getInsertdate().equals(date) ){
				map.put(date, nameList);
				nameList = new ArrayList<String>();
			}
			nameList.add(perrecord.getYuanliao().getmName());
			 date = perrecord.getInsertdate();
			 if(i==perrecordList.size()-1){
				 map.put(date, nameList);
			 }
		}
		return map;
	}
	
	@Override
	public Perdiet calculate(List<Perrecord> perrecordList) throws SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Perdiet perdiet = new Perdiet();
		double sum=0; 
		double asum =0;
		String[] name = new String[]{"calories","carbohydrate","fat","protein","vitamine","vta",
				"vtc","vte","carotene","thiamine","riboflavin","yansuan","cholesterol","mg",
				"ca","iron","zinc","copper","mn","k","p","na","se"};
		//分别得到get与set的方法名字
		List<String> getmethodName = getMethodName(name);
		List<String> setmethodName = setMethodName(name);
		//原料总用量
		for(int i = 0 ;i<perrecordList.size();i++){
			 asum=+perrecordList.get(i).getAmount();			 
		 }
		//计算营养成分的总量
		for(int j=0;j<getmethodName.size();j++){
			
			Method setmethod =perdiet.getClass().getMethod(setmethodName.get(j),Double.class);
			
			//营养成分含量
			
			//结果
			Double a=0.0;
			for(int i = 0 ;i<perrecordList.size();i++){
				Method getmethod =perrecordList.get(i).getYuanliao().getClass().getMethod(getmethodName.get(j));
				Double d = (Double) getmethod.invoke(perrecordList.get(i).getYuanliao());
				 sum+=(d*perrecordList.get(i).getAmount());			 
			 }
			a=sum/100;
			setmethod.invoke(perdiet, a);
			sum=0.0;
		}
		return  perdiet;
	}
	//得到get方法的名字
		private List<String> getMethodName(String[] name){
			List<String> methodName = new ArrayList<String>();
			for(int i = 0;i<name.length;i++){
				String str;
				str = "get"+name[i].substring(0,1).toUpperCase()+name[i].substring(1);
				methodName.add(str);
			}
			return methodName;
		}
	//得到set方法的名字
		private List<String> setMethodName(String[] name){
			List<String> methodName = new ArrayList<String>();
			for(int i = 0;i<name.length;i++){
				String str;
				str = "set"+name[i].substring(0,1).toUpperCase()+name[i].substring(1);
				methodName.add(str);
			}
			return methodName;
		}
		
		@Override
		public List<Perrecord> getrulePre(int userId,int ...array){
			List<Perrecord> perrecordList = new ArrayList<Perrecord>();
			for(int i=0;i<=array.length;i++){
			String hql = "FROM Perrecord WHERE userId="+ userId+"and name[i]";
			perrecordList = list(hql);
			}
			return perrecordList;
		}
		@Override
		public List<Perrecord> getrulePre(int userId, Integer[] array) {
			// TODO Auto-generated method stub
			List<Perrecord> perrecordList = new ArrayList<Perrecord>();
			//通过每一种原料，找到所有包含这个原料的子记录，最后求得其平均值作为构建新子记录的用量总和
			for(int i=0;i<array.length;i++){
			String hql = "FROM Perrecord WHERE userId="+ userId+"and maId="+array[i]+"";
			List<Perrecord> list = new ArrayList<Perrecord>();
			list = list(hql);
			int sum=0;
			for(int j=0;j<list.size();j++){
				sum+=list.get(j).getAmount();
			}
			sum=sum/list.size();
			Perrecord perrecord = new Perrecord();
			perrecord.setAmount(sum);
			perrecord.setInsertdate(new Date());
			perrecord.setUsers(list.get(0).getUsers());
			perrecord.setYuanliao(list.get(0).getYuanliao());
			perrecordList.add(perrecord);
			}
			return perrecordList;
		}
		
}
