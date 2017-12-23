package com.ustb.food.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.ustb.food.entity.Perrecord;

@Entity(name = "Users")
// @Table(name="Food",schema="dbo.",catalog="FoodS")
public class Users implements java.io.Serializable {

	private int userId;
	private String userName;
	private String userPassword;
	private Set<Perdietcm> perdietcms = new HashSet<Perdietcm>(0);
	private Set<Perrecord> perrecords = new HashSet<Perrecord>(0);
	private Set<Perdiet> perdiets = new HashSet<Perdiet>(0);

	public Users() {

	}

	public Users(String userName, String userPassword) {

		this.userName = userName;
		this.userPassword = userPassword;

	}

	/** full constructor */
	public Users(String userName, String userPassword, Set<Perdietcm> perdietcms, Set<Perrecord> perrecords,
			Set<Perdiet> perdiets) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.perdietcms = perdietcms;
		this.perrecords = perrecords;
		this.perdiets = perdiets;
	}

	@Id
	@GeneratedValue
	@Column(name = "userId", unique = true, nullable = false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "userName", length = 50, nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "userPassword", length = 50, nullable = false)
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Perdietcm> getPerdietcms() {
		return this.perdietcms;
	}

	public void setPerdietcms(Set<Perdietcm> perdietcms) {
		this.perdietcms = perdietcms;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Perrecord> getPerrecords() {
		return this.perrecords;
	}

	public void setPerrecords(Set<Perrecord> perrecords) {
		this.perrecords = perrecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Perdiet> getPerdiets() {
		return this.perdiets;
	}

	public void setPerdiets(Set<Perdiet> perdiets) {
		this.perdiets = perdiets;
	}

}
