package com.ustb.food.entity;

// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Guanxi entity. @author MyEclipse Persistence Tools
 */
@Entity(name = "guanxi")
public class Guanxi implements java.io.Serializable {

	// Fields

	private GuanxiId id;
	private Caipu caipu;
	private Yuanliao yuanliao;
	private Integer amount;

	// Constructors

	/** default constructor */
	public Guanxi() {
	}

	/** minimal constructor */
	public Guanxi(GuanxiId id, Caipu caipu, Yuanliao yuanliao) {
		this.id = id;
		this.caipu = caipu;
		this.yuanliao = yuanliao;
	}

	public Guanxi(Yuanliao yuanliao, Integer amount) {
		this.yuanliao = yuanliao;
		this.amount = amount;
	}

	/** full constructor */
	public Guanxi(GuanxiId id, Caipu caipu, Yuanliao yuanliao, Integer amount) {
		this.id = id;
		this.caipu = caipu;
		this.yuanliao = yuanliao;
		this.amount = amount;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "viewId", column = @Column(name = "viewId", nullable = false)),
			@AttributeOverride(name = "maId", column = @Column(name = "maId", nullable = false)) })
	public GuanxiId getId() {
		return this.id;
	}

	public void setId(GuanxiId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "viewId", nullable = false, insertable = false, updatable = false)
	public Caipu getCaipu() {
		return this.caipu;
	}

	public void setCaipu(Caipu caipu) {
		this.caipu = caipu;
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

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Guanxi other = (Guanxi) obj;
		if (this.id.hashCode() == other.id.hashCode()) {
			return true;
		} else {
			return false;
		}
	}

}