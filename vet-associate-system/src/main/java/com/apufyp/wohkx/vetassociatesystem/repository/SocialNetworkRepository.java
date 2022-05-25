package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.SocialNetwork;
import com.apufyp.wohkx.vetassociatesystem.model.User;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long>{
	
	@Query("select n from SocialNetwork n where lower(n.descriptions) like lower(concat('%', :descriptions,'%'))")
	List<SocialNetwork> findByDescriptionsContainingIgnoreCase(@Param("descriptions") String descriptions);
	
	List<SocialNetwork> findByWritter(User user);
	
	List<SocialNetwork> findTop20ByOrderBySnTimeDesc();
}
