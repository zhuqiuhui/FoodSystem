package com.ustb.food.entity;

import javax.persistence.Column;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月1日 下午4:49:57
 */
public class TcmPerrecordId implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="tpId")
	private Integer tpId;
	@Column(name="tId")
	private Integer tId;
	@Column(name="userId")
	private Integer userId;
	
	public TcmPerrecordId() {}

	public TcmPerrecordId(Integer tpId, Integer tId, Integer userId) {
		super();
		this.tpId = tpId;
		this.tId = tId;
		this.userId = userId;
	}

	public Integer getTpId() {
		return tpId;
	}

	public void setTpId(Integer tpId) {
		this.tpId = tpId;
	}

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTpId() == null ? 0 : this.getTpId().hashCode());
		result = 37 * result
				+ (gettId() == null ? 0 : this.gettId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ((this == obj))
			return true;
		if ((obj == null))
			return false;
		if (!(obj instanceof TcmPerrecordId))
			return false;
		TcmPerrecordId castOther = (TcmPerrecordId) obj;

		return ((this.getTpId() == castOther.getTpId()) || (this
				.getTpId() != null && castOther.getTpId() != null && this
				.getTpId().equals(castOther.getTpId())))
				&& ((this.gettId() == castOther.gettId()) || (this.gettId() != null
						&& castOther.gettId() != null && this.gettId()
						.equals(castOther.gettId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId()
				.equals(castOther.getUserId())));
	}
}
