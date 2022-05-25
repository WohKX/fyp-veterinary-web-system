package com.apufyp.wohkx.vetassociatesystem.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.apufyp.wohkx.vetassociatesystem.constant.AptStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.Appointment;
import com.apufyp.wohkx.vetassociatesystem.model.ClinicHospital;
import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;
import com.apufyp.wohkx.vetassociatesystem.repository.AppointmentRepository;

@Service
public class AppointmentServices {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ClinicHospitalServices clinicHospitalServices;

	@Autowired
	private PetOwnerServices petOwnerServices;

	private static final Logger logger = LoggerFactory.getLogger(AppointmentServices.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd");

	public void updatePetOwnerAppointmentStatus(String petOwnerId) {
		PetOwner petOwner = petOwnerServices.findById(petOwnerId);
		logger.info("Updating appointment status for: " + petOwner);
		Date date = new Date();
		List<Appointment> appointmentList = appointmentRepository.findByPetOwner(petOwner);
		if (appointmentList.size() > 0) {
			for (Appointment a : appointmentList) {
				if (a.getAptDate().before(date) && (!a.getAptStatus().equals(AptStatusEnum.Completed.getCode()))) {
					a.setAptStatus(AptStatusEnum.Expired.getCode());
				}
			}
		}
		appointmentRepository.saveAllAndFlush(appointmentList);
	}

	public void updateCHAppointmentStatus(String chId) {
		ClinicHospital ch = clinicHospitalServices.findById(chId);
		logger.info("Updating appointment status for: " + ch.getChName());
		Date date = new Date();
		List<Appointment> appointmentList = appointmentRepository.findByChId(ch);
		if (appointmentList.size() > 0) {
			for (Appointment a : appointmentList) {
				if (a.getAptDate().before(date) && (!a.getAptStatus().equals(AptStatusEnum.Completed.getCode()))) {
					a.setAptStatus(AptStatusEnum.Expired.getCode());
				}
			}
		}
		appointmentRepository.saveAllAndFlush(appointmentList);
	}

	public void updateAppointmentStatus(String aptId, AptStatusEnum status) {
		Appointment appointment = findById(aptId);
		appointment.setAptStatus(status.getCode());
		logger.info("Updating appointment: " + aptId + " as status " + status.getDescription());
		appointmentRepository.saveAndFlush(appointment);
	}

	public void createApt(Appointment appointment, String chId) {
		ClinicHospital ch = clinicHospitalServices.findById(chId);
		appointment.setChId(ch);
		logger.info("Creating appointment for: " + chId + " by " + appointment.getPetOwner().getUsername());
		appointmentRepository.saveAndFlush(appointment);
	}

	public ModelMap findByPetOwner(String petOwnerId) {
		ModelMap map = new ModelMap();
		PetOwner petOwner = petOwnerServices.findById(petOwnerId);
		List<Appointment> aptRequestList = findByAppointmentStatusAndPetOwner(AptStatusEnum.Request.getCode(),
				petOwner);
		List<Appointment> aptAcceptList = findByAppointmentStatusAndPetOwner(AptStatusEnum.Accepted.getCode(),
				petOwner);
		List<Appointment> aptCompleteList = findByAppointmentStatusAndPetOwner(AptStatusEnum.Completed.getCode(),
				petOwner);
		List<Appointment> aptRejectList = findByAppointmentStatusAndPetOwner(AptStatusEnum.Rejected.getCode(),
				petOwner);
		List<Appointment> aptExpiredList = findByAppointmentStatusAndPetOwner(AptStatusEnum.Expired.getCode(),
				petOwner);
		map.addAttribute("aptRequestList", aptRequestList);
		map.addAttribute("aptAcceptList", aptAcceptList);
		map.addAttribute("aptCompleteList", aptCompleteList);
		map.addAttribute("aptRejectList", aptRejectList);
		map.addAttribute("aptExpiredList", aptExpiredList);
		return map;
	}

	public ModelMap findByClinicHospital(String chId) {
		ModelMap map = new ModelMap();
		ClinicHospital ch = clinicHospitalServices.findById(chId);
		List<Appointment> aptRequestList = findByAppointmentStatusAndClinicHospital(AptStatusEnum.Request.getCode(),
				ch);
		List<Appointment> aptAcceptList = findByAppointmentStatusAndClinicHospital(AptStatusEnum.Accepted.getCode(),
				ch);
		List<Appointment> aptCompleteList = findByAppointmentStatusAndClinicHospital(AptStatusEnum.Completed.getCode(),
				ch);
		List<Appointment> aptRejectList = findByAppointmentStatusAndClinicHospital(AptStatusEnum.Rejected.getCode(),
				ch);
		List<Appointment> aptExpiredList = findByAppointmentStatusAndClinicHospital(AptStatusEnum.Expired.getCode(),
				ch);
		map.addAttribute("aptRequestList", aptRequestList);
		map.addAttribute("aptAcceptList", aptAcceptList);
		map.addAttribute("aptCompleteList", aptCompleteList);
		map.addAttribute("aptRejectList", aptRejectList);
		map.addAttribute("aptExpiredList", aptExpiredList);
		return map;
	}

	private List<Appointment> findByAppointmentStatusAndPetOwner(String aptStatus, PetOwner petOwner) {
		logger.info("Getting appointment with status " + aptStatus + " for: " + petOwner.getId());
		List<Appointment> appointments = new LinkedList<Appointment>();
		appointments.addAll(appointmentRepository.findByAptStatusAndPetOwner(aptStatus, petOwner, Sort.by("aptDate")));
		for (Appointment a : appointments) {
			a.setDateString(dateFormat.format(a.getAptDate()));
		}
		return appointments;
	}

	private List<Appointment> findByAppointmentStatusAndClinicHospital(String aptStatus, ClinicHospital ch) {
		logger.info("Getting appointment with status " + aptStatus + " for: " + ch.getChName());
		List<Appointment> appointments = new LinkedList<Appointment>();
		appointments.addAll(appointmentRepository.findByAptStatusAndChId(aptStatus, ch, Sort.by("aptDate")));
		for (Appointment a : appointments) {
			a.setDateString(dateFormat.format(a.getAptDate()));
		}
		return appointments;
	}

	private Appointment findById(String aptId) {
		logger.info("Fiding appointment with id " + aptId);
		return appointmentRepository.findById(Long.parseLong(aptId)).get();
	}

}
