package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "userOwner")
@EntityListeners(AuditingEntityListener.class)
@Access(value = AccessType.FIELD)
public class PetOwner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3081732531422834800L;

	@Id
	@Column(name = "userId", length = 80, nullable = false)
	private String id;

	@Column(name = "username", length = 50, nullable = false)
	private String username;

	@Column(name = "street", length = 50)
	private String street;

	@Column(name = "poscode", length = 5)
	private String poscode;

	@Column(name = "district", length = 20)
	private String district;

	@Column(name = "states", length = 20)
	private String states;

	@Column(name = "phoneNo", length = 15)
	private String phoneNo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@MapsId
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;

	public PetOwner() {
		super();
	}

	public PetOwner(String id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public PetOwner(String id, String username, User user) {
		super();
		this.id = id;
		this.username = username;
		this.user = user;
	}

	public PetOwner(String id, String username, String street, String poscode, String district, String states,
			String phoneNo) {
		super();
		this.id = id;
		this.username = username;
		this.street = street;
		this.poscode = poscode;
		this.district = district;
		this.states = states;
		this.phoneNo = phoneNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPoscode() {
		return poscode;
	}

	public void setPoscode(String poscode) {
		this.poscode = poscode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
