package com.ustb.food.dao.impl;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.impl.BaseDaoImpl;

import com.ustb.food.dao.UsersDao;
import com.ustb.food.entity.Users;

@Component("usersDao")
public class UsersDaoImpl  extends BaseDaoImpl<Users, Integer> implements UsersDao {

}
