package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, String> {

	@Query("select p from PetOwner p where lower(p.username) like lower(concat('%', :username,'%'))")
	List<PetOwner> findByUsernameContainingIgnoreCase(@Param("username") String username);

	Optional<PetOwner> findById (String id);
}
