package com.ustb.food.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Perdiet entity. @author MyEclipse Persistence Tools
 */
@Entity(name = "Perdiet")

public class Perdiet implements java.io.Serializable {

	// Fields

	@Override
	public String toString() {
		return "Perdiet [pgId=" + pgId + ", users=" + users + ", vname=" + vname
				+ ", calories=" + calories + ", carbohydrate=" + carbohydrate
				+ ", fat=" + fat + ", protein=" + protein + ", vitamine="
				+ vitamine + ", vta=" + vta + ", vtc=" + vtc + ", vte=" + vte
				+ ", carotene=" + carotene + ", thiamine=" + thiamine
				+ ", riboflavin=" + riboflavin + ", yansuan=" + yansuan
				+ ", cholesterol=" + cholesterol + ", mg=" + mg + ", ca=" + ca
				+ ", iron=" + iron + ", zinc=" + zinc + ", copper=" + copper
				+ ", mn=" + mn + ", k=" + k + ", p=" + p + ", na=" + na
				+ ", se=" + se + ", insertdate=" + insertdate + "]";
	}

	private Integer pgId;
	private Users users;
	private String vname;
	private Double calories;
	private Double carbohydrate;
	private Double fat;
	private Double protein;
	private Double vitamine;
	private Double vta;
	private Double vtc;
	private Double vte;
	private Double carotene;
	private Double thiamine;
	private Double riboflavin;
	private Double yansuan;
	private Double cholesterol;
	private Double mg;
	private Double ca;
	private Double iron;
	private Double zinc;
	private Double copper;
	private Double mn;
	private Double k;
	private Double p;
	private Double na;
	private Double se;
	private Date insertdate;
	
	private List<Perrecord> perrecords;

	// Constructors

	/** default constructor */
	public Perdiet() {
	}

	/** minimal constructor */
	public Perdiet(Users users, Date insertdate) {
		this.users = users;
		this.insertdate = insertdate;
	}

	/** full constructor */
	public Perdiet(Users users, String vname, Double calories,
			Double carbohydrate, Double fat, Double protein, Double vitamine,
			Double vta, Double vtc, Double vte, Double carotene, Double thiamine,
			Double riboflavin, Double yansuan, Double cholesterol, Double mg,
			Double ca, Double iron, Double zinc, Double copper, Double mn, Double k,
			Double p, Double na, Double se, Date insertdate, List<Perrecord> perrecords) {
		super();
		this.users = users;
		this.vname = vname;
		this.calories = calories;
		this.carbohydrate = carbohydrate;
		this.fat = fat;
		this.protein = protein;
		this.vitamine = vitamine;
		this.vta = vta;
		this.vtc = vtc;
		this.vte = vte;
		this.carotene = carotene;
		this.thiamine = thiamine;
		this.riboflavin = riboflavin;
		this.yansuan = yansuan;
		this.cholesterol = cholesterol;
		this.mg = mg;
		this.ca = ca;
		this.iron = iron;
		this.zinc = zinc;
		this.copper = copper;
		this.mn = mn;
		this.k = k;
		this.p = p;
		this.na = na;
		this.se = se;
		this.insertdate = insertdate;
		this.perrecords = perrecords;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pgId", unique = true, nullable = false)
	public Integer getPgId() {
		return pgId;
	}

	public void setPgId(Integer pgId) {
		this.pgId = pgId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "vName", length = 50)
	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	@Column(name = "calories", precision = 12, scale = 0)
	public Double getCalories() {
		return this.calories;
	}

	public void setCalories(Double calories) {
		this.calories = calories;
	}

	@Column(name = "carbohydrate", precision = 12, scale = 0)
	public Double getCarbohydrate() {
		return this.carbohydrate;
	}

	public void setCarbohydrate(Double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	@Column(name = "fat", precision = 12, scale = 0)
	public Double getFat() {
		return this.fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	@Column(name = "protein", precision = 12, scale = 0)
	public Double getProtein() {
		return this.protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	@Column(name = "vitamine", precision = 12, scale = 0)
	public Double getVitamine() {
		return this.vitamine;
	}

	public void setVitamine(Double vitamine) {
		this.vitamine = vitamine;
	}

	@Column(name = "vta", precision = 12, scale = 0)
	public Double getVta() {
		return this.vta;
	}

	public void setVta(Double vta) {
		this.vta = vta;
	}

	@Column(name = "vtc", precision = 12, scale = 0)
	public Double getVtc() {
		return this.vtc;
	}

	public void setVtc(Double vtc) {
		this.vtc = vtc;
	}

	@Column(name = "vte", precision = 12, scale = 0)
	public Double getVte() {
		return this.vte;
	}

	public void setVte(Double vte) {
		this.vte = vte;
	}

	@Column(name = "carotene", precision = 12, scale = 0)
	public Double getCarotene() {
		return this.carotene;
	}

	public void setCarotene(Double carotene) {
		this.carotene = carotene;
	}

	@Column(name = "thiamine", precision = 12, scale = 0)
	public Double getThiamine() {
		return this.thiamine;
	}

	public void setThiamine(Double thiamine) {
		this.thiamine = thiamine;
	}

	@Column(name = "riboflavin", precision = 12, scale = 0)
	public Double getRiboflavin() {
		return this.riboflavin;
	}

	public void setRiboflavin(Double riboflavin) {
		this.riboflavin = riboflavin;
	}

	@Column(name = "yansuan", precision = 12, scale = 0)
	public Double getYansuan() {
		return this.yansuan;
	}

	public void setYansuan(Double yansuan) {
		this.yansuan = yansuan;
	}

	@Column(name = "cholesterol", precision = 12, scale = 0)
	public Double getCholesterol() {
		return this.cholesterol;
	}

	public void setCholesterol(Double cholesterol) {
		this.cholesterol = cholesterol;
	}

	@Column(name = "mg", precision = 12, scale = 0)
	public Double getMg() {
		return this.mg;
	}

	public void setMg(Double mg) {
		this.mg = mg;
	}

	@Column(name = "ca", precision = 12, scale = 0)
	public Double getCa() {
		return this.ca;
	}

	public void setCa(Double ca) {
		this.ca = ca;
	}

	@Column(name = "iron", precision = 12, scale = 0)
	public Double getIron() {
		return this.iron;
	}

	public void setIron(Double iron) {
		this.iron = iron;
	}

	@Column(name = "zinc", precision = 12, scale = 0)
	public Double getZinc() {
		return this.zinc;
	}

	public void setZinc(Double zinc) {
		this.zinc = zinc;
	}

	@Column(name = "copper", precision = 12, scale = 0)
	public Double getCopper() {
		return this.copper;
	}

	public void setCopper(Double copper) {
		this.copper = copper;
	}

	@Column(name = "mn", precision = 12, scale = 0)
	public Double getMn() {
		return this.mn;
	}

	public void setMn(Double mn) {
		this.mn = mn;
	}

	@Column(name = "k", precision = 12, scale = 0)
	public Double getK() {
		return this.k;
	}

	public void setK(Double k) {
		this.k = k;
	}

	@Column(name = "p", precision = 12, scale = 0)
	public Double getP() {
		return this.p;
	}

	public void setP(Double p) {
		this.p = p;
	}

	@Column(name = "na", precision = 12, scale = 0)
	public Double getNa() {
		return this.na;
	}

	public void setNa(Double na) {
		this.na = na;
	}

	@Column(name = "se", precision = 12, scale = 0)
	public Double getSe() {
		return this.se;
	}

	public void setSe(Double se) {
		this.se = se;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "insertdate", nullable = false, length = 10)
	public Date getInsertdate() {
		return this.insertdate;
	}

	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "perdiet")
	public List<Perrecord> getPerrecords() {
		return perrecords;
	}

	public void setPerrecords(List<Perrecord> perrecords) {
		this.perrecords = perrecords;
	}
	

}