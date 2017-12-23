package com.ustb.food.service;

import java.util.List;

import com.ustb.food.entity.Guanxi;

public interface GuanxiService extends BaseService<Guanxi, Integer> {
	public List<Guanxi> getC(int viewId, int maId);
}
