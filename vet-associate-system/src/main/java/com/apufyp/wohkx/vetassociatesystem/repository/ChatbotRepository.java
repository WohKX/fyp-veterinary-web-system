package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.Chatbot;

@Repository
public interface ChatbotRepository extends JpaRepository<Chatbot, Long> {
	
	@Query("SELECT DISTINCT(b.classes) FROM Chatbot b")
	List<String> findClassType();
	
	List<Chatbot> findByClasses(String classes);
}
