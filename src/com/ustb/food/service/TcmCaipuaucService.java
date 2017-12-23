package com.ustb.food.service;

import java.util.List;

import com.ustb.food.entity.TcmCaipuauc;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月2日 下午5:33:36
 */
public interface TcmCaipuaucService extends BaseService<TcmCaipuauc,Integer> {
	
	List<TcmCaipuauc> getBestTcmPerdietAucList(String paraName1, float v1, String paraName2, float v2, String paraName3, float v3);
}
