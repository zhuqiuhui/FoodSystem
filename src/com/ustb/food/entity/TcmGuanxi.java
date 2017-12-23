package com.ustb.food.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月30日 上午10:41:59
 */
@Entity(name="tcmGuanxi")
@Table(name="tcm_guanxi")
public class TcmGuanxi implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "tcmId", column = @Column(name = "tcmId", nullable = false)),
			@AttributeOverride(name = "tId", column = @Column(name = "tId", nullable = false)) })
	TcmGuanxiId id;
	@ManyToOne
	@JoinColumn(name = "tcmId", nullable = false, insertable = false, updatable = false)
	private TcmCaipu tcmCaipu;
	@ManyToOne
	@JoinColumn(name = "tId", nullable = false, insertable = false, updatable = false)
	private TcmYuanliao tcmYuanliao;
	@Column(name="amount")
	private Integer amount;
	
	public TcmGuanxi() {}
	
	public TcmGuanxi(TcmGuanxiId id, TcmCaipu tcmCaipu, TcmYuanliao tcmYuanliao, Integer amount) {
		super();
		this.id = id;
		this.tcmCaipu = tcmCaipu;
		this.tcmYuanliao = tcmYuanliao;
		this.amount = amount;
	}
	
	public TcmGuanxiId getId() {
		return id;
	}

	public void setId(TcmGuanxiId id) {
		this.id = id;
	}

	public TcmCaipu getTcmCaipu() {
		return tcmCaipu;
	}

	public void setTcmCaipu(TcmCaipu tcmCaipu) {
		this.tcmCaipu = tcmCaipu;
	}

	public TcmYuanliao getTcmYuanliao() {
		return tcmYuanliao;
	}

	public void setTcmYuanliao(TcmYuanliao tcmYuanliao) {
		this.tcmYuanliao = tcmYuanliao;
	}

	public Integer getAmount() {
		return amount;
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
		TcmGuanxi other = (TcmGuanxi) obj;
		if (this.id.hashCode() == other.id.hashCode()) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
