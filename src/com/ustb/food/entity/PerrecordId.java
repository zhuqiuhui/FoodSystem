package com.ustb.food.entity;

import javax.persistence.Column;

public class PerrecordId implements java.io.Serializable {
	
	private Integer pgId;
	private Integer maId;
	private Integer userId;
	
	
	
	public PerrecordId() {
	}
	
	public PerrecordId(Integer pgId, Integer maId, Integer userId) {
		super();
		this.pgId = pgId;
		this.maId = maId;
		this.userId = userId;
	}
	
	@Column(name = "pgId", nullable = false)
	public Integer getPgId() {
		return pgId;
	}
	
	public void setPgId(Integer pgId) {
		this.pgId = pgId;
	}
	
	@Column(name = "maId", nullable = false)
	public Integer getMaId() {
		return maId;
	}
	public void setMaId(Integer maId) {
		this.maId = maId;
	}
	
	@Column(name = "userId", nullable = false)
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
				+ (getPgId() == null ? 0 : this.getPgId().hashCode());
		result = 37 * result
				+ (getMaId() == null ? 0 : this.getMaId().hashCode());
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
		if (!(obj instanceof PerrecordId))
			return false;
		PerrecordId castOther = (PerrecordId) obj;

		return ((this.getPgId() == castOther.getPgId()) || (this
				.getPgId() != null && castOther.getPgId() != null && this
				.getPgId().equals(castOther.getPgId())))
				&& ((this.getMaId() == castOther.getMaId()) || (this.getMaId() != null
						&& castOther.getMaId() != null && this.getMaId()
						.equals(castOther.getMaId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId()
				.equals(castOther.getUserId())));
	}
	
	
	
	
}
