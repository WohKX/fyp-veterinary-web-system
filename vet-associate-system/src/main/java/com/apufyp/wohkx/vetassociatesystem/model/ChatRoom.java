package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author wohkx
 *
 */
@Entity
@Table(name = "chatRoom")
@EntityListeners(AuditingEntityListener.class)
@JsonSerialize
public class ChatRoom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4012600083349947343L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userOne", referencedColumnName = "userId", nullable = false)
	private User userOne;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userTwo", referencedColumnName = "userId", nullable = false)
	private User userTwo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
	@JsonManagedReference
	private List<ChatMessage> messageList;

	@Column(name = "messageTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date messageTime;

	@Column(name = "newMessageCount")
	private Integer newMessageCount;

	public ChatRoom() {
		super();
	}

	public ChatRoom(User userOne, User userTwo) {
		super();
		this.userOne = userOne;
		this.userTwo = userTwo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserOne() {
		return userOne;
	}

	public void setUserOne(User userOne) {
		this.userOne = userOne;
	}

	public User getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(User userTwo) {
		this.userTwo = userTwo;
	}

	public List<ChatMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<ChatMessage> messageList) {
		this.messageList = messageList;
	}

	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}

	public Integer getNewMessageCount() {
		return newMessageCount;
	}

	public void setNewMessageCount(Integer newMessageCount) {
		this.newMessageCount = newMessageCount;
	}

}
