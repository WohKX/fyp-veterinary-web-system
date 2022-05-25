package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "clinicHospital")
@EntityListeners(AuditingEntityListener.class)
public class ClinicHospital implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5049719661555592396L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chId")
	private Long chId;

	@Column(name = "chName", length = 80, nullable = false)
	private String chName;

	@Column(name = "street", length = 150, nullable = false)
	private String street;

	@Column(name = "poscode", length = 5, nullable = false)
	private String poscode;

	@Column(name = "district", length = 20, nullable = false)
	private String district;

	@Column(name = "states", length = 50, nullable = false)
	private String states;

	@Column(name = "phoneNo", length = 15)
	private String phoneNo;

	@Column(name = "chStatus", length = 15, nullable = false)
	private String chStatus;

	@Column(name = "ratings")
	private double ratings;

	@Column(name = "ratingNo", length = 5, nullable = false)
	private Integer ratingNo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "id")
	@Column(name = "chOperationTime")
	private List<CHOperationTime> operationTime;

	public ClinicHospital() {
		super();
	}

	public ClinicHospital(String chName, String street, String poscode, String district, String states, String chStatus,
			Integer ratingNo) {
		super();
		this.chName = chName;
		this.street = street;
		this.poscode = poscode;
		this.district = district;
		this.states = states;
		this.chStatus = chStatus;
		this.ratingNo = ratingNo;
	}

	public Long getChId() {
		return chId;
	}

	public void setChId(Long id) {
		this.chId = id;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPoscode() {
		return poscode;
	}

	public void setPoscode(String poscode) {
		this.poscode = poscode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getChStatus() {
		return chStatus;
	}

	public void setChStatus(String chStatus) {
		this.chStatus = chStatus;
	}

	public double getRatings() {
		return ratings;
	}

	public void setRatings(double ratings) {
		this.ratings = ratings;
	}

	public Integer getRatingNo() {
		return ratingNo;
	}

	public void setRatingNo(Integer ratingNo) {
		this.ratingNo = ratingNo;
	}

}
