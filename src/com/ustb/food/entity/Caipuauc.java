package com.ustb.food.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="caipu_auc")
@Entity(name = "Caipuauc")
public class Caipuauc implements java.io.Serializable {
	
	private int id;
	private int viewId;
	private String viewName;
	private float aucHypertension; // 存放该菜谱高血压 auc 值
	private float aucStomach; // 存放该菜谱养肠胃 auc 值
	private float aucDiabetes; // 存放该菜谱糖尿病 auc 值
	
	public Caipuauc() {}
	
	public Caipuauc(int viewId, String viewName, float aucHypertension, float aucStomach, float aucDiabetes) {
		this.viewId = viewId;
		this.viewName = viewName;
		this.aucHypertension = aucHypertension;
		this.aucStomach = aucStomach;
		this.aucDiabetes = aucDiabetes;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "viewId")
	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

	@Column(name = "viewName", length = 50)
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	@Column(name = "aucHypertension", precision = 12, scale = 0)
	public float getAucHypertension() {
		return aucHypertension;
	}

	public void setAucHypertension(float aucHypertension) {
		this.aucHypertension = aucHypertension;
	}

	@Column(name = "aucStomach", precision = 12, scale = 0)
	public float getAucStomach() {
		return aucStomach;
	}

	public void setAucStomach(float aucStomach) {
		this.aucStomach = aucStomach;
	}

	@Column(name = "aucDiabetes", precision = 12, scale = 0)
	public float getAucDiabetes() {
		return aucDiabetes;
	}

	public void setAucDiabetes(float aucDiabetes) {
		this.aucDiabetes = aucDiabetes;
	}
	
}
