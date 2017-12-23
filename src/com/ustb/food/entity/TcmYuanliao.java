package com.ustb.food.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tcm_yuanliao")
public class TcmYuanliao implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	// 基本属性
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tId", unique = true, nullable = false)
	private int tId;
	@Column(name = "name")
	private String name;
	@Column(name="mName")
	private String mName;
	@Column(name="weight")
	private double weight;
	@Column(name = "fei")
	private double fei;
	@Column(name = "xin")
	private double xin;
	@Column(name = "piwei")
	private double piwei;
	@Column(name = "gandan")
	private double gandan;
	@Column(name = "shen")
	private double shen;
	@Column(name = "sanjiao")
	private double sanjiao;
	@Column(name = "qi")
	private double qi;
	@Column(name = "xue")
	private double xue;
	@Column(name = "yin")
	private double yin;
	@Column(name = "yang")
	private double yang;
	@Column(name = "xing")
	private double xing;
	@Column(name = "ku")
	private double ku;
	@Column(name = "gan")
	private double gan;
	@Column(name = "suan")
	private double suan;
	@Column(name = "xian")
	private double xian;
	@Column(name = "dan")
	private double dan;
	@Column(name = "sheng")
	private double sheng;
	@Column(name = "jiang")
	private double jiang;
	@Column(name = "chen")
	private double chen;
	@Column(name = "fu")
	private double fu;
	@Column(name = "hezhong")
	private double hezhong;
	
	// 中医原料与关系对应实体
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tcmYuanliao")
	private List<TcmGuanxi> tcmGuanxis;
	
	public TcmYuanliao() {
		
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getFei() {
		return fei;
	}

	public void setFei(double fei) {
		this.fei = fei;
	}

	public double getXin() {
		return xin;
	}

	public void setXin(double xin) {
		this.xin = xin;
	}

	public double getPiwei() {
		return piwei;
	}

	public void setPiwei(double piwei) {
		this.piwei = piwei;
	}

	public double getGandan() {
		return gandan;
	}

	public void setGandan(double gandan) {
		this.gandan = gandan;
	}

	public double getShen() {
		return shen;
	}

	public void setShen(double shen) {
		this.shen = shen;
	}

	public double getSanjiao() {
		return sanjiao;
	}

	public void setSanjiao(double sanjiao) {
		this.sanjiao = sanjiao;
	}

	public double getQi() {
		return qi;
	}

	public void setQi(double qi) {
		this.qi = qi;
	}

	public double getXue() {
		return xue;
	}

	public void setXue(double xue) {
		this.xue = xue;
	}

	public double getYin() {
		return yin;
	}

	public void setYin(double yin) {
		this.yin = yin;
	}

	public double getYang() {
		return yang;
	}

	public void setYang(double yang) {
		this.yang = yang;
	}

	public double getXing() {
		return xing;
	}

	public void setXing(double xing) {
		this.xing = xing;
	}

	public double getKu() {
		return ku;
	}

	public void setKu(double ku) {
		this.ku = ku;
	}

	public double getGan() {
		return gan;
	}

	public void setGan(double gan) {
		this.gan = gan;
	}

	public double getSuan() {
		return suan;
	}

	public void setSuan(double suan) {
		this.suan = suan;
	}

	public double getXian() {
		return xian;
	}

	public void setXian(double xian) {
		this.xian = xian;
	}

	public double getDan() {
		return dan;
	}

	public void setDan(double dan) {
		this.dan = dan;
	}

	public double getSheng() {
		return sheng;
	}

	public void setSheng(double sheng) {
		this.sheng = sheng;
	}

	public double getJiang() {
		return jiang;
	}

	public void setJiang(double jiang) {
		this.jiang = jiang;
	}

	public double getChen() {
		return chen;
	}

	public void setChen(double chen) {
		this.chen = chen;
	}

	public double getFu() {
		return fu;
	}

	public void setFu(double fu) {
		this.fu = fu;
	}

	public double getHezhong() {
		return hezhong;
	}

	public void setHezhong(double hezhong) {
		this.hezhong = hezhong;
	}

	public List<TcmGuanxi> getTcmGuanxis() {
		return tcmGuanxis;
	}

	public void setTcmGuanxis(List<TcmGuanxi> tcmGuanxis) {
		this.tcmGuanxis = tcmGuanxis;
	}

}
