package com.ustb.food.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TcmGuanxiId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="tcmId")
	private Integer tcmId;
	@Column(name="tId")
	private Integer tId;
	
	public TcmGuanxiId() {}
	
	public TcmGuanxiId(Integer tcmId, Integer tId) {
		super();
		this.tcmId = tcmId;
		this.tId = tId;
	}

	public Integer getTcmId() {
		return tcmId;
	}

	public void setTcmId(Integer tcmId) {
		this.tcmId = tcmId;
	}

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTcmId() == null ? 0 : this.getTcmId().hashCode());
		result = 37 * result
				+ (gettId() == null ? 0 : this.gettId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ((this == obj))
			return true;
		if ((obj == null))
			return false;
		if (!(obj instanceof TcmGuanxiId))
			return false;
		TcmGuanxiId castOther = (TcmGuanxiId) obj;

		return ((this.getTcmId() == castOther.getTcmId()) || (this
				.getTcmId() != null && castOther.getTcmId() != null && this
				.getTcmId().equals(castOther.getTcmId())))
				&& ((this.gettId() == castOther.gettId()) || (this.gettId() != null
						&& castOther.gettId() != null && this.gettId()
						.equals(castOther.gettId())));
	} 
	
}
