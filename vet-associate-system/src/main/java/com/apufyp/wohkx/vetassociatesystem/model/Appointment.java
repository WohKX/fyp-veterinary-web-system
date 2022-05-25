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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "appointments")
@EntityListeners(AuditingEntityListener.class)
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7328918092262273769L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointmentId")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chId", referencedColumnName = "chId", nullable = false)
	private ClinicHospital chId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "petOwner", referencedColumnName = "userId", nullable = false)
	private PetOwner petOwner;

	@Column(name = "aptDate", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date aptDate;

	@Column(name = "aptStatus", length = 3, nullable = false)
	private String aptStatus;
	
	@Column(name = "aptTime")
	private String aptTime;
	
	@Column(name = "dateString")
	private String dateString;

	public Appointment() {
		super();
	}

	public Appointment(PetOwner petOwner, Date aptDate, String aptStatus, String aptTime) {
		super();
		this.petOwner = petOwner;
		this.aptDate = aptDate;
		this.aptStatus = aptStatus;
		this.aptTime = aptTime;
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

	public PetOwner getPetOwner() {
		return petOwner;
	}

	public void setPetOwner(PetOwner petOwner) {
		this.petOwner = petOwner;
	}

	public Date getAptDate() {
		return aptDate;
	}

	public void setAptDate(Date aptDate) {
		this.aptDate = aptDate;
	}

	public String getAptStatus() {
		return aptStatus;
	}

	public void setAptStatus(String aptStatus) {
		this.aptStatus = aptStatus;
	}

	public String getAptTime() {
		return aptTime;
	}

	public void setAptTime(String aptTime) {
		this.aptTime = aptTime;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

}
