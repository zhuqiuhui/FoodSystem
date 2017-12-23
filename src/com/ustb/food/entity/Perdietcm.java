package com.ustb.food.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Perdietcm entity. @author MyEclipse Persistence Tools
 */
@Entity(name = "Perdietcm")
public class Perdietcm implements java.io.Serializable {

	// Fields

	private Integer pcmId;
	private Users users;
	private String pcmName;
	private double fei;
	private double xin;
	private double piwei;
	private double gandan;
	private double shen;
	private double sanjiao;
	private double qi;
	private double xue;
	private double yin;
	private double yang;
	private double xxin;
	private double ku;
	private double gan;
	private double tian;
	private double xian;
	private double dan;
	private double sheng;
	private double jiang;
	private double chenlian;
	private double fusan;
	private double hezhong;
	private double buyang;
	private double buyin;
	private double xiehuo;
	private double sanhan;
	private Date insertdate;

	// Constructors

	/** default constructor */
	public Perdietcm() {
	}

	/** minimal constructor */
	public Perdietcm(Users users, Date insertdate) {
		this.users = users;
		this.insertdate = insertdate;
	}

	/** full constructor */
	public Perdietcm(Users users, String pcmName, double fei, double xin,
			double piwei, double gandan, double shen, double sanjiao,
			double qi, double xue, double yin, double yang, double xxin,
			double ku, double gan, double tian, double xian, double dan,
			double sheng, double jiang, double chenlian, double fusan,
			double hezhong, double buyang, double buyin, double xiehuo,
			double sanhan, Date insertdate) {
		this.users = users;
		this.pcmName = pcmName;
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
		this.xxin = xxin;
		this.ku = ku;
		this.gan = gan;
		this.tian = tian;
		this.xian = xian;
		this.dan = dan;
		this.sheng = sheng;
		this.jiang = jiang;
		this.chenlian = chenlian;
		this.fusan = fusan;
		this.hezhong = hezhong;
		this.buyang = buyang;
		this.buyin = buyin;
		this.xiehuo = xiehuo;
		this.sanhan = sanhan;
		this.insertdate = insertdate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pcmId", unique = true, nullable = false)
	public Integer getPcmId() {
		return this.pcmId;
	}

	public void setPcmId(Integer pcmId) {
		this.pcmId = pcmId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "pcmName", length = 20)
	public String getPcmName() {
		return this.pcmName;
	}

	public void setPcmName(String pcmName) {
		this.pcmName = pcmName;
	}

	@Column(name = "fei", precision = 10)
	public double getFei() {
		return this.fei;
	}

	public void setFei(double fei) {
		this.fei = fei;
	}

	@Column(name = "xin", precision = 10)
	public double getXin() {
		return this.xin;
	}

	public void setXin(double xin) {
		this.xin = xin;
	}

	@Column(name = "piwei", precision = 10)
	public double getPiwei() {
		return this.piwei;
	}

	public void setPiwei(double piwei) {
		this.piwei = piwei;
	}

	@Column(name = "gandan", precision = 10)
	public double getGandan() {
		return this.gandan;
	}

	public void setGandan(double gandan) {
		this.gandan = gandan;
	}

	@Column(name = "shen", precision = 10)
	public double getShen() {
		return this.shen;
	}

	public void setShen(double shen) {
		this.shen = shen;
	}

	@Column(name = "sanjiao", precision = 10)
	public double getSanjiao() {
		return this.sanjiao;
	}

	public void setSanjiao(double sanjiao) {
		this.sanjiao = sanjiao;
	}

	@Column(name = "qi", precision = 10)
	public double getQi() {
		return this.qi;
	}

	public void setQi(double qi) {
		this.qi = qi;
	}

	@Column(name = "xue", precision = 10)
	public double getXue() {
		return this.xue;
	}

	public void setXue(double xue) {
		this.xue = xue;
	}

	@Column(name = "yin", precision = 10)
	public double getYin() {
		return this.yin;
	}

	public void setYin(double yin) {
		this.yin = yin;
	}

	@Column(name = "yang", precision = 10)
	public double getYang() {
		return this.yang;
	}

	public void setYang(double yang) {
		this.yang = yang;
	}

	@Column(name = "xxin", precision = 10)
	public double getXxin() {
		return this.xxin;
	}

	public void setXxin(double xxin) {
		this.xxin = xxin;
	}

	@Column(name = "ku", precision = 10)
	public double getKu() {
		return this.ku;
	}

	public void setKu(double ku) {
		this.ku = ku;
	}

	@Column(name = "gan", precision = 10)
	public double getGan() {
		return this.gan;
	}

	public void setGan(double gan) {
		this.gan = gan;
	}

	@Column(name = "tian", precision = 10)
	public double getTian() {
		return this.tian;
	}

	public void setTian(double tian) {
		this.tian = tian;
	}

	@Column(name = "xian", precision = 10)
	public double getXian() {
		return this.xian;
	}

	public void setXian(double xian) {
		this.xian = xian;
	}

	@Column(name = "dan", precision = 10)
	public double getDan() {
		return this.dan;
	}

	public void setDan(double dan) {
		this.dan = dan;
	}

	@Column(name = "sheng", precision = 10)
	public double getSheng() {
		return this.sheng;
	}

	public void setSheng(double sheng) {
		this.sheng = sheng;
	}

	@Column(name = "jiang", precision = 10)
	public double getJiang() {
		return this.jiang;
	}

	public void setJiang(double jiang) {
		this.jiang = jiang;
	}

	@Column(name = "chenlian", precision = 10)
	public double getChenlian() {
		return this.chenlian;
	}

	public void setChenlian(double chenlian) {
		this.chenlian = chenlian;
	}

	@Column(name = "fusan", precision = 10)
	public double getFusan() {
		return this.fusan;
	}

	public void setFusan(double fusan) {
		this.fusan = fusan;
	}

	@Column(name = "hezhong", precision = 10)
	public double getHezhong() {
		return this.hezhong;
	}

	public void setHezhong(double hezhong) {
		this.hezhong = hezhong;
	}

	@Column(name = "buyang", precision = 10)
	public double getBuyang() {
		return this.buyang;
	}

	public void setBuyang(double buyang) {
		this.buyang = buyang;
	}

	@Column(name = "buyin", precision = 10)
	public double getBuyin() {
		return this.buyin;
	}

	public void setBuyin(double buyin) {
		this.buyin = buyin;
	}

	@Column(name = "xiehuo", precision = 10)
	public double getXiehuo() {
		return this.xiehuo;
	}

	public void setXiehuo(double xiehuo) {
		this.xiehuo = xiehuo;
	}

	@Column(name = "sanhan", precision = 10)
	public double getSanhan() {
		return this.sanhan;
	}

	public void setSanhan(double sanhan) {
		this.sanhan = sanhan;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "insertdate", nullable = false, length = 10)
	public Date getInsertdate() {
		return this.insertdate;
	}

	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}

}