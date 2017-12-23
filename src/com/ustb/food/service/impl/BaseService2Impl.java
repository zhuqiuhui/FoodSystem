package com.ustb.food.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ustb.food.dao.BaseDao2;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.BaseService2;





public abstract class BaseService2Impl<M extends java.io.Serializable, PK extends java.io.Serializable> extends BaseServiceImpl<M,PK> implements BaseService2<M,PK>{
	BaseDao2<M,Integer> baseDao2;
	public abstract void setBaseDao(BaseDao2<M,Integer> baseDao2);
	@Override
	public M calculate(M m, List<Yuanliao> ml, List<String> al,boolean boo)
			throws SecurityException, NoSuchMethodException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return baseDao2.calculate(m, ml, al,boo);
	}
	
	@Override
	public List<Integer> format(List<String> idl){
		return baseDao2.format(idl);
	}
	

}