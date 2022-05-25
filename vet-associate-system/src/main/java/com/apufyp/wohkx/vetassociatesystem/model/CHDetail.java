package com.apufyp.wohkx.vetassociatesystem.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "chDetails")
@EntityListeners(AuditingEntityListener.class)
public class CHDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3359696529804658192L;

	@Id
	@Column(name = "chId")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ownerId", referencedColumnName = "userId")
	private Veterinarian owner;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "chId", referencedColumnName = "chId")
	private ClinicHospital clinicHospital;

	public CHDetail() {
		super();
	}
	
	public CHDetail(Long id, Veterinarian owner) {
		super();
		this.id = id;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Veterinarian getOwner() {
		return owner;
	}

	public void setOwner(Veterinarian owner) {
		this.owner = owner;
	}

	public ClinicHospital getClinicHospital() {
		return clinicHospital;
	}

	public void setClinicHospital(ClinicHospital clinicHospital) {
		this.clinicHospital = clinicHospital;
	}

}
