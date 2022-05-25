package com.apufyp.wohkx.vetassociatesystem.controllers;

import java.util.ArrayList;
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

import com.apufyp.wohkx.vetassociatesystem.constant.TicketStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.constant.UCHStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.CHComment;
import com.apufyp.wohkx.vetassociatesystem.model.CHDetail;
import com.apufyp.wohkx.vetassociatesystem.model.CHOperationTime;
import com.apufyp.wohkx.vetassociatesystem.model.ClinicHospital;
import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.services.AppointmentServices;
import com.apufyp.wohkx.vetassociatesystem.services.ClinicHospitalServices;
import com.apufyp.wohkx.vetassociatesystem.services.PetOwnerServices;
import com.apufyp.wohkx.vetassociatesystem.services.SocialNetworkServices;
import com.apufyp.wohkx.vetassociatesystem.services.TicketServices;
import com.apufyp.wohkx.vetassociatesystem.services.UserServices;
import com.apufyp.wohkx.vetassociatesystem.services.VeterinarianServices;

@Controller
@RequestMapping(path = "/manageUCH")
public class UserCHController {

	@Autowired
	private ClinicHospitalServices clinicHospitalServices;

	@Autowired
	private PetOwnerServices petOwnerServices;

	@Autowired
	private VeterinarianServices veterinarianServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private SocialNetworkServices socialNetworkServices;

	@Autowired
	private AppointmentServices appointmentServices;

	@Autowired
	private TicketServices ticketServices;

	@PostMapping("/admin/viewOwner")
	public String adminViewOwner(@RequestParam("id") String userId, ModelMap map, HttpServletRequest request) {
		PetOwner petOwner = petOwnerServices.findById(userId);
		map.addAttribute("petOwner", petOwner);
		map.addAttribute("uchType", "Pet_Owner");
		return "admin/manageUCH/uchManage";
	}

	@PostMapping("/admin/viewVet")
	public String adminViewVet(@RequestParam("id") String userId, ModelMap map, HttpServletRequest request) {
		Veterinarian veterinarian = veterinarianServices.findById(userId);
		map.addAttribute("veterinarian", veterinarian);
		map.addAttribute("uchType", "Vets");
		return "admin/manageUCH/uchManage";
	}

	@PostMapping("/admin/viewCH")
	public String adminViewCH(@RequestParam("id") String uchId, ModelMap map, HttpServletRequest request) {
		ClinicHospital ch = clinicHospitalServices.findById(uchId);
		List<CHOperationTime> operationTimeList = clinicHospitalServices.getOperationTime(ch);
		map.addAttribute("ch", ch);
		map.addAttribute("uchType", "CH");
		map.addAttribute("operationTimeList", operationTimeList);
		return "admin/manageUCH/uchManage";
	}

	@PostMapping("/admin/petOwner")
	public String adminManageOwner(@RequestParam("manageUCHButton") String manageUCHButton,
			@RequestParam("id") String userId, ModelMap map, HttpServletRequest request) {

		if (manageUCHButton.equals("Activate")) {
			userServices.updateUserStatus(userId, UCHStatusEnum.Activated.getCode());
		} else if (manageUCHButton.equals("Block")) {
			userServices.updateUserStatus(userId, UCHStatusEnum.Blocked.getCode());
		}

		List<PetOwner> petOwnerList = petOwnerServices.findAll();
		map.addAttribute("petOwnerList", petOwnerList);
		return "admin/manageUCH/petOwnerList";

	}

	@PostMapping("/admin/vet")
	public String adminManageVet(@RequestParam("manageUCHButton") String manageUCHButton,
			@RequestParam("id") String userId, ModelMap map, HttpServletRequest request) {
		if (manageUCHButton.equals("Activate")) {
			userServices.updateUserStatus(userId, UCHStatusEnum.Activated.getCode());
			ticketServices.completeTicketVet(userId, TicketStatusEnum.Completed);
		} else if (manageUCHButton.equals("Block")) {
			userServices.updateUserStatus(userId, UCHStatusEnum.Blocked.getCode());
			ticketServices.completeTicketVet(userId, TicketStatusEnum.Rejected);
		}
		List<Veterinarian> veterinarianList = veterinarianServices.findAll();
		map.addAttribute("veterinarianList", veterinarianList);
		return "admin/manageUCH/veterinarianList";

	}

	@PostMapping("/admin/editCH")
	public String adminEditCH(@RequestParam("id") String uchId, @RequestParam("street") String street,
			@RequestParam("poscode") String poscode, @RequestParam("district") String district,
			@RequestParam("states") String states, @RequestParam(name = "phoneNo", required = false) String phoneNo,
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
			@RequestParam("manageUCHButton") String manageUCHButton, ModelMap map, HttpServletRequest request) {
		switch (manageUCHButton) {
		case "Save": {
			ClinicHospital ch = clinicHospitalServices.findById(uchId);
			ch.setStreet(street);
			ch.setPoscode(poscode);
			ch.setDistrict(district);
			ch.setStates(states);
			ch.setPhoneNo(phoneNo);
			clinicHospitalServices.updateCHProfile(ch);
			List<CHOperationTime> operationTimeList = saveOperationTime(startTimeMonday, endTimeMonday,
					startTimeTuesday, endTimeTuesday, startTimeWednesday, endTimeWednesday, startTimeThursday,
					endTimeThursday, startTimeFriday, endTimeFriday, startTimeSaturday, endTimeSaturday,
					startTimeSunday, endTimeSunday);
			clinicHospitalServices.saveAllOperationTime(operationTimeList, ch);
			break;
		}
		case "Activate": {
			ClinicHospital ch = clinicHospitalServices.findById(uchId);
			ch.setChStatus(UCHStatusEnum.Activated.getCode());
			clinicHospitalServices.updateCHProfile(ch);
			break;
		}
		case "Block": {
			ClinicHospital ch = clinicHospitalServices.findById(uchId);
			ch.setChStatus(UCHStatusEnum.Blocked.getCode());
			clinicHospitalServices.updateCHProfile(ch);
			break;
		}
		}
		List<ClinicHospital> chProfileList = clinicHospitalServices.findAll();
		map.addAttribute("chProfileList", chProfileList);
		return "admin/manageUCH/chList";
	}

	@PostMapping("/admin/createCH")
	public String adminCreateCH(@RequestParam("chName") String chName, @RequestParam("street") String street,
			@RequestParam("poscode") String poscode, @RequestParam("district") String district,
			@RequestParam("states") String states, @RequestParam(name = "phoneNo", required = false) String phoneNo,
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
			ModelMap map, HttpServletRequest request) {
		ClinicHospital ch = new ClinicHospital(chName, street, poscode, district, states,
				UCHStatusEnum.Activated.getCode(), 0);
		ch.setPhoneNo(phoneNo);
		clinicHospitalServices.create(ch);
		List<CHOperationTime> operationTimeList = saveOperationTime(startTimeMonday, endTimeMonday, startTimeTuesday,
				endTimeTuesday, startTimeWednesday, endTimeWednesday, startTimeThursday, endTimeThursday,
				startTimeFriday, endTimeFriday, startTimeSaturday, endTimeSaturday, startTimeSunday, endTimeSunday);
		clinicHospitalServices.saveAllOperationTime(operationTimeList, ch);
		List<ClinicHospital> chProfileList = clinicHospitalServices.findAll();
		map.addAttribute("chProfileList", chProfileList);
		return "admin/manageUCH/chList";

	}

	@PostMapping("/petOwner/editProfile")
	public String ownerEditProfile(@RequestParam("username") String username,
			@RequestParam(name = "street", required = false) String street,
			@RequestParam(name = "poscode", required = false) String poscode,
			@RequestParam(name = "district", required = false) String district,
			@RequestParam(name = "states", required = false) String states,
			@RequestParam(name = "phoneNo", required = false) String phoneNo, ModelMap map,
			HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			User user = userServices.findById(UID);
			user.setName(username);
			userServices.updateUser(user);
			PetOwner petOwner = petOwnerServices.findById(UID);
			petOwner.setUsername(username);
			petOwner.setStreet(street);
			petOwner.setPoscode(poscode);
			petOwner.setDistrict(district);
			petOwner.setStates(states);
			petOwner.setPhoneNo(phoneNo);
			String msg = petOwnerServices.updatePetOwner(petOwner);
			map.addAttribute("msg", msg);
			map.addAllAttributes(socialNetworkServices.get20Posts());
			map.addAttribute("petOwner", petOwner);
			return "petOwner/personal/editProfile";
		} else {
			return "redirect:/?msg=Session Out!";
		}

	}

	@PostMapping("/petOwner/changePassw")
	public String ownerChangePassw(@RequestParam("oldPassw") String oldPassw, @RequestParam("newPassw") String newPassw,
			ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			User user = userServices.findById(UID);
			String msg = userServices.changePassw(user, oldPassw, newPassw);
			PetOwner petOwner = petOwnerServices.findById(UID);
			map.addAttribute("petOwner", petOwner);
			map.addAttribute("msg", msg);
			return "petOwner/personal/editProfile";
		} else {
			return "redirect:/?msg=Session Out!";
		}

	}

	@GetMapping("/petOwner/viewCH")
	public String ownerViewCH(@RequestParam("chId") String chId,
			@RequestParam(name = "msg", required = false) String msg, ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			map.addAllAttributes(getCHProfileDetails(chId));
			if (msg != null || msg != "") {
				map.addAttribute("msg", msg);
			}
			return "petOwner/vetCH/chProfile";
		} else {
			return "redirect:/?msg=Session Out!";
		}
	}

	@PostMapping("/petOwner/comments")
	public String comments(@RequestParam("ratingOption") String ratingOption, @RequestParam("chId") String chId,
			@RequestParam("descriptions") String descriptions, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			CHComment comment = new CHComment(descriptions, Double.parseDouble(ratingOption));
			clinicHospitalServices.postComment(comment, chId, UID);
			return "redirect:/manageUCH/petOwner/viewCH?chId=" + chId;
		} else {
			return "redirect:/?msg=Session Out!";
		}
	}

	@PostMapping("/vet/editProfile")
	public String vetEditProfile(@RequestParam("email") String email,
			@RequestParam(name = "phoneNo", required = false) String phoneNo, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			Veterinarian veterinarian = veterinarianServices.findById(UID);
			veterinarian.setEmail(email);
			veterinarian.setPhoneNo(phoneNo);
			String msg = veterinarianServices.updateVet(veterinarian);
			map.addAttribute("msg", msg);
			map.addAttribute("veterinarian", veterinarian);
			return "vet/personal/editProfile";
		} else {
			return "redirect:/vet?msg=Session Out!";
		}
	}

	@PostMapping("/vet/changePassw")
	public String vetChangePassw(@RequestParam("oldPassw") String oldPassw, @RequestParam("newPassw") String newPassw,
			ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			User user = userServices.findById(UID);
			String msg = userServices.changePassw(user, oldPassw, newPassw);
			Veterinarian veterinarian = veterinarianServices.findById(UID);
			map.addAttribute("veterinarian", veterinarian);
			map.addAttribute("msg", msg);
			return "vet/personal/editProfile";
		} else {
			return "redirect:/vet?msg=Session Out!";
		}
	}

	@GetMapping("/vet/viewCH")
	public String vetViewCH(@RequestParam("chId") String chId, @RequestParam(name = "msg", required = false) String msg,
			ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			Veterinarian veterinarian = veterinarianServices.findById(UID);
			map.addAllAttributes(getCHProfileDetails(chId));
			if (veterinarian.getChId() != null) {
				map.addAttribute("vetCHId", veterinarian.getChId().getChId());
			}
			if (msg != null || msg != "") {
				map.addAttribute("msg", msg);
			}
			return "vet/ch/chProfile";
		} else {
			return "redirect:/vet?msg=Session Out!";
		}
	}

	@GetMapping("/vet/addAsMember")
	public String vetRequestAsMemeber(@RequestParam("chId") String chId, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			Veterinarian veterinarian = veterinarianServices.findById(UID);
			veterinarian.setChId(clinicHospitalServices.findById(chId));
			String msg = veterinarianServices.updateVet(veterinarian);
			return "redirect:/manageUCH/vet/viewCH?chId=" + chId + "&msg=" + msg;
		} else {
			return "redirect:/vet?msg=Session Out!";
		}
	}

	@PostMapping("/vet/editCH")
	public String vetEditCH(@RequestParam("id") String chId, @RequestParam("street") String street,
			@RequestParam("poscode") String poscode, @RequestParam("district") String district,
			@RequestParam("states") String states, @RequestParam(name = "phoneNo", required = false) String phoneNo,
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
			@RequestParam("manageCHButton") String manageCHButton, ModelMap map, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			if (manageCHButton.equals("manageApt")) {
				map.addAllAttributes(appointmentServices.findByClinicHospital(chId));
				return "vet/ch/aptList";
			} else {
				ClinicHospital ch = clinicHospitalServices.findById(chId);
				ch.setStreet(street);
				ch.setPoscode(poscode);
				ch.setDistrict(district);
				ch.setStates(states);
				ch.setPhoneNo(phoneNo);
				clinicHospitalServices.updateCHProfile(ch);
				List<CHOperationTime> operationTimeList = saveOperationTime(startTimeMonday, endTimeMonday,
						startTimeTuesday, endTimeTuesday, startTimeWednesday, endTimeWednesday, startTimeThursday,
						endTimeThursday, startTimeFriday, endTimeFriday, startTimeSaturday, endTimeSaturday,
						startTimeSunday, endTimeSunday);
				clinicHospitalServices.saveAllOperationTime(operationTimeList, ch);
				return "redirect:/nav/vet?vetNavButton=MyCH";
				
			}
		} else {
			return "vet/acc/login";
		}

	}

	@GetMapping("/admin/addCH")
	public String adminAddCHPage() {
		return "admin/manageUCH/addCH";
	}

	@GetMapping("/vet/addCH")
	public String vetAddCHPage() {
		return "vet/ch/addCH";
	}

	private ModelMap getCHProfileDetails(String chId) {
		ModelMap map = new ModelMap();
		ClinicHospital ch = clinicHospitalServices.findById(chId);
		List<CHOperationTime> operationTimeList = clinicHospitalServices.getOperationTime(ch);
		List<CHComment> comments = clinicHospitalServices.getCommentByChId(ch);
		map.addAttribute("ch", ch);
		map.addAttribute("operationTimeList", operationTimeList);
		map.addAttribute("comments", comments);
		if (clinicHospitalServices.checkDetailsExists(ch)) {
			CHDetail detail = clinicHospitalServices.getDetails(ch);
			Veterinarian vet = detail.getOwner();
			map.addAttribute("chOwner", vet);
		} else {
			map.addAttribute("chOwner", null);
		}
		map.addAttribute("ch", ch);
		return map;
	}

	private List<CHOperationTime> saveOperationTime(String startTimeMonday, String endTimeMonday,
			String startTimeTuesday, String endTimeTuesday, String startTimeWednesday, String endTimeWednesday,
			String startTimeThursday, String endTimeThursday, String startTimeFriday, String endTimeFriday,
			String startTimeSaturday, String endTimeSaturday, String startTimeSunday, String endTimeSunday) {
		List<CHOperationTime> timeList = new ArrayList<CHOperationTime>();
		CHOperationTime time1 = new CHOperationTime("Monday", startTimeMonday, endTimeMonday);
		CHOperationTime time2 = new CHOperationTime("Tuesday", startTimeTuesday, endTimeTuesday);
		CHOperationTime time3 = new CHOperationTime("Wednesday", startTimeWednesday, endTimeWednesday);
		CHOperationTime time4 = new CHOperationTime("Thursday", startTimeThursday, endTimeThursday);
		CHOperationTime time5 = new CHOperationTime("Friday", startTimeFriday, endTimeFriday);
		CHOperationTime time6 = new CHOperationTime("Saturday", startTimeSaturday, endTimeSaturday);
		CHOperationTime time7 = new CHOperationTime("Sunday", startTimeSunday, endTimeSunday);
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
