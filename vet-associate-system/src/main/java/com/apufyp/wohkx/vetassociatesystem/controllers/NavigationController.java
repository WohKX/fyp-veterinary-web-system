package com.apufyp.wohkx.vetassociatesystem.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apufyp.wohkx.vetassociatesystem.constant.TicketStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.constant.UCHStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.Admin;
import com.apufyp.wohkx.vetassociatesystem.model.CHDetail;
import com.apufyp.wohkx.vetassociatesystem.model.ChatRoom;
import com.apufyp.wohkx.vetassociatesystem.model.Chatbot;
import com.apufyp.wohkx.vetassociatesystem.model.ClinicHospital;
import com.apufyp.wohkx.vetassociatesystem.model.Ticket;
import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.services.AdminServices;
import com.apufyp.wohkx.vetassociatesystem.services.AppointmentServices;
import com.apufyp.wohkx.vetassociatesystem.services.ChatRoomServices;
import com.apufyp.wohkx.vetassociatesystem.services.ChatbotServices;
import com.apufyp.wohkx.vetassociatesystem.services.ClinicHospitalServices;
import com.apufyp.wohkx.vetassociatesystem.services.PetOwnerServices;
import com.apufyp.wohkx.vetassociatesystem.services.SocialNetworkServices;
import com.apufyp.wohkx.vetassociatesystem.services.TicketServices;
import com.apufyp.wohkx.vetassociatesystem.services.VeterinarianServices;

@Controller
@RequestMapping(path = "/nav")
public class NavigationController {
	@Autowired
	private TicketServices ticketServices;

	@Autowired
	private PetOwnerServices petOwnerServices;

	@Autowired
	private VeterinarianServices veterinarianServices;

	@Autowired
	private ClinicHospitalServices clinicHospitalServices;

	@Autowired
	private ChatbotServices chatbotServices;

	@Autowired
	private AppointmentServices appointmentServices;

	@Autowired
	private AdminServices adminServices;

	@Autowired
	private ChatRoomServices chatRoomServices;

	@Autowired
	private SocialNetworkServices socialNetworkServices;

	@GetMapping("/admin")
	public String adminNav(@RequestParam("adminNavButton") String adminNavButton,
			@RequestParam(name = "msg", required = false) String msg, ModelMap map, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			switch (adminNavButton) {
			case "Tickets": {
				List<String> completeTicketStatus = Arrays.asList(TicketStatusEnum.Completed.getCode(),
						TicketStatusEnum.Rejected.getCode());
				List<Ticket> newTickets = ticketServices
						.findByTicketStatusOrders(TicketStatusEnum.New_Raised.getCode());
				List<Ticket> completedTickets = ticketServices.findByTicketStatusInOrders(completeTicketStatus);
				List<Ticket> reviewedTickets = ticketServices
						.findByTicketStatusOrders(TicketStatusEnum.Reviewed.getCode());
				map.addAttribute("newTickets", newTickets);
				map.addAttribute("completedTickets", completedTickets);
				map.addAttribute("reviewedTickets", reviewedTickets);
				String infoMsg = TicketStatusEnum.getDescriptionMsg();
				map.addAttribute("infoMsg", infoMsg);
				return "admin/tickets/ticketList";
			}
			case "ManagePetOwner": {
				List<PetOwner> petOwnerList = petOwnerServices.findAll();
				map.addAttribute("petOwnerList", petOwnerList);
				return "admin/manageUCH/petOwnerList";
			}
			case "ManageVeterinarian": {
				List<Veterinarian> veterinarianList = veterinarianServices.findAll();
				map.addAttribute("veterinarianList", veterinarianList);
				return "admin/manageUCH/veterinarianList";
			}
			case "ManageCH": {
				List<ClinicHospital> chProfileList = clinicHospitalServices.findAll();
				map.addAttribute("chProfileList", chProfileList);
				return "admin/manageUCH/chList";
			}
			case "ManageChatbot": {
				List<Chatbot> chatbotList = chatbotServices.findAll();
				map.addAttribute("chatbotList", chatbotList);
				if (msg != null || msg != "") {
					map.addAttribute("msg", msg);
				}
				return "admin/chatbot/chatbotList";
			}
			case "CreateAcc": {
				if (msg != null || msg != "") {
					map.addAttribute("msg", msg);
				}
				return "admin/acc/createAcc";
			}
			case "Logout": {
				session.invalidate();
				return "admin/acc/login";
			}
			default: {
				Admin admin = adminServices.findById(UID);
				map.addAllAttributes(socialNetworkServices.get20Posts());
				map.addAttribute("admin", admin);
				return "admin/posts/home";
			}
			}
		} else {
			return "redirect:/admin";
		}

	}

	@GetMapping("/petOwner")
	public String petOwnerNav(@RequestParam("ownerNavButton") String ownerNavButton,
			@RequestParam(name = "msg", required = false) String msg, ModelMap map, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			switch (ownerNavButton) {
			case "SearchCH": {
				List<ClinicHospital> chProfileList = clinicHospitalServices
						.findByCHStatus(UCHStatusEnum.Activated.getCode());
				map.addAttribute("chProfileList", chProfileList);
				return "petOwner/vetCH/chList";
			}
			case "SearchVet": {
				List<Veterinarian> veterinarianList = veterinarianServices.getActiveVetAcc();
				map.addAttribute("veterinarianList", veterinarianList);
				return "petOwner/vetCH/veterinarianList";
			}
			case "Conversation": {
				List<ChatRoom> chatRoomList = chatRoomServices.getChatRoomListByUser(UID);
				if (chatRoomList != null) {
					chatRoomServices.updateMessageStatusUponNotified(chatRoomList);
				}
				map.addAttribute("UID", UID);
				map.addAttribute("chatRoomList", chatRoomList);
				return "petOwner/chats/chatRoomList";
			}
			case "Profile": {
				PetOwner petOwner = petOwnerServices.findById(UID);
				map.addAttribute("petOwner", petOwner);
				return "petOwner/personal/editProfile";

			}
			case "Inquiry": {
				List<Ticket> ticketList = ticketServices.findByRaiserOrders(UID);
				String infoMsg = TicketStatusEnum.getDescriptionMsg();
				map.addAttribute("ticketList", ticketList);
				map.addAttribute("infoMsg", infoMsg);
				if (msg != null || msg != "") {
					map.addAttribute("msg", msg);
				}
				return "petOwner/personal/ticketList";
			}
			case "Appointment": {
				map.addAllAttributes(appointmentServices.findByPetOwner(UID));
				return "petOwner/vetCH/aptList";
			}
			case "Logout": {
				session.invalidate();
				return "petOwner/acc/login";
			}
			default: {
				PetOwner petOwner = petOwnerServices.findById(UID);
				map.addAllAttributes(socialNetworkServices.get20Posts());
				map.addAttribute("petOwner", petOwner);
				return "petOwner/posts/home";
			}
			}
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/vet")
	public String vetNav(@RequestParam("vetNavButton") String vetNavButton,
			@RequestParam(name = "msg", required = false) String msg, ModelMap map, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			switch (vetNavButton) {
			case "MyCH": {
				Veterinarian veterinarian = veterinarianServices.findById(UID);
				ClinicHospital ch = veterinarian.getChId();
				if (ch == null) {
					List<ClinicHospital> chProfileList = clinicHospitalServices
							.findByCHStatus(UCHStatusEnum.Activated.getCode());
					map.addAttribute("chProfileList", chProfileList);
					if (msg != null || msg != "") {
						map.addAttribute("msg", msg);
					}
					return "vet/ch/chList";
				} else {
					map.addAttribute("vetCHId", veterinarian.getChId().getChId());
					map.addAttribute("ch", ch);
					map.addAttribute("operationTimeList", clinicHospitalServices.getOperationTime(ch));
					map.addAttribute("comments", clinicHospitalServices.getCommentByChId(ch));
					if (clinicHospitalServices.checkDetailsExists(ch)) {
						CHDetail detail = clinicHospitalServices.getDetails(ch);
						Veterinarian vet = detail.getOwner();
						map.addAttribute("chOwner", vet);
						if (detail.getOwner().equals(veterinarian)) {
							return "vet/ch/chManage";
						} else {
							return "vet/ch/chProfile";
						}
					} else {
						return "vet/ch/chProfile";
					}
				}
			}
			case "Conversation": {
				List<ChatRoom> chatRoomList = chatRoomServices.getChatRoomListByUser(UID);
				if (chatRoomList != null) {
					chatRoomServices.updateMessageStatusUponNotified(chatRoomList);
				}
				map.addAttribute("UID", UID);
				map.addAttribute("chatRoomList", chatRoomList);
				return "vet/chats/chatRoomList";
			}
			case "Profile": {
				Veterinarian veterinarian = veterinarianServices.findById(UID);
				map.addAttribute("veterinarian", veterinarian);
				return "vet/personal/editProfile";
			}
			case "Inquiry": {
				List<Ticket> ticketList = ticketServices.findByRaiserOrders(UID);
				String infoMsg = TicketStatusEnum.getDescriptionMsg();
				map.addAttribute("ticketList", ticketList);
				map.addAttribute("infoMsg", infoMsg);
				if (msg != null || msg != "") {
					map.addAttribute("msg", msg);
				}
				return "vet/personal/ticketList";
			}
			case "Logout": {
				session.invalidate();
				return "vet/acc/login";
			}
			default: {
				Veterinarian veterinarian = veterinarianServices.findById(UID);
				map.addAllAttributes(socialNetworkServices.get20Posts());
				map.addAttribute("veterinarian", veterinarian);
				return "vet/posts/home";
			}
			}
		} else {
			return "redirect:/vet";
		}

	}

}
