package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.CHComment;
import com.apufyp.wohkx.vetassociatesystem.model.ClinicHospital;

@Repository
public interface CHCommentRepository extends JpaRepository<CHComment, Long>{

	List<CHComment> findByChIdOrderByCommentTimeDesc (ClinicHospital clinicHospital);
	
}
