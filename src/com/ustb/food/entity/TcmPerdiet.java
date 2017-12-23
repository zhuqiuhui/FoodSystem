package com.ustb.food.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 该类记录用户的中医饮食类，类似于菜谱（参考 TcmCaipu 类）
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月1日 下午3:41:10
 */
@Entity
@Table(name = "tcm_perdiet")
public class TcmPerdiet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 基本属性
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tpId", unique = true, nullable = false)
	private int tpId;
	@Column(name = "name")
	private String name;
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
	@Temporal(TemporalType.DATE)
	@Column(name = "insertdate")
	private Date insertdate;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", nullable = false)
	private Users users;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tcmPerdiet")
	private List<TcmPerrecord> tcmPerrecords;
	
	public TcmPerdiet() {}
	
	public TcmPerdiet(String name, double fei, double xin, double piwei, double gandan, double shen,
			double sanjiao, double qi, double xue, double yin, double yang, double xing, double ku, double gan,
			double suan, double xian, double dan, double sheng, double jiang, double chen, double fu, double hezhong,
			Date insertdate, Users users, List<TcmPerrecord> tcmPerrecords) {
		super();
		this.name = name;
		this.fei = fei;
		this.xin = xin;
		this.piwei = piwei;
		this.gandan = gandan;
		this.shen = shen;
		this.sanjiao = sanjiao;
		this.qi = qi;
		this.xue = xue;
		this.yin = yin;
		this.yang = yang;
		this.xing = xing;
		this.ku = ku;
		this.gan = gan;
		this.suan = suan;
		this.xian = xian;
		this.dan = dan;
		this.sheng = sheng;
		this.jiang = jiang;
		this.chen = chen;
		this.fu = fu;
		this.hezhong = hezhong;
		this.insertdate = insertdate;
		this.users = users;
		this.tcmPerrecords = tcmPerrecords;
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

	public Date getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<TcmPerrecord> getTcmPerrecords() {
		return tcmPerrecords;
	}

	public void setTcmPerrecords(List<TcmPerrecord> tcmPerrecords) {
		this.tcmPerrecords = tcmPerrecords;
	}
	
}
