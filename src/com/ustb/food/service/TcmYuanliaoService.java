package com.ustb.food.service;

import java.util.List;

import com.ustb.food.entity.TcmYuanliao;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月30日 下午3:29:40
 */

public interface TcmYuanliaoService extends BaseService<TcmYuanliao,Integer> {
	public List<TcmYuanliao> findByName(String name);
}
