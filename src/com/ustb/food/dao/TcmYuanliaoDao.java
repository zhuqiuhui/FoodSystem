package com.ustb.food.dao;

import java.util.List;

import com.ustb.food.entity.TcmYuanliao;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月30日 下午3:33:34
 */
public interface TcmYuanliaoDao extends BaseDao<TcmYuanliao, Integer>{
	
	List<TcmYuanliao> findByName(String name);
	
}
