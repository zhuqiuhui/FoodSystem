package com.ustb.food.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ustb.food.entity.Perdiet;
import com.ustb.food.entity.Perrecord;

public interface PerrecordDao extends BaseDao<Perrecord, Integer> {
	public Map findbytime(int userId);
	public List<Perrecord> perrecordList(int userId,String insertDate);
	Perdiet calculate(List<Perrecord> perrecordList) throws SecurityException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException;
	public List<Perrecord> perrecordListAll(int userId);
	public Map<Date,List<String>> getAll(int userId);
	public List<Perrecord> getrulePre(int userId, int[] maId);
	List<Perrecord> getrulePre(int userId, Integer[] array);
}

