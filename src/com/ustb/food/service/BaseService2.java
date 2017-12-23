package com.ustb.food.service;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ustb.food.entity.Yuanliao;

public interface BaseService2<M extends java.io.Serializable, PK extends java.io.Serializable> extends BaseService<M,PK>{

	public M calculate(M m,List<Yuanliao> ml,List<String>al,boolean boo) throws SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	public List<Integer> format(List<String> idl);
}
