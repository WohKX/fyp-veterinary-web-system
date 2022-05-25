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
@Table(name = "chComments")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "commentTime" }, allowGetters = true)
public class CHComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8565325267454413110L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commentId")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chId", referencedColumnName = "chId", nullable = false)
	private ClinicHospital chId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "writter", referencedColumnName = "userId", nullable = false)
	private User writter;

	@Column(name = "descriptions", length = 600, nullable = false)
	private String descriptions;

	@Column(name = "ratings")
	private double ratings;

	@Column(name = "commentTime", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date commentTime;

	@Column(name = "timeString")
	private String timeString;

	public CHComment() {
		super();
	}

	public CHComment(String descriptions, double ratings) {
		super();
		this.descriptions = descriptions;
		this.ratings = ratings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClinicHospital getChId() {
		return chId;
	}

	public void setChId(ClinicHospital chId) {
		this.chId = chId;
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

	public double getRatings() {
		return ratings;
	}

	public void setRatings(double ratings) {
		this.ratings = ratings;
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
