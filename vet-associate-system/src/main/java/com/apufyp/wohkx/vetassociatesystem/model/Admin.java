package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "admins")
@EntityListeners(AuditingEntityListener.class)
public class Admin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2550606453484348144L;

	@Id
	@Column(name = "userIc", length = 20)
	private String id;

	@Column(name = "realName", length = 50, nullable = false)
	private String realName;

	@Column(name = "username", length = 15, nullable = false)
	private String username;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;
	
	public Admin() {
		super();
	}

	public Admin(String id, String realName, String username, User user) {
		super();
		this.id = id;
		this.realName = realName;
		this.username = username;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
