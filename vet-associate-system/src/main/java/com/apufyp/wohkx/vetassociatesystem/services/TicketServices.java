package com.apufyp.wohkx.vetassociatesystem.services;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apufyp.wohkx.vetassociatesystem.constant.TicketStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.constant.TicketTypeEnum;
import com.apufyp.wohkx.vetassociatesystem.model.Admin;
import com.apufyp.wohkx.vetassociatesystem.model.Ticket;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.repository.TicketRepository;

@Service
public class TicketServices {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private UserServices userServices;

	@Autowired
	private AdminServices adminServices;

	private static final Logger logger = LoggerFactory.getLogger(TicketServices.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public Ticket findById(String id) {
		logger.info("Searching for ticket by Id: " + id);
		return ticketRepository.findById(Long.parseLong(id)).get();
	}

	public String updateTicketStatus(String id, String ticketStatus, String adminID) {
		Admin admin = adminServices.findById(adminID);
		Ticket ticket = findById(id);
		ticket.setTicketStatus(ticketStatus);
		ticket.setResponser(admin);
		logger.info("Updating ticket status: " + id + " set status to " + ticketStatus);
		ticketRepository.saveAndFlush(ticket);
		return ("The ticket has been " + ticketStatus);
	}

	public void completeTicketVet(String userId, TicketStatusEnum status) {
		User raiser = userServices.findById(userId);
		if (ticketRepository.findByRaiserAndTicketType(raiser, TicketTypeEnum.Veterinarian_New_Acc.toString())
				.size() > 0) {
			Ticket ticket = ticketRepository.getByRaiserAndTicketType(raiser,
					TicketTypeEnum.Veterinarian_New_Acc.toString());
			ticket.setTicketStatus(status.getCode());
		}
	}

	public String findRaisorById(String id) {
		logger.info("Finding raisor for : " + id);
		Ticket ticket = findById(id);
		return ticket.getRaiser().getId();
	}

	public List<Ticket> findByTicketStatusOrders(String ticketStatus) {
		List<Ticket> ticketList = new LinkedList<Ticket>();
		List<Ticket> tickets = ticketRepository.findByTicketStatus(ticketStatus, Sort.by("ticketDate"));
		logger.info("Finding ticket with status: " + ticketStatus);
		if (tickets.size() > 0) {
			for (Ticket t : tickets) {
				t.setTimeString(dateFormat.format(t.getTicketDate()));
			}
			ticketList.addAll(tickets);
		}
		return ticketList;
	}

	public List<Ticket> findByTicketStatusInOrders(List<String> ticketStatus) {
		List<Ticket> ticketList = new LinkedList<Ticket>();
		List<Ticket> tickets = ticketRepository.findByTicketStatusInOrders(ticketStatus, Sort.by("ticketDate"));
		logger.info("Finding ticket with status: " + ticketStatus);
		if (tickets.size() > 0) {
			for (Ticket t : tickets) {
				t.setTimeString(dateFormat.format(t.getTicketDate()));
			}
			ticketList.addAll(tickets);
		}
		return ticketList;
	}

	public List<Ticket> findByRaiserOrders(String raiserId) {
		List<Ticket> ticketList = new LinkedList<Ticket>();
		User raiser = userServices.findById(raiserId);
		List<Ticket> tickets = ticketRepository.findByRaiser(raiser, Sort.by("ticketDate"));
		logger.info("Finding ticket raised by: " + raiserId);
		if (tickets.size() > 0) {
			for (Ticket t : tickets) {
				t.setTimeString(dateFormat.format(t.getTicketDate()));
			}
			ticketList.addAll(tickets);
		}
		return ticketList;
	}

	public void create(Ticket ticket) {
		logger.info("Creating new ticket: " + ticket.getId());
		ticketRepository.saveAndFlush(ticket);
	}
}
