package com.ustb.food.action.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.TcmYuanliao;
import com.ustb.food.service.TcmYuanliaoService;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月30日 下午4:47:00
 */
@Component("/json/TcmYuanliaoAction")
@Scope("prototype")
public class TcmYuanliaoAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private TcmYuanliaoService tcmYuanliaoService;
	
	private String keyword;
	private List<TcmYuanliao> yuanliaoList;

	public String search() {
		yuanliaoList = new ArrayList<TcmYuanliao>();
		for (TcmYuanliao yuanliao : tcmYuanliaoService.findByName(keyword)) {
			TcmYuanliao yuanliaoTemp = new TcmYuanliao();
			yuanliaoTemp.settId(yuanliao.gettId());
			yuanliaoTemp.setName(yuanliao.getName());
			yuanliaoList.add(yuanliaoTemp);
		}
		return "json";
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<TcmYuanliao> getYuanliaoList() {
		return yuanliaoList;
	}

	public void setYuanliaoList(List<TcmYuanliao> yuanliaoList) {
		this.yuanliaoList = yuanliaoList;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
	}

}
