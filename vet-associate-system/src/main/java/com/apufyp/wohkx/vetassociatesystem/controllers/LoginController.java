package com.apufyp.wohkx.vetassociatesystem.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apufyp.wohkx.vetassociatesystem.constant.UCHStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.services.AppointmentServices;
import com.apufyp.wohkx.vetassociatesystem.services.ChatRoomServices;
import com.apufyp.wohkx.vetassociatesystem.services.ClinicHospitalServices;
import com.apufyp.wohkx.vetassociatesystem.services.UserServices;
import com.apufyp.wohkx.vetassociatesystem.services.VeterinarianServices;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

	@Autowired
	private AppointmentServices appointmentServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private VeterinarianServices veterinarianServices;

	@Autowired
	private ClinicHospitalServices clinicHospitalServices;

	@Autowired
	private ChatRoomServices chatRoomServices;

	@PostMapping("/admin")
	public String adminLogin(@RequestParam("userId") String userId, @RequestParam("password") String password,
			HttpServletRequest request) {
		User user = new User();
		String msg = null;
		if (userServices.checkExistsById(userId)) {
			user = userServices.findById(userId);
			if (user.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute(session.getId(), userId);
				return "redirect:/home/admin/homePosts";
			} else {
				msg = "The password is incorrect.";
			}
		} else {
			msg = "User does not exist.";
		}
		return "redirect:/admin?msg=" + msg;
	}

	@PostMapping("/petOwner")
	public String petOwnerLogin(@RequestParam("submitButton") String submitButton,
			@RequestParam("userId") String userId, @RequestParam("password") String password,
			HttpServletRequest request) {
		if (submitButton.equals("login")) {
			User user = new User();
			String msg = null;
			if (userServices.checkExistsById(userId)) {
				user = userServices.findById(userId);
				if (user.getPassword().equals(password)) {
					if (user.getAccStatus().equals(UCHStatusEnum.Activated.getCode())) {
						HttpSession session = request.getSession();
						session.setAttribute(session.getId(), userId);
						appointmentServices.updatePetOwnerAppointmentStatus(userId);
						int messageCount = chatRoomServices.getNotificationCount(userId);
						return "redirect:/home/petOwner/homePosts?messageCount=" + messageCount;
					} else {
						msg = "Your account has been blocked, please contact administrator for further info.";
					}
				} else {
					msg = "The password is incorrect.";
				}
			} else {
				msg = "User does not exist.";
			}
			return "redirect:/?msg=" + msg;
		} else {
			return "petOwner/acc/register";
		}

	}

	@GetMapping("/petOwner/forgetPassword")
	public String petOwnerForgetPassword(@RequestParam("userId") String userId) {
		String msg = userServices.petOwnerForgetPassword(userId);
		return "redirect:/?msg=" + msg;
	}

	@PostMapping("/vet")
	public String vetLogin(@RequestParam("submitButton") String submitButton, @RequestParam("userId") String userId,
			@RequestParam("password") String password, HttpServletRequest request) {
		if (submitButton.equals("login")) {
			User user = new User();
			String msg = null;
			if (userServices.checkExistsById(userId)) {
				user = userServices.findById(userId);
				if (user.getPassword().equals(password)) {
					if (user.getAccStatus().equals(UCHStatusEnum.Activated.getCode())) {
						HttpSession session = request.getSession();
						session.setAttribute(session.getId(), userId);
						Veterinarian veterinarian = veterinarianServices.findById(userId);
						if (clinicHospitalServices.checkIsCHOwner(userId)) {
							appointmentServices
									.updateCHAppointmentStatus(Long.toString(veterinarian.getChId().getChId()));
						}
						int messageCount = chatRoomServices.getNotificationCount(userId);
						return "redirect:/home/vet/homePosts?messageCount=" + messageCount;
					} else {
						msg = "Your account has been blocked, please contact administrator for further info.";
					}
				} else {
					msg = "The password is incorrect.";
				}
			} else {
				msg = "User does not exist.";
			}
			return "redirect:/vet?msg=" + msg;
		} else {
			return "vet/acc/register";
		}

	}

	@GetMapping("/vet/forgetPassword")
	public String vetForgetPassword(@RequestParam("userId") String userId, @RequestParam("email") String email) {
		String msg = veterinarianServices.vetForgetPassword(userId, email);
		return "redirect:/vet?msg=" + msg;
	}
}
