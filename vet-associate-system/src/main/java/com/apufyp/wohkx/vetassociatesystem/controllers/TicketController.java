package com.apufyp.wohkx.vetassociatesystem.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.apufyp.wohkx.vetassociatesystem.constant.TicketStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.constant.TicketTypeEnum;
import com.apufyp.wohkx.vetassociatesystem.constant.UCHStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.CHOperationTime;
import com.apufyp.wohkx.vetassociatesystem.model.ClinicHospital;
import com.apufyp.wohkx.vetassociatesystem.model.Ticket;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.services.ClinicHospitalServices;
import com.apufyp.wohkx.vetassociatesystem.services.TicketServices;
import com.apufyp.wohkx.vetassociatesystem.services.UserServices;

@Controller
@RequestMapping(path = "/ticket")
public class TicketController {

	@Autowired
	private TicketServices ticketServices;

	@Autowired
	private ClinicHospitalServices clinicHospitalServices;

	@Autowired
	private UserServices userServices;

	@GetMapping("/admin/view")
	public String adminViewTicket(@RequestParam("ticketId") String ticketId, ModelMap map, HttpServletRequest request) {

//		String user = (String) request.getSession(false).getAttribute("UID_Session");
		Ticket ticket = ticketServices.findById(ticketId);
		map.addAttribute("ticket", ticket);
		if (ticket.getTicketType().equals(TicketTypeEnum.CH_Change_Owner.toString())
				|| ticket.getTicketType().equals(TicketTypeEnum.CH_Registration.toString())) {
			ClinicHospital ch = clinicHospitalServices.findById(ticket.getTargeter());
			map.addAttribute("ch", ch);
		}
		return "admin/tickets/ticketManage";
	}

	@GetMapping("/admin/vetAcc")
	public String adminManageVet(@RequestParam("ticketId") String ticketId,
			@RequestParam("manageTicketButton") String manageTicketButton, ModelMap map, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String msg = "";
			String UID = (String) session.getAttribute(session.getId());
			switch (manageTicketButton) {
			case "Approve": {
				msg = ticketServices.updateTicketStatus(ticketId, TicketStatusEnum.Completed.getCode(), UID);
				userServices.updateUserStatus(ticketServices.findRaisorById(ticketId),
						UCHStatusEnum.Activated.getCode());
				break;
			}
			case "Review": {
				msg = ticketServices.updateTicketStatus(ticketId, TicketStatusEnum.Reviewed.getCode(), UID);
				break;
			}
			case "Reject": {
				msg = ticketServices.updateTicketStatus(ticketId, TicketStatusEnum.Rejected.getCode(), UID);
				userServices.updateUserStatus(ticketServices.findRaisorById(ticketId), UCHStatusEnum.Blocked.getCode());
				break;
			}
			}
			String infoMsg = TicketStatusEnum.getDescriptionMsg();
			map.addAttribute("infoMsg", infoMsg);
			map.addAttribute("msg", msg);
			map.addAllAttributes(getAllTickets());
			return "admin/tickets/ticketList";
		} else {
			return "redirect:/admin";
		}

	}

	@GetMapping("/admin/manageCH")
	public String adminManageCH(@RequestParam("ticketId") String ticketId,
			@RequestParam("manageTicketButton") String manageTicketButton, ModelMap map, HttpServletRequest request) {
		Ticket ticket = ticketServices.findById(ticketId);
		HttpSession session = request.getSession(false);
		if (session != null) {
			String msg = "";
			String UID = (String) session.getAttribute(session.getId());
			switch (manageTicketButton) {
			case "Approve": {
				msg = ticketServices.updateTicketStatus(ticketId, TicketStatusEnum.Completed.getCode(), UID);
				clinicHospitalServices.approveCH(ticket.getTargeter(), ticket.getRaiser().getId());
				break;
			}
			case "Review": {
				msg = ticketServices.updateTicketStatus(ticketId, TicketStatusEnum.Reviewed.getCode(), UID);
				break;
			}
			case "Reject": {
				msg = ticketServices.updateTicketStatus(ticketId, TicketStatusEnum.Rejected.getCode(), UID);
				if (ticket.getTicketType().equals(TicketTypeEnum.CH_Registration.toString())) {
					ClinicHospital ch = clinicHospitalServices.findById(ticket.getTargeter());
					ch.setChStatus(UCHStatusEnum.Blocked.getCode());
					clinicHospitalServices.updateCHProfile(ch);
				}
				break;
			}
			}
			String infoMsg = TicketStatusEnum.getDescriptionMsg();
			map.addAttribute("infoMsg", infoMsg);
			map.addAttribute("msg", msg);
			map.addAllAttributes(getAllTickets());
			return "admin/tickets/ticketList";
		} else {
			return "redirect:/admin";
		}
	}

	@GetMapping("/admin/defaults")
	public String adminManageDefault(@RequestParam("ticketId") String ticketId,
			@RequestParam("manageTicketButton") String manageTicketButton, ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String msg = "";
			String UID = (String) session.getAttribute(session.getId());
			switch (manageTicketButton) {
			case "Approve": {
				msg = ticketServices.updateTicketStatus(ticketId, TicketStatusEnum.Completed.getCode(), UID);
				break;
			}
			case "Review": {
				msg = ticketServices.updateTicketStatus(ticketId, TicketStatusEnum.Reviewed.getCode(), UID);
				break;
			}
			case "Reject": {
				msg = ticketServices.updateTicketStatus(ticketId, TicketStatusEnum.Rejected.getCode(), UID);
				break;
			}
			}
			String infoMsg = TicketStatusEnum.getDescriptionMsg();
			map.addAttribute("infoMsg", infoMsg);
			map.addAttribute("msg", msg);
			map.addAllAttributes(getAllTickets());
			return "admin/tickets/ticketList";
		} else {
			return "redirect:/admin";
		}
	}

	@PostMapping("/petOwner/create")
	public String petOwnerInquiry(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam(name = "provenImage", required = false) MultipartFile provenImage, HttpServletRequest request)
			throws IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String msg = null;
			String UID = (String) session.getAttribute(session.getId());
			Ticket ticket = new Ticket(userServices.findById(UID), TicketTypeEnum.Pet_Owner_Inquiry.toString(), title,
					description, TicketStatusEnum.New_Raised.getCode());
			if (!provenImage.isEmpty()) {
				String contentType = provenImage.getContentType();
				byte[] data = provenImage.getBytes();
				String imageString = Base64.getEncoder().encodeToString(data);
				ticket.setImageType(contentType);
				ticket.setProvenImage(imageString);
			}
			ticketServices.create(ticket);
			msg = "You have submit your inquiry, please check it in your inquiry list.";
			return "redirect:/nav/petOwner?ownerNavButton=Inquiry&msg=" + msg;
		} else {
			return "redirect:/";
		}

	}

	@PostMapping("/vet/create")
	public String vetInquiry(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam(name = "provenImage", required = false) MultipartFile provenImage, ModelMap map,
			HttpServletRequest request) throws IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String msg = null;
			String UID = (String) session.getAttribute(session.getId());
			Ticket ticket = new Ticket(userServices.findById(UID), TicketTypeEnum.Veterinarian_Inquiry.toString(),
					title, description, TicketStatusEnum.New_Raised.getCode());
			if (!provenImage.isEmpty()) {
				String contentType = provenImage.getContentType();
				byte[] data = provenImage.getBytes();
				String imageString = Base64.getEncoder().encodeToString(data);
				ticket.setImageType(contentType);
				ticket.setProvenImage(imageString);
			}
			ticketServices.create(ticket);
			msg = "You have submit your inquiry,<br> please check it in your inquiry list.";
			return "redirect:/nav/vet?vetNavButton=Inquiry&msg=" + msg;
		} else {
			return "redirect:/vet";
		}
	}

	@PostMapping("/vet/addAsOwner")
	public String vetRequestAsOwner(@RequestParam("chId") String chId, @RequestParam("description") String description,
			@RequestParam("provenImage") MultipartFile provenImage, ModelMap map, HttpServletRequest request)
			throws IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String msg = null;
			String UID = (String) session.getAttribute(session.getId());
			User chOwner = userServices.findById(UID);
			ClinicHospital clinicHospital = clinicHospitalServices.findById(chId);
			Ticket ticket = new Ticket(chOwner, chId, TicketTypeEnum.CH_Change_Owner.toString(),
					(TicketTypeEnum.CH_Change_Owner.getDescription() + clinicHospital.getChName()), description,
					TicketStatusEnum.New_Raised.getCode());
			String contentType = provenImage.getContentType();
			byte[] data = provenImage.getBytes();
			String imageString = Base64.getEncoder().encodeToString(data);
			ticket.setImageType(contentType);
			ticket.setProvenImage(imageString);
			ticketServices.create(ticket);
			msg = "You have submit the form,<br> please check it in your enquiry list.";
			return "redirect:/nav/vet?vetNavButton=Inquiry&msg=" + msg;
		} else {
			return "redirect:/vet";
		}
	}

	@PostMapping("/vet/addCH")
	public String vetAddCH(@RequestParam("chName") String chName, @RequestParam("street") String street,
			@RequestParam("poscode") String poscode, @RequestParam("district") String district,
			@RequestParam("states") String states, @RequestParam(name = "phoneNo", required = false) String phoneNo,
			@RequestParam("provenImage") MultipartFile provenImage,
			@RequestParam(name = "startTimeMonday", required = false, defaultValue = "00:00") String startTimeMonday,
			@RequestParam(name = "endTimeMonday", required = false, defaultValue = "00:00") String endTimeMonday,
			@RequestParam(name = "startTimeTuesday", required = false, defaultValue = "00:00") String startTimeTuesday,
			@RequestParam(name = "endTimeTuesday", required = false, defaultValue = "00:00") String endTimeTuesday,
			@RequestParam(name = "startTimeWednesday", required = false, defaultValue = "00:00") String startTimeWednesday,
			@RequestParam(name = "endTimeWednesday", required = false, defaultValue = "00:00") String endTimeWednesday,
			@RequestParam(name = "startTimeThursday", required = false, defaultValue = "00:00") String startTimeThursday,
			@RequestParam(name = "endTimeThursday", required = false, defaultValue = "00:00") String endTimeThursday,
			@RequestParam(name = "startTimeFriday", required = false, defaultValue = "00:00") String startTimeFriday,
			@RequestParam(name = "endTimeFriday", required = false, defaultValue = "00:00") String endTimeFriday,
			@RequestParam(name = "startTimeSaturday", required = false, defaultValue = "00:00") String startTimeSaturday,
			@RequestParam(name = "endTimeSaturday", required = false, defaultValue = "00:00") String endTimeSaturday,
			@RequestParam(name = "startTimeSunday", required = false, defaultValue = "00:00") String startTimeSunday,
			@RequestParam(name = "endTimeSunday", required = false, defaultValue = "00:00") String endTimeSunday,
			@RequestParam(name = "description", required = false) String description, ModelMap map,
			HttpServletRequest request) throws IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String msg = null;
			String UID = (String) session.getAttribute(session.getId());
			User raisor = userServices.findById(UID);
			ClinicHospital ch = new ClinicHospital(chName, street, poscode, district, states,
					UCHStatusEnum.Processing.getCode(), 0);
			ch.setPhoneNo(phoneNo);
			clinicHospitalServices.create(ch);
			Ticket ticket = new Ticket(raisor, Long.toString(ch.getChId()), TicketTypeEnum.CH_Registration.toString(),
					(TicketTypeEnum.CH_Registration.getDescription() + ch.getChName()), description,
					TicketStatusEnum.New_Raised.getCode());
			String contentType = provenImage.getContentType();
			byte[] data = provenImage.getBytes();
			String imageString = Base64.getEncoder().encodeToString(data);
			ticket.setImageType(contentType);
			ticket.setProvenImage(imageString);
			ticketServices.create(ticket);
			List<CHOperationTime> operationTimeList = saveOperationTime(startTimeMonday, endTimeMonday,
					startTimeTuesday, endTimeTuesday, startTimeWednesday, endTimeWednesday, startTimeThursday,
					endTimeThursday, startTimeFriday, endTimeFriday, startTimeSaturday, endTimeSaturday,
					startTimeSunday, endTimeSunday);
			clinicHospitalServices.saveAllOperationTime(operationTimeList, ch);
			msg = "You have submit the form,<br> please check it in your enquiry list.";
			return "redirect:/nav/vet?vetNavButton=MyCH&msg=" + msg;
		} else {
			return "redirect:/vet";
		}
	}

	@GetMapping("/vet/personal/raiseTicket")
	public String vetRaiseTicket() {
		return "vet/personal/raiseTicket";
	}

	@GetMapping("/petOwner/personal/raiseTicket")
	public String petOwnerRaiseTicket() {
		return "petOwner/personal/raiseTicket";
	}

	private ModelMap getAllTickets() {
		ModelMap map = new ModelMap();
		List<String> completeTicketStatus = Arrays.asList(TicketStatusEnum.Completed.getCode(),
				TicketStatusEnum.Rejected.getCode());
		List<Ticket> newTickets = ticketServices.findByTicketStatusOrders(TicketStatusEnum.New_Raised.getCode());
		List<Ticket> completedTickets = ticketServices.findByTicketStatusInOrders(completeTicketStatus);
		List<Ticket> reviewedTickets = ticketServices.findByTicketStatusOrders(TicketStatusEnum.Reviewed.getCode());
		map.addAttribute("newTickets", newTickets);
		map.addAttribute("completedTickets", completedTickets);
		map.addAttribute("reviewedTickets", reviewedTickets);
		return map;
	}

	private List<CHOperationTime> saveOperationTime(String startTimeMonday, String endTimeMonday,
			String startTimeTuesday, String endTimeTuesday, String startTimeWednesday, String endTimeWednesday,
			String startTimeThursday, String endTimeThursday, String startTimeFriday, String endTimeFriday,
			String startTimeSaturday, String endTimeSaturday, String startTimeSunday, String endTimeSunday) {
		List<CHOperationTime> timeList = new ArrayList<CHOperationTime>();
		CHOperationTime time1 = new CHOperationTime(null, "Monday", startTimeMonday, endTimeMonday);
		CHOperationTime time2 = new CHOperationTime(null, "Tuesday", startTimeTuesday, endTimeTuesday);
		CHOperationTime time3 = new CHOperationTime(null, "Wednesday", startTimeWednesday, endTimeWednesday);
		CHOperationTime time4 = new CHOperationTime(null, "Thursday", startTimeThursday, endTimeThursday);
		CHOperationTime time5 = new CHOperationTime(null, "Friday", startTimeFriday, endTimeFriday);
		CHOperationTime time6 = new CHOperationTime(null, "Saturday", startTimeSaturday, endTimeSaturday);
		CHOperationTime time7 = new CHOperationTime(null, "Sunday", startTimeSunday, endTimeSunday);
		timeList.add(time1);
		timeList.add(time2);
		timeList.add(time3);
		timeList.add(time4);
		timeList.add(time5);
		timeList.add(time6);
		timeList.add(time7);
		return timeList;
	}
}
