package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.Appointment;
import com.apufyp.wohkx.vetassociatesystem.model.ClinicHospital;
import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByAptStatusAndPetOwner(String aptStatus, PetOwner petOwner, Sort sort);

	List<Appointment> findByPetOwner(PetOwner petOwner);
	
	List<Appointment> findByChId(ClinicHospital chId);
	
	List<Appointment> findByAptStatusAndChId(String aptStatus, ClinicHospital ch, Sort sort);
}
