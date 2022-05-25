package com.apufyp.wohkx.vetassociatesystem.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apufyp.wohkx.vetassociatesystem.constant.AptStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.Appointment;
import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.services.AppointmentServices;
import com.apufyp.wohkx.vetassociatesystem.services.PetOwnerServices;
import com.apufyp.wohkx.vetassociatesystem.services.VeterinarianServices;

@Controller
@RequestMapping(path = "/apt")
public class AppointmentController {

	@Autowired
	private PetOwnerServices petOwnerServices;

	@Autowired
	private VeterinarianServices veterinarianServices;

	@Autowired
	private AppointmentServices appointmentServices;

	private static final Logger logger = LoggerFactory.getLogger(AppointmentServices.class);

	@PostMapping("/petOwner/bookApt")
	public String petOwnerBookApt(@RequestParam("chId") String chId, @RequestParam("dateApt") String dateApt,
			@RequestParam("timeApt") String timeApt, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			PetOwner petOwner = petOwnerServices.findById(UID);
			String msg = null;
			if (timeApt != "") {
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateApt);
					Appointment appointment = new Appointment(petOwner, date, AptStatusEnum.Request.getCode(), timeApt);
					appointmentServices.createApt(appointment, chId);
					msg = ("Appointment booked, please check it at your appointment list.<br>"
							+ " You might contact with the person on charge for further detials.");
				} catch (ParseException e) {
					logger.warn("Something wrong when parsing the date of appoitnment booked.");
					msg = ("Something wrong when parsing the date of appoitnment booked.");
				}
			} else {
				msg = ("Please choose a time for your appointment");
			}
			return "redirect:/manageUCH/petOwner/viewCH?chId=" + chId + "&msg=" + msg;

		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/vet/manageApt")
	public String vetManageApt(@RequestParam("manageAptButton") String manageAptButton,
			@RequestParam("aptId") String aptId, ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			Veterinarian veterinarian = veterinarianServices.findById(UID);
			if (manageAptButton.equals(AptStatusEnum.Accepted.toString())) {
				appointmentServices.updateAppointmentStatus(aptId, AptStatusEnum.Accepted);
			} else if (manageAptButton.equals(AptStatusEnum.Completed.toString())) {
				appointmentServices.updateAppointmentStatus(aptId, AptStatusEnum.Completed);
			} else if (manageAptButton.equals(AptStatusEnum.Rejected.toString())) {
				appointmentServices.updateAppointmentStatus(aptId, AptStatusEnum.Rejected);
			}
			map.addAllAttributes(
					appointmentServices.findByClinicHospital(Long.toString(veterinarian.getChId().getChId())));
			return "vet/ch/aptList";

		} else {
			return "redirect:/vet";
		}

	}
}
