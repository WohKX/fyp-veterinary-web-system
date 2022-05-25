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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tickets")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "ticketDate" }, allowGetters = true)
public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3164167314876364830L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "responser", referencedColumnName = "userId")
	private Admin responser;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "raiser", referencedColumnName = "userId", nullable = false)
	private User raiser;

	@Column(name = "targeter")
	private String targeter;

	@Column(name = "ticketType", length = 25, nullable = false)
	private String ticketType;

	@Column(name = "ticketTitle", length = 100, nullable = false)
	private String ticketTitle;

	@Column(name = "imageType", length = 20)
	private String imageType;

	@Lob
	@Column(name = "provenImage")
	private String provenImage;

	@Column(name = "descriptions", length = 600)
	private String description;

	@Column(name = "ticketStatus", length = 3, nullable = false)
	private String ticketStatus;

	@Column(name = "ticketDate", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date ticketDate;

	private String timeString;

	public Ticket() {
		super();
	}

	public Ticket(User raiser, String ticketType, String ticketTitle, String description, String ticketStatus) {
		super();
		this.raiser = raiser;
		this.ticketType = ticketType;
		this.ticketTitle = ticketTitle;
		this.description = description;
		this.ticketStatus = ticketStatus;
	}

	public Ticket(User raiser, String targeter, String ticketType, String ticketTitle, String description,
			String ticketStatus) {
		super();
		this.raiser = raiser;
		this.targeter = targeter;
		this.ticketType = ticketType;
		this.ticketTitle = ticketTitle;
		this.description = description;
		this.ticketStatus = ticketStatus;
	}

	public Ticket(User raiser, String ticketType, String ticketTitle, String imageType, String provenImage,
			String description, String ticketStatus) {
		super();
		this.raiser = raiser;
		this.ticketType = ticketType;
		this.ticketTitle = ticketTitle;
		this.imageType = imageType;
		this.provenImage = provenImage;
		this.description = description;
		this.ticketStatus = ticketStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Admin getResponser() {
		return responser;
	}

	public void setResponser(Admin responser) {
		this.responser = responser;
	}

	public User getRaiser() {
		return raiser;
	}

	public void setRaiser(User raiser) {
		this.raiser = raiser;
	}

	public String getTargeter() {
		return targeter;
	}

	public void setTargeter(String targeter) {
		this.targeter = targeter;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getTicketTitle() {
		return ticketTitle;
	}

	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getProvenImage() {
		return provenImage;
	}

	public void setProvenImage(String provenImage) {
		this.provenImage = provenImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Date getTicketDate() {
		return ticketDate;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

}
