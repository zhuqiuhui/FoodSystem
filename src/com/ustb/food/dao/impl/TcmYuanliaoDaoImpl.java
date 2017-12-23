package com.ustb.food.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ustb.food.dao.TcmYuanliaoDao;
import com.ustb.food.entity.TcmYuanliao;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月30日 下午3:38:54
 */
@Component("tcmYuanliaoDao")
public class TcmYuanliaoDaoImpl extends BaseDaoImpl<TcmYuanliao, Integer> implements TcmYuanliaoDao {

	@Override
	public List<TcmYuanliao> findByName(String name) {
		String hql = "from TcmYuanliao where name like '%" + name + "%'";
		return list(hql, 0, 0);
	}
}
