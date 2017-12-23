package com.ustb.food.entity;

// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GuanxiId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class GuanxiId implements java.io.Serializable {

	// Fields

	private Integer viewId;
	private Integer maId;

	// Constructors

	/** default constructor */
	public GuanxiId() {
	}

	/** full constructor */
	public GuanxiId(Integer viewId, Integer maId) {
		this.viewId = viewId;
		this.maId = maId;
	}

	// Property accessors

	@Column(name = "viewId", nullable = false)
	public Integer getViewId() {
		return this.viewId;
	}

	public void setViewId(Integer viewId) {
		this.viewId = viewId;
	}

	@Column(name = "maId", nullable = false)
	public Integer getMaId() {
		return this.maId;
	}

	public void setMaId(Integer maId) {
		this.maId = maId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GuanxiId))
			return false;
		GuanxiId castOther = (GuanxiId) other;

		return ((this.getViewId() == castOther.getViewId()) || (this
				.getViewId() != null && castOther.getViewId() != null && this
				.getViewId().equals(castOther.getViewId())))
				&& ((this.getMaId() == castOther.getMaId()) || (this.getMaId() != null
						&& castOther.getMaId() != null && this.getMaId()
						.equals(castOther.getMaId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getViewId() == null ? 0 : this.getViewId().hashCode());
		result = 37 * result
				+ (getMaId() == null ? 0 : this.getMaId().hashCode());
		return result;
	}

}