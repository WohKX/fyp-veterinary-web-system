package com.apufyp.wohkx.vetassociatesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apufyp.wohkx.vetassociatesystem.services.AdminServices;
import com.apufyp.wohkx.vetassociatesystem.services.ChatbotServices;
import com.apufyp.wohkx.vetassociatesystem.services.ClinicHospitalServices;
import com.apufyp.wohkx.vetassociatesystem.services.UserServices;

@Controller
public class VetAssociateSystem {

	@Autowired
	private AdminServices adminServices;

	@Autowired
	private ChatbotServices chatbotServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private ClinicHospitalServices clinicHospitalServices;

	@RequestMapping("/")
	public String welcomePage() {
		startUp();
		return "petOwner/acc/login";
	}

	@RequestMapping("/petOwner/acc/login")
	public String petOwnerLoginPage() {
		return "petOwner/acc/login";
	}

	@RequestMapping("/vet")
	public String vetLoginPage() {
		return "vet/acc/login";
	}

	@RequestMapping("/petOwner/acc/forgetPassw")
	public String petOwnerForgetPassword() {
		return "petOwner/acc/forgetPassw";
	}

	@RequestMapping("/vet/acc/forgetPassw")
	public String vetForgetPassword() {
		return "vet/acc/forgetPassw";
	}

	@RequestMapping("/admin")
	public String adminPage() {
		startUp();
		return "admin/acc/login";
	}

	private void startUp() {
		adminServices.startUp();
		chatbotServices.startup();
		userServices.startUpForDemo();
		clinicHospitalServices.startup();

	}
}
