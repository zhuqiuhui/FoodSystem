package com.ustb.food.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.YuanliaoDao;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;
@Component("yuanliaoService")
public class YuanliaoServiceImpl extends BaseServiceImpl<Yuanliao,Integer> implements YuanliaoService{
	
	@Autowired YuanliaoDao yuanliaoDao;
	
	@Override
	@Resource
	public void setBaseDao(BaseDao<Yuanliao, Integer> yuanliaoDao) {
		this.baseDao =  yuanliaoDao;
	}

	@Override
	public List<Yuanliao> findSame(String mName) {
		return yuanliaoDao.findSame(mName);
	}

	@Override
	public List<Yuanliao> findByName(String mName) {
		return yuanliaoDao.findByName(mName);
	}

	@Override
	public List<Yuanliao> findbyList(List<String> nameLike) {
		return yuanliaoDao.findbyList(nameLike);
	}

	@Override
	public List<Yuanliao> bohe() {
		return yuanliaoDao.bohe();
	}

	@Override
	public List<Yuanliao> standofcountry() {
		// TODO Auto-generated method stub
		return yuanliaoDao.standofcountry();
	}

	@Override
	@Transactional
	public List<Yuanliao> findTiaoliao() {
		// TODO Auto-generated method stub
		return yuanliaoDao.findTiaoliao();
	}
	@Test
	public void test() {
		try{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		YuanliaoService yls=(YuanliaoService)ac.getBean("yuanliaoService");
		List<Yuanliao> same = yls.findSame("羊肉");
		System.out.println(same.get(0).getmName());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
