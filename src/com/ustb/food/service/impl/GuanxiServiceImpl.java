package com.ustb.food.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.GuanxiDao;
import com.ustb.food.entity.Guanxi;
import com.ustb.food.service.GuanxiService;

@Component("guanxiService")
public class GuanxiServiceImpl extends BaseServiceImpl<Guanxi, Integer>
		implements GuanxiService {

	@Autowired
	GuanxiDao guanxiDao;

	@Override
	@Resource
	public void setBaseDao(BaseDao<Guanxi, Integer> guanxiDao) {
		this.baseDao = guanxiDao;
	}

	@Override
	public List<Guanxi> getC(int viewId, int maId) {
		return guanxiDao.getC(viewId, maId);
	}

}
