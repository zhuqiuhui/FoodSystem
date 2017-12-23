package com.ustb.food.action.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;

@Component("/json/YuanliaoAction")
@Scope("prototype")
public class YuanliaoAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	@Autowired
	YuanliaoService yuanliaoService;
	private String keyword;
	private List<Yuanliao> yuanliaoList;

	public String search() {
		yuanliaoList = new ArrayList<Yuanliao>();
		for (Yuanliao yuanliao : yuanliaoService.findByName(keyword)) {
			Yuanliao yuanliaoTemp = new Yuanliao();
			yuanliaoTemp.setmName(yuanliao.getmName());
			yuanliaoTemp.setMaId(yuanliao.getMaId());
			yuanliaoList.add(yuanliaoTemp);
		}
		return "json";
	}

	// 利用注解的方式来像后台传值，实际上是按照get方式进行传值@JSON(serialize = false)说明他是不传的。
	@JSON(serialize = false)
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Yuanliao> getYuanliaoList() {
		return yuanliaoList;
	}

	public void setYuanliaoList(List<Yuanliao> yuanliaoList) {
		this.yuanliaoList = yuanliaoList;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}
}
