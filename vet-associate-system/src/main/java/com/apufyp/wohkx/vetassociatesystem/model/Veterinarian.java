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
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "userVet")
@EntityListeners(AuditingEntityListener.class)
@Access(value = AccessType.FIELD)
public class Veterinarian implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7956792073795280016L;

	@Id
	@Column(name = "userId", length = 80, nullable = false)
	private String id;

	@Column(name = "userIc", length = 15, nullable = false)
	private String userIc;

	@Column(name = "realName", length = 50, nullable = false)
	private String realName;

	@Column(name = "email", length = 80)
	private String email;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chId", referencedColumnName = "chId")
	private ClinicHospital chId;

	@Column(name = "phoneNo", length = 15)
	private String phoneNo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@MapsId
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;

	public Veterinarian() {
		super();
	}

	public Veterinarian(String id, String userIc, String realName, String email) {
		super();
		this.id = id;
		this.userIc = userIc;
		this.realName = realName;
		this.email = email;
	}

	public Veterinarian(String id, String userIc, String realName, String email, String phoneNo) {
		super();
		this.id = id;
		this.userIc = userIc;
		this.realName = realName;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public Veterinarian(String id, String userIc, String realName, String email, ClinicHospital chId, String phoneNo) {
		super();
		this.id = id;
		this.userIc = userIc;
		this.realName = realName;
		this.email = email;
		this.chId = chId;
		this.phoneNo = phoneNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserIc() {
		return userIc;
	}

	public void setUserIc(String userIc) {
		this.userIc = userIc;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClinicHospital getChId() {
		return chId;
	}

	public void setChId(ClinicHospital chId) {
		this.chId = chId;
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
