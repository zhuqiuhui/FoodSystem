package com.ustb.food.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 该类是用户饮食与中医原料表的对应记录类
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月1日 下午4:05:23
 */
@Entity
@Table(name="tcm_perrecord")
public class TcmPerrecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ptId", column = @Column(name = "ptId", nullable = false)),
			@AttributeOverride(name = "tId", column = @Column(name = "tId", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false))})
	private TcmPerrecordId tcmPgId;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "tpId", nullable = false, insertable = false, updatable = false)
	private TcmPerdiet tcmPerdiet;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
	private Users users;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tId", nullable = false, insertable = false, updatable = false)
	private TcmYuanliao tcmYuanliao;
	@Column(name = "amount")
	private Integer amount;
	@Temporal(TemporalType.DATE)
	@Column(name = "insertdate", length = 10)
	private Date insertdate;
	
	public TcmPerrecord() {}
	
	public TcmPerrecord(TcmPerrecordId tcmPgId, TcmPerdiet tcmPerdiet, Users users, TcmYuanliao tcmYuanliao,
			Integer amount, Date insertdate) {
		super();
		this.tcmPgId = tcmPgId;
		this.tcmPerdiet = tcmPerdiet;
		this.users = users;
		this.tcmYuanliao = tcmYuanliao;
		this.amount = amount;
		this.insertdate = insertdate;
	}



	public TcmPerrecordId getTcmPgId() {
		return tcmPgId;
	}

	public void setTcmPgId(TcmPerrecordId tcmPgId) {
		this.tcmPgId = tcmPgId;
	}

	public TcmPerdiet getTcmPerdiet() {
		return tcmPerdiet;
	}

	public void setTcmPerdiet(TcmPerdiet tcmPerdiet) {
		this.tcmPerdiet = tcmPerdiet;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
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

	public Date getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}
	
}
