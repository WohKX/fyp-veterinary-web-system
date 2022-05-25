package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "chOperationTime")
@EntityListeners(AuditingEntityListener.class)
public class CHOperationTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5406730021389499058L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chId", referencedColumnName = "chId")
	private ClinicHospital clinicHospital;

	@Column(name = "operationDay", length = 15, nullable = false)
	private String day;

	@Column(name = "operationST", length = 10)
	private String startTime;

	@Column(name = "operationET", length = 10)
	private String endTime;

	public CHOperationTime() {
		super();
	}

	public CHOperationTime(String day, String startTime, String endTime) {
		super();
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public CHOperationTime(ClinicHospital clinicHospital, String day, String startTime, String endTime) {
		super();
		this.clinicHospital = clinicHospital;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClinicHospital getClinicHospital() {
		return clinicHospital;
	}

	public void setClinicHospital(ClinicHospital clinicHospital) {
		this.clinicHospital = clinicHospital;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
