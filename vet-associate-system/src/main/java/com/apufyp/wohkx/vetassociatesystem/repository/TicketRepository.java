package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.Ticket;
import com.apufyp.wohkx.vetassociatesystem.model.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByTicketStatus(String ticketStatus, Sort sort);

	@Query("SELECT t FROM Ticket t WHERE t.ticketStatus IN :ticketStatus")
	List<Ticket> findByTicketStatusInOrders(@Param("ticketStatus") List<String> ticketStatus, Sort sort);

	List<Ticket> findByRaiser(User raiser, Sort sort);
	
	Ticket getByRaiserAndTicketType(User raiser, String ticketType);
	
	List<Ticket> findByRaiserAndTicketType(User raiser, String ticketType);
}
