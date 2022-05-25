package com.apufyp.wohkx.vetassociatesystem.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

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
import com.apufyp.wohkx.vetassociatesystem.constant.UsertypeEnum;
import com.apufyp.wohkx.vetassociatesystem.model.Admin;
import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;
import com.apufyp.wohkx.vetassociatesystem.model.Ticket;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.services.AdminServices;
import com.apufyp.wohkx.vetassociatesystem.services.PetOwnerServices;
import com.apufyp.wohkx.vetassociatesystem.services.TicketServices;
import com.apufyp.wohkx.vetassociatesystem.services.UserServices;
import com.apufyp.wohkx.vetassociatesystem.services.VeterinarianServices;

import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserServices userServices;

	@Autowired
	private PetOwnerServices petOwnerServices;

	@Autowired
	private VeterinarianServices veterinarianServices;

	@Autowired
	private TicketServices ticketServices;

	@Autowired
	private AdminServices adminServices;

	@PostMapping("/admin")
	public String adminCreateAcc(@RequestParam("username") String username, @RequestParam("ICsec1") String ICsec1,
			@RequestParam("ICsec2") String ICsec2, @RequestParam("ICsec3") String ICsec3,
			@RequestParam("password") String password, ModelMap map, HttpServletRequest request) {
		String ic = ICsec1.concat("-").concat(ICsec2).concat("-").concat(ICsec3);
		String msg = null;
		if (!adminServices.checkExistsById(ic)) {

			int i = adminServices.numberOfAdmin();
			User user = new User(("Admin: " + Integer.toString(i + 1)), UsertypeEnum.Administrator.getCode(), password,
					UCHStatusEnum.Activated.getCode(), ("Admin: " + Integer.toString(i + 1)));
			userServices.create(user);
			Admin admin = new Admin(ic, username, ("Admin: " + Integer.toString(i + 1)), user);
			adminServices.create(admin);
			msg = "The account is registered successfully.";
		} else {
			msg = "The ic has been signed up before!";
		}
		return "redirect:/nav/admin?adminNavButton=CreateAcc&msg=" + msg;
	}

	@PostMapping("/petOwner")
	public String petOwnerRegister(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, HttpServletRequest request)
			throws MessagingException, UnsupportedEncodingException {
		String msg = null;
		if (!userServices.checkExistsById(email)) {
			User user = new User(email, UsertypeEnum.Pet_Owner.getCode(), password, UCHStatusEnum.Processing.getCode(),
					username);
			String randomCode = RandomString.make(64);
			user.setVerificationCode(randomCode);
			PetOwner petOwner = new PetOwner(email, username);
			petOwner.setUser(user);
			userServices.create(user);
			petOwnerServices.create(petOwner, request, user);
			msg = "You have signed up successfully! Please check your email to verify your account.";
		} else {
			msg = "The email has been signed up before!";
		}
		return "redirect:/?msg=" + msg;
	}

	@GetMapping("/verifyEmail")
	public String verifyUser(@RequestParam("code") String code) {
		if (verifyEmail(code)) {
			return "redirect:/?msg=Congratulations, your account has been verified.";
		} else {
			return "redirect:/?msg=Sorry, we could not verify account. It maybe already activated, or verification code is incorrect.";
		}
	}

	@PostMapping("/vet")
	public String vetRegister(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("ICsec1") String ICsec1, @RequestParam("ICsec2") String ICsec2,
			@RequestParam("ICsec3") String ICsec3, @RequestParam("credential") MultipartFile credential,
			@RequestParam("password") String password, ModelMap map, HttpServletRequest request) throws IOException {
		String msg = null;
		String ic = ICsec1.concat("-").concat(ICsec2).concat("-").concat(ICsec3);
		if (!userServices.checkExistsById(ic)) {
			String contentType = credential.getContentType();
			byte[] data = credential.getBytes();
			String imageString = Base64.getEncoder().encodeToString(data);

			User user = new User(ic, UsertypeEnum.Veterinarian.getCode(), password, UCHStatusEnum.Processing.getCode(),
					username);
			Veterinarian veterinarian = new Veterinarian(ic, ic, username, email);
			userServices.create(user);
			veterinarian.setUser(user);
			veterinarianServices.create(veterinarian);
			Ticket ticket = new Ticket(user, TicketTypeEnum.Veterinarian_New_Acc.toString(), ic, contentType,
					imageString, (TicketTypeEnum.Veterinarian_New_Acc.getDescription().concat(ic)),
					TicketStatusEnum.New_Raised.getCode());
			ticketServices.create(ticket);
			msg = "You have signed up successfully! Please wait for your account to be approved, it usually takes within 3 working days.";
		} else {
			msg = "The ic has been signed up before!";
		}
		return "redirect:/vet?msg=" + msg;
	}

	private boolean verifyEmail(String verificationCode) {
		User user = userServices.findByVerificationCode(verificationCode);
		if (user != null) {
			if (!user.getAccStatus().equals(UCHStatusEnum.Processing.getCode())) {
				return false;
			} else {
				user.setVerificationCode(null);
				user.setAccStatus(UCHStatusEnum.Activated.getCode());
				userServices.updateUser(user);
				return true;
			}
		} else {
			return false;
		}

	}

}
