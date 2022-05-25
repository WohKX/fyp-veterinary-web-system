package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, String> {

	@Query("select v from Veterinarian v where lower(v.realName) like lower(concat('%', :realName,'%'))")
	List<Veterinarian> findByRealNameContainingIgnoreCase(@Param("realName") String realName);

}
