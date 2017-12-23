package com.ustb.food.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.TcmYuanliaoDao;
import com.ustb.food.entity.TcmYuanliao;
import com.ustb.food.service.TcmYuanliaoService;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月30日 下午3:33:26
 */
@Component("tcmYuanliaoService")
public class TcmYuanliaoServiceImpl extends BaseServiceImpl<TcmYuanliao, Integer> implements TcmYuanliaoService {
   
	@Autowired
	private TcmYuanliaoDao tcmYuanliaoDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<TcmYuanliao, Integer> tcmYuanliaoDao) {
		this.baseDao = tcmYuanliaoDao;
	}

	@Override
	public List<TcmYuanliao> findByName(String name) {
		return tcmYuanliaoDao.findByName(name);
	}
}
