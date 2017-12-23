package com.ustb.food.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.dao.BaseDao;
import com.ustb.food.dao.UsersDao;
import com.ustb.food.entity.Users;
import com.ustb.food.service.UsersService;
@Component("usersService")
public class UsersServiceImpl extends BaseServiceImpl<Users,Integer> implements UsersService{
	
	@Autowired UsersDao usersDao;
	@Override
	
	@Resource
	public void setBaseDao(BaseDao<Users, Integer> usersDao) {
		// TODO Auto-generated method stub
		this.baseDao =  usersDao;
	}

}
