package com.ustb.food.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月2日 下午5:27:30
 */
@Table(name="tcm_caipu_auc")
@Entity(name = "TcmCaipuauc")
public class TcmCaipuauc implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "tpId")
	private int tpId;
	@Column(name = "name")
	private String name;
	@Column(name = "aucHypertension")
	private float aucHypertension; // 存放该菜谱高血压 auc 值
	@Column(name = "aucStomach")
	private float aucStomach; // 存放该菜谱养肠胃 auc 值
	@Column(name = "aucDiabetes")
	private float aucDiabetes; // 存放该菜谱糖尿病 auc 值
	
	public TcmCaipuauc() {}

	public TcmCaipuauc(int tpId, String name, float aucHypertension, float aucStomach, float aucDiabetes) {
		super();
		this.tpId = tpId;
		this.name = name;
		this.aucHypertension = aucHypertension;
		this.aucStomach = aucStomach;
		this.aucDiabetes = aucDiabetes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTpId() {
		return tpId;
	}

	public void setTpId(int tpId) {
		this.tpId = tpId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAucHypertension() {
		return aucHypertension;
	}

	public void setAucHypertension(float aucHypertension) {
		this.aucHypertension = aucHypertension;
	}

	public float getAucStomach() {
		return aucStomach;
	}

	public void setAucStomach(float aucStomach) {
		this.aucStomach = aucStomach;
	}

	public float getAucDiabetes() {
		return aucDiabetes;
	}

	public void setAucDiabetes(float aucDiabetes) {
		this.aucDiabetes = aucDiabetes;
	}
	
}
