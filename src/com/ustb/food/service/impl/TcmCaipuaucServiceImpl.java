package com.ustb.food.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.TcmCaipuaucDao;
import com.ustb.food.entity.TcmCaipuauc;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月2日 下午5:36:31
 */

@Component("tcmCaipuaucService")
public class TcmCaipuaucServiceImpl extends BaseServiceImpl<TcmCaipuauc, Integer> implements com.ustb.food.service.TcmCaipuaucService {

	@Resource
	private TcmCaipuaucDao tcmCaipuaucDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<TcmCaipuauc, Integer> tcmCaipuaucDao) {
		this.baseDao = tcmCaipuaucDao;
	}

	@Override
	public List<TcmCaipuauc> getBestTcmPerdietAucList(String paraName1, float v1, String paraName2, float v2,
			String paraName3, float v3) {
		return tcmCaipuaucDao.getBestTcmPerdietAucList(paraName1, v1, paraName2, v2, paraName3, v3);
	}
}
