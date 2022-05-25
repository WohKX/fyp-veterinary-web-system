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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "snComments")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "commentTime" }, allowGetters = true)
public class SNComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6829495896035717172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commentId")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "snId", referencedColumnName = "snId", nullable = false)
	private SocialNetwork snId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "writter", referencedColumnName = "userId", nullable = false)
	private User writter;

	@Column(name = "descriptions", length = 255, nullable = false)
	private String descriptions;

	@Column(name = "commentTime", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date commentTime;

	@Column(name = "timeString")
	private String timeString;

	public SNComment() {
		super();
	}

	public SNComment(SocialNetwork snId, User writter, String descriptions) {
		super();
		this.snId = snId;
		this.writter = writter;
		this.descriptions = descriptions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SocialNetwork getSnId() {
		return snId;
	}

	public void setSnId(SocialNetwork snId) {
		this.snId = snId;
	}

	public User getWritter() {
		return writter;
	}

	public void setWritter(User writter) {
		this.writter = writter;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

}
