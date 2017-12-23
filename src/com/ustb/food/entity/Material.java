package com.ustb.food.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Material")
public class Material implements java.io.Serializable{
	
	private int maId;
	private String mName ;
	private Double calories ;
	private Double carbohydrate;
	private Double fat ;
	private Double protein ;
	private Double vitamine ;
	private Double vta ;
	private Double vtc ;
	private Double vte ;
	private Double carotene ;
	private Double thiamine ;
	private Double riboflavin ;
	private Double yansuan ;
	private Double cholesterol ;
	private Double mg ;
	private Double ca ;
	private Double iron ;
	private Double zinc ;
	private Double copper ;
	private Double mn ;
	private Double k ;
	private Double p ;
	private Double na ;
	private Double se ;
	private String source;
	private Double amount;
	public Material(){
		
	}
	
	
public Material(String mName,Double calories,Double carbohydrate,Double fat, Double protein,Double vitamine){
	this.mName = mName;
	this.calories = calories;
	this.carbohydrate = carbohydrate;
	this.fat = fat;
	this.protein = protein;
	this.vitamine = vitamine;
	
		
	}

@Id
@GeneratedValue
@Column(name="maId",unique=true,nullable=false)
public int getMaId() {
	return maId;
}


public void setMaId(int maId) {
	this.maId = maId;
}


@Column(name="mName",nullable=false)
public String getmName() {
	return mName;
}

public void setmName(String mName) {
	this.mName = mName;
}


@Column(name="calories",nullable=true)
public Double getCalories() {
	return calories;
}


public void setCalories(Double calories) {
	this.calories = calories;
}

@Column(name="carbohydrate",nullable=true)
public Double getCarbohydrate() {
	return carbohydrate;
}


public void setCarbohydrate(Double carbohydrate) {
	this.carbohydrate = carbohydrate;
}

@Column(name="fat",nullable=true)
public Double getFat() {
	return fat;
}


public void setFat(Double fat) {
	this.fat = fat;
}

@Column(name="protein",nullable=true)
public Double getProtein() {
	return protein;
}


public void setProtein(Double protein) {
	this.protein = protein;
}

@Column(name="vitamine",nullable=true)
public Double getVitamine() {
	return vitamine;
}


public void setVitamine(Double vitamine) {
	this.vitamine = vitamine;
}

@Column(name="vta",nullable=true)
public Double getVta() {
	return vta;
}


public void setVta(Double vta) {
	this.vta = vta;
}

@Column(name="vtc",nullable=true)
public Double getVtc() {
	return vtc;
}


public void setVtc(Double vtc) {
	this.vtc = vtc;
}

@Column(name="vte",nullable=true)
public Double getVte() {
	return vte;
}


public void setVte(Double vte) {
	this.vte = vte;
}

@Column(name="carotene",nullable=true)
public Double getCarotene() {
	return carotene;
}


public void setCarotene(Double carotene) {
	this.carotene = carotene;
}

@Column(name="thiamine",nullable=true)
public Double getThiamine() {
	return thiamine;
}


public void setThiamine(Double thiamine) {
	this.thiamine = thiamine;
}

@Column(name="riboflavin",nullable=true)
public Double getRiboflavin() {
	return riboflavin;
}


public void setRiboflavin(Double riboflavin) {
	this.riboflavin = riboflavin;
}

@Column(name="yansuan",nullable=true)
public Double getYansuan() {
	return yansuan;
}


public void setYansuan(Double yansuan) {
	this.yansuan = yansuan;
}

@Column(name="cholesterol",nullable=true)
public Double getCholesterol() {
	return cholesterol;
}


public void setCholesterol(Double cholesterol) {
	this.cholesterol = cholesterol;
}

@Column(name="mg",nullable=true)
public Double getMg() {
	return mg;
}


public void setMg(Double mg) {
	this.mg = mg;
}

@Column(name="ca",nullable=true)
public Double getCa() {
	return ca;
}


public void setCa(Double ca) {
	this.ca = ca;
}

@Column(name="iron",nullable=true)
public Double getIron() {
	return iron;
}


public void setIron(Double iron) {
	this.iron = iron;
}

@Column(name="zinc",nullable=true)
public Double getZinc() {
	return zinc;
}


public void setZinc(Double zinc) {
	this.zinc = zinc;
}

@Column(name="copper",nullable=true)
public Double getCopper() {
	return copper;
}


public void setCopper(Double copper) {
	this.copper = copper;
}

@Column(name="mn",nullable=true)
public Double getMn() {
	return mn;
}


public void setMn(Double mn) {
	this.mn = mn;
}

@Column(name="k",nullable=true)
public Double getK() {
	return k;
}


public void setK(Double k) {
	this.k = k;
}

@Column(name="p",nullable=true)
public Double getP() {
	return p;
}


public void setP(Double p) {
	this.p = p;
}

@Column(name="na",nullable=true)
public Double getNa() {
	return na;
}


public void setNa(Double na) {
	this.na = na;
}

@Column(name="se",nullable=true)
public Double getSe() {
	return se;
}


public void setSe(Double se) {
	this.se = se;
}

@Column(name="source",nullable=false)
public String getSource() {
	return source;
}


public void setSource(String source) {
	this.source = source;
}

@Column(name="amount",nullable=false)
public Double getAmount() {
	return amount;
}


public void setAmount(Double amount) {
	this.amount = amount;
}
	
	
	
}
