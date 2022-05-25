package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author wohkx
 *
 */
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonSerialize
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3225777502064525429L;

	@Id
	@Column(name = "userId", length = 80, nullable = false)
	private String id;

	@Column(name = "usertype", length = 10, nullable = false)
	private String usertype;

	@Column(name = "passw", length = 25, nullable = false)
	private String password;

	@Column(name = "accStatus", length = 15, nullable = false)
	private String accStatus;

	@Column(name = "verification_code", length = 64)
	private String verificationCode;

	@Column(name = "name", length = 50)
	private String name;

	public User() {
		super();
	}

	public User(String id, String usertype, String password, String accStatus, String name) {
		super();
		this.id = id;
		this.usertype = usertype;
		this.password = password;
		this.accStatus = accStatus;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
