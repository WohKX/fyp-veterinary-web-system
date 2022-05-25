package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.CHOperationTime;
import com.apufyp.wohkx.vetassociatesystem.model.ClinicHospital;

@Repository
public interface CHOperationTimeRepository extends JpaRepository<CHOperationTime, Long> {

	List<CHOperationTime> findByClinicHospital(ClinicHospital clinicHospital);

	List<CHOperationTime> findByClinicHospitalAndDay(ClinicHospital clinicHospital, String day);

	CHOperationTime getByClinicHospitalAndDay(ClinicHospital clinicHospital, String day);
}
