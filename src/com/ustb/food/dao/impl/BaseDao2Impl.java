package com.ustb.food.dao.impl;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.ustb.food.dao.BaseDao2;
import com.ustb.food.entity.Yuanliao;



public class BaseDao2Impl<M extends java.io.Serializable, PK extends java.io.Serializable> extends BaseDaoImpl<M,PK> implements BaseDao2<M,PK> {
	
	@Override
	public  M calculate(M m,List<Yuanliao> ml,List<String> al,boolean boo) throws SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		//ID List idl 强制转换为 int
		List<Double> alDou = new ArrayList<Double>();
		//转化List中数据类型
		for(int i = 0;i<al.size();i++){
			double b = Double.parseDouble(al.get(i));
			alDou.add(b);
		}
		double sum=0; double asum =0;
		String[] name = new String[]{"calories","carbohydrate","fat","protein","vitamine","vta",
				"vtc","vte","carotene","thiamine","riboflavin","yansuan","cholesterol","mg",
				"ca","iron","zinc","copper","mn","k","p","na","se"};
		//分别得到get与set的方法名字
		List<String> getmethodName = getMethodName(name);
		List<String> setmethodName = setMethodName(name);
		
		//原料总用量
		for(int i = 0 ;i<al.size();i++){
			 asum+=alDou.get(i);			 
		 }
		
//		M obj = (M) m.getClass().newInstance();

		//计算营养成分的总量
		for(int j=0;j<getmethodName.size();j++){
			
			Method setmethod =m.getClass().getMethod(setmethodName.get(j),Double.class);
			
			//营养成分含量
			
			//结果
			Double a=0.0;
			for(int i = 0 ;i<al.size();i++){
				Method getmethod =ml.get(i).getClass().getMethod(getmethodName.get(j));
				Double d = (Double) getmethod.invoke(ml.get(i));
				 sum+=(d*alDou.get(i));			 
			 }
			if(boo==false){a=sum/asum;}
			if(boo==true){a=sum/100;}
			setmethod.invoke(m, a);
			sum=0.0;
		}
		return  m;
	}
	//得到get方法的名字
	public List<String> getMethodName(String[] name){
		List<String> methodName = new ArrayList<String>();
		for(int i = 0;i<name.length;i++){
			String str;
			str = "get"+name[i].substring(0,1).toUpperCase()+name[i].substring(1);
			methodName.add(str);
		}
		return methodName;
	}
	//得到set方法的名字
	public List<String> setMethodName(String[] name){
		List<String> methodName = new ArrayList<String>();
		for(int i = 0;i<name.length;i++){
			String str;
			str = "set"+name[i].substring(0,1).toUpperCase()+name[i].substring(1);
			methodName.add(str);
		}
		return methodName;
	}
	
	@Override
	public List<Integer> format(List<String> idl){
		List<Integer> idlInt = new ArrayList<Integer>();
		for(int i = 0;i<idl.size();i++){
			int a = Integer.parseInt(idl.get(i));
			idlInt.add(a);
		}
		return idlInt;
	}
	
}
