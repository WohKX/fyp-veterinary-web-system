package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByVerificationCode(String verificationCode);

	List<User> findByAccStatus(String accStatus);
	
	Optional<User> findById(String id);

}
