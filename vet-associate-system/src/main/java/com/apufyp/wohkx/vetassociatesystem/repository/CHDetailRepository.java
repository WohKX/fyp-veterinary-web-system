package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.CHDetail;
import com.apufyp.wohkx.vetassociatesystem.model.ClinicHospital;

@Repository
public interface CHDetailRepository extends JpaRepository<CHDetail, Long> {

	CHDetail getByClinicHospital(ClinicHospital clinicHospital);

	List<CHDetail> findByClinicHospital(ClinicHospital clinicHospital);
}
