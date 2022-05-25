package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.Admin;
import com.apufyp.wohkx.vetassociatesystem.model.User;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

	Optional<Admin> findById(String id);
	
	Admin findByUser(User user);
	
}
