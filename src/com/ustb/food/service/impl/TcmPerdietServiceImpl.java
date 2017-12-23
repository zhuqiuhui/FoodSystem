package com.ustb.food.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.TcmPerdietDao;
import com.ustb.food.entity.TcmPerdiet;
import com.ustb.food.service.TcmPerdietService;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月1日 下午5:36:48
 */
@Component("tcmPerdietService")
public class TcmPerdietServiceImpl  extends BaseServiceImpl<TcmPerdiet, Integer> implements TcmPerdietService {
    @Resource
    private TcmPerdietDao tcmPerdietDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<TcmPerdiet, Integer> tcmPerdietDao) {
		this.baseDao = tcmPerdietDao;
	}

	@Override
	public List<TcmPerdiet> findbytime(int userId, String insertdate) {
		return tcmPerdietDao.findbytime(userId, insertdate);
	}

	@Override
	public Map<Date, List<TcmPerdiet>> findbytime(int userId, String startDate, String endDate) {
		return tcmPerdietDao.findbytime(userId, startDate, endDate);
	}

}
