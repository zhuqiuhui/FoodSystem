package com.ustb.food.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.TcmYuanliao;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.TcmYuanliaoService;
import com.ustb.food.service.YuanliaoService;

@Component("/front/TcmYuanliaoAction")
@Scope("prototype")
public class TcmYuanliaoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	TcmYuanliaoService tcmYuanliaoService;
	
	private Map<String, Object> session;
	private String url;
	private TcmYuanliao material;
	private int id;
	private String mn;
	private List<TcmYuanliao> ylList;

	public String detail() {
		material = tcmYuanliaoService.get(id);
		return SUCCESS;
	}

	public String search() {
		ylList = tcmYuanliaoService.findByName(mn);
		return SUCCESS;
	}

	public String doSearch() {
		ylList = tcmYuanliaoService.findByName(mn);
		return SUCCESS;
	}

	public List<TcmYuanliao> getYlList() {
		return ylList;
	}

	public void setYlList(List<TcmYuanliao> ylList) {
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

	public TcmYuanliao getMaterial() {
		return material;
	}

	public void setMaterial(TcmYuanliao material) {
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
