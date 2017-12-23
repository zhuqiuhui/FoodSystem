package com.ustb.food.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Material;
import com.ustb.food.service.MaterialService;


@Component("/front/MaterialAction")
@Scope("prototype")
public class MaterialAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired MaterialService materialService;
	private Map<String, Object> session;
	private String url;
	private Material material;
	private int id;

	public String detail(){
		material = materialService.get(id);
		return SUCCESS;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
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
