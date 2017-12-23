package com.ustb.food.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Perrecord entity. @author MyEclipse Persistence Tools
 */
@Entity(name = "Perrecord")

public class Perrecord implements java.io.Serializable {

	// Fields

	private PerrecordId pgId;
	private Perdiet perdiet;
	private Users users;
	private Yuanliao yuanliao;
	private Integer amount;
	private Date insertdate;

	// Constructors

	/** default constructor */
	public Perrecord() {
	}

	/** full constructor */
	public Perrecord(PerrecordId pgId, Perdiet perdiet, Users users, Yuanliao yuanliao, Integer amount, Date insertdate) {
		this.pgId = pgId;
		this.perdiet = perdiet;
		this.users = users;
		this.yuanliao = yuanliao;
		this.amount = amount;
		this.insertdate = insertdate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "pgId", column = @Column(name = "pgId", nullable = false)),
			@AttributeOverride(name = "maId", column = @Column(name = "maId", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false))})
	public PerrecordId getPgId() {
		return pgId;
	}

	public void setPgId(PerrecordId pgId) {
		this.pgId = pgId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pgId", nullable = false, insertable = false, updatable = false)
	public Perdiet getPerdiet() {
		return perdiet;
	}

	public void setPerdiet(Perdiet perdiet) {
		this.perdiet = perdiet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maId", nullable = false, insertable = false, updatable = false)
	public Yuanliao getYuanliao() {
		return this.yuanliao;
	}

	public void setYuanliao(Yuanliao yuanliao) {
		this.yuanliao = yuanliao;
	}

	@Column(name = "amount")
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "insertdate", length = 10)

	public Date getInsertdate() {
		return this.insertdate;
	}

	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}

}