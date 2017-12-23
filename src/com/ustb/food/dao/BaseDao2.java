package com.ustb.food.dao;



import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ustb.food.entity.Yuanliao;

public interface  BaseDao2<M extends java.io.Serializable, PK extends java.io.Serializable> extends BaseDao<M,PK>{
	//boolean 为false时是每100g原料所含营养成分，为ture时是原料所含营养成分的总和
	public M calculate(M m,List<Yuanliao> ml,List<String>al,boolean boo) throws SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException; 
	public List<Integer> format(List<String> idl);
}
