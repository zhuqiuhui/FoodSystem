package com.ustb.food.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.TcmPerrecordDao;
import com.ustb.food.entity.TcmPerrecord;
import com.ustb.food.service.TcmPerrecordService;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月1日 下午6:15:24
 */
@Component("tcmPerrecordService")
public class TcmPerrecordServiceImpl extends BaseServiceImpl<TcmPerrecord, Integer> implements TcmPerrecordService {

	@Resource
	private TcmPerrecordDao tcmPerrecordDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<TcmPerrecord, Integer> tcmPerrecordDao) {
		this.baseDao = tcmPerrecordDao;
	}

}
