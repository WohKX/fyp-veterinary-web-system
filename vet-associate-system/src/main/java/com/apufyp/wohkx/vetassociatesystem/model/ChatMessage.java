package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "messaging")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "messageTime" }, allowGetters = true)
@JsonSerialize
public class ChatMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1083094456103413313L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "chatRoom", referencedColumnName = "id", nullable = false)
	private ChatRoom chatRoom;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender", referencedColumnName = "userId", nullable = false)
	private User sender;

	@Column(name = "messageTime", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date messageTime;

	@Column(name = "messageType", length = 5, nullable = false) // text or image
	private String messageType;

	@Column(name = "messageText", length = 255)
	private String messageText;

	@Lob
	@Column(name = "messageImage")
	private String messageImage;

	@Column(name = "messageStatus", length = 10)
	private String messageStatus;

	@Column(name = "timeString")
	private String timeString;

	public ChatMessage() {
		super();
	}

	public ChatMessage(User sender, String messageStatus) {
		super();
		this.sender = sender;
		this.messageStatus = messageStatus;
	}

	public ChatMessage(User sender, String imageType, String messageStatus) {
		super();
		this.sender = sender;
		this.messageStatus = messageStatus;
	}

	public ChatMessage(ChatRoom chatRoomId, User sender, String messageStatus) {
		super();
		this.chatRoom = chatRoomId;
		this.sender = sender;
		this.messageStatus = messageStatus;
	}

	public ChatMessage(ChatRoom chatRoomId, User sender, String imageType, String messageStatus) {
		super();
		this.chatRoom = chatRoomId;
		this.sender = sender;
		this.messageStatus = messageStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChatRoom getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(ChatRoom chatRoomId) {
		this.chatRoom = chatRoomId;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Date getMessageTime() {
		return messageTime;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getMessageImage() {
		return messageImage;
	}

	public void setMessageImage(String messageImage) {
		this.messageImage = messageImage;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

}
