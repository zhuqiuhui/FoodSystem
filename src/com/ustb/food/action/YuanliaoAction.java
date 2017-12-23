package com.ustb.food.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;

@Component("/front/YuanliaoAction")
@Scope("prototype")
public class YuanliaoAction extends ActionSupport implements SessionAware {
	@Autowired
	YuanliaoService yuanliaoService;
	private Map<String, Object> session;
	private String url;
	private Yuanliao material;
	private int id;
	private String mn;
	private List<Yuanliao> ylList;

	public String detail() {
		material = yuanliaoService.get(id);
		return SUCCESS;
	}

	public String search() {
		ylList = yuanliaoService.findByName(mn);
		return SUCCESS;
	}

	public String doSearch() {
		ylList = yuanliaoService.findByName(mn);
		return SUCCESS;
	}

	public List<Yuanliao> getYlList() {
		return ylList;
	}

	public void setYlList(List<Yuanliao> ylList) {
		this.ylList = ylList;
	}

	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Yuanliao getMaterial() {
		return material;
	}

	public void setMaterial(Yuanliao material) {
		this.material = material;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
