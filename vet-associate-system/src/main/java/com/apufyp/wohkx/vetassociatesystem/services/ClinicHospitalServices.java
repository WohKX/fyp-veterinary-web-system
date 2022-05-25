package com.apufyp.wohkx.vetassociatesystem.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apufyp.wohkx.vetassociatesystem.constant.UCHStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.CHComment;
import com.apufyp.wohkx.vetassociatesystem.model.CHDetail;
import com.apufyp.wohkx.vetassociatesystem.model.CHOperationTime;
import com.apufyp.wohkx.vetassociatesystem.model.ClinicHospital;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.repository.CHCommentRepository;
import com.apufyp.wohkx.vetassociatesystem.repository.CHDetailRepository;
import com.apufyp.wohkx.vetassociatesystem.repository.CHOperationTimeRepository;
import com.apufyp.wohkx.vetassociatesystem.repository.ClinicHospitalRepository;

@Service
public class ClinicHospitalServices {

	@Autowired
	private ClinicHospitalRepository clinicHospitalRepository;

	@Autowired
	private CHOperationTimeRepository chOperationTimeRepository;

	@Autowired
	private CHDetailRepository chDetailRepository;

	@Autowired
	private CHCommentRepository chCommentRepository;

	@Autowired
	private VeterinarianServices veterinarianServices;

	@Autowired
	private UserServices userServices;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm MMMM-dd(yyyy)");

	private static final Logger logger = LoggerFactory.getLogger(ClinicHospitalServices.class);

	public void updateCHProfile(ClinicHospital clinicHospital) {
		logger.info("Updating Clinic/Hospital: " + clinicHospital.getChId());
		clinicHospitalRepository.saveAndFlush(clinicHospital);
	}

	public void approveCH(String chId, String UID) {
		ClinicHospital ch = findById(chId);
		Veterinarian veterinarian = veterinarianServices.findById(UID);
		changeOwnerCH(ch, veterinarian);
		ch.setChStatus(UCHStatusEnum.Activated.getCode());
		veterinarian.setChId(ch);
		clinicHospitalRepository.saveAndFlush(ch);
		veterinarianServices.updateVet(veterinarian);
		logger.info(ch.getChName() + "has been activated.");
	}

	public List<ClinicHospital> findByCHStatus(String chStatus) {
		logger.info("Finding Clinic/Hospital with status= " + chStatus);
		return clinicHospitalRepository.findByCHStatus(chStatus);
	}

	public List<ClinicHospital> findAll() {
		logger.info("Getting all clinic/hospitals from database.");
		return clinicHospitalRepository.findAll();
	}

	public ClinicHospital findById(String id) {
		logger.info("Getting clinic/hospitals: " + id);
		return clinicHospitalRepository.findById(Long.parseLong(id)).get();
	}

	public void create(ClinicHospital ch) {
		logger.info("Creating new clinic/hospitals: " + ch.getChId());
		clinicHospitalRepository.saveAndFlush(ch);
	}

	public List<CHOperationTime> getOperationTime(ClinicHospital ch) {
		logger.info("Getting operation time list of " + ch.getChId());
		return chOperationTimeRepository.findByClinicHospital(ch);
	}

	public boolean checkIsCHOwner(String userId) {
		Veterinarian veterinarian = veterinarianServices.findById(userId);
		if (veterinarian.getChId() != null) {
			if (checkDetailsExists(veterinarian.getChId())) {
				CHDetail detail = getDetails(veterinarian.getChId());
				if (detail.getOwner().equals(veterinarian)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean checkDetailsExists(ClinicHospital ch) {
		if (chDetailRepository.findByClinicHospital(ch).size() > 0) {
			logger.info("The details of " + ch.getChName() + " found.");
			return true;
		} else {
			logger.info("The details of " + ch.getChName() + " not found.");
			return false;
		}
	}

	public CHDetail getDetails(ClinicHospital ch) {
		logger.info("Getting the details of " + ch.getChId());
		CHDetail detail = chDetailRepository.getByClinicHospital(ch);
		return detail;
	}

	public List<CHComment> getCommentByChId(ClinicHospital ch) {
		logger.info("Getting the comments of " + ch.getChId());
		List<CHComment> chComments = chCommentRepository.findByChIdOrderByCommentTimeDesc(ch);
		if (chComments.size() > 0) {
			for (CHComment c : chComments) {
				c.setTimeString(dateFormat.format(c.getCommentTime()));
			}
			return chComments;
		} else {
			return null;
		}
	}

	public void postComment(CHComment comment, String chId, String UID) {
		ClinicHospital ch = findById(chId);
		User writter = userServices.findById(UID);
		comment.setChId(ch);
		comment.setWritter(writter);
		logger.info("Comment for " + ch.getChName());
		chCommentRepository.saveAndFlush(comment);
		updateCHRating(ch, comment.getRatings());
	}

	public void saveAllOperationTime(List<CHOperationTime> operationTimeList, ClinicHospital ch) {
		for (CHOperationTime time : operationTimeList) {
			if (chOperationTimeRepository.findByClinicHospitalAndDay(ch, time.getDay()).size() > 0) {
				CHOperationTime chTime = chOperationTimeRepository.getByClinicHospitalAndDay(ch, time.getDay());
				chTime.setStartTime(time.getStartTime());
				chTime.setEndTime(time.getEndTime());
				chOperationTimeRepository.save(chTime);
			} else {
				CHOperationTime chTime = new CHOperationTime(ch, time.getDay(), time.getStartTime(), time.getEndTime());
				chOperationTimeRepository.save(chTime);
			}
		}
		chOperationTimeRepository.flush();
	}

	public void startup() {
		List<ClinicHospital> clinicHospitals = clinicHospitalRepository.findAll();
		if (clinicHospitals.size() < 1) {
			startupClinicHospital();
			startupCHForDemo();
		}
	}

	private void updateCHRating(ClinicHospital ch, double ratings) {
		Integer ratingNo = ch.getRatingNo();
		double currentRating = ch.getRatings();
		double newRating = ((ratingNo * currentRating) + ratings) / (ratingNo + 1);
		ch.setRatingNo(ratingNo + 1);
		ch.setRatings(newRating);
		logger.info("Updating rating for " + ch.getChName());
		clinicHospitalRepository.saveAndFlush(ch);
	}

	private void changeOwnerCH(ClinicHospital ch, Veterinarian veterinarian) {
		CHDetail details = new CHDetail();
		logger.info("Changing the owner for " + ch.getChName());
		if (chDetailRepository.existsById(ch.getChId())) {
			details = chDetailRepository.getByClinicHospital(ch);
		} else {
			details.setClinicHospital(ch);
			details.setOwner(veterinarian);
		}
		chDetailRepository.saveAndFlush(details);
	}

	private void startupClinicHospital() {
		List<ClinicHospital> clinicHospitals = new ArrayList<ClinicHospital>();
		ClinicHospital clinicHospital = new ClinicHospital("Bukit Jalil Veterinary Clinic & Surgery",
				"G-13A, Kompleks Niaga, 2, Jalan Bukit Jalil Indah 4, Taman Ltat", "57000", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital2 = new ClinicHospital("VPAC - Vets For Pets Animal Clinic Kuchai Lama",
				"No. 49, Jalan Kuchai Lama, Taman Lian Hoe", "58100", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital3 = new ClinicHospital("Companion Animal Veterinary Clinic",
				"Jalan Klang Lama, 29, Jalan Hujan Rahmat 3, Taman Overseas Union", "58200", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital4 = new ClinicHospital("Sri Petaling Veterinary Clinic and Surgery",
				"123, Jalan Radin Bagus, Bandar Baru Sri Petaling", "57000", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital5 = new ClinicHospital("PREVENTICARE Veterinary Clinic",
				"G 15, Jalan 1/114, Kuchai Business Centre", "58200", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital6 = new ClinicHospital("Lee Veterinary Clinic (Leevet Clinic Sdn Bhd)",
				"35 & 37G, Jalan Kuchai Maju 1, Kuchai Entrepreneurs Park", "58200", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital7 = new ClinicHospital("G Veterinary Clinic & Surgery",
				"27-G, Jalan 5/76b, Desa Pandan", "55100", "Kuala Lumpur", "Federal Territory of Kuala Lumpur",
				UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital8 = new ClinicHospital("Catnest Veterinary Clinic (Sg Besi)",
				"Wilayah Persekutuan, The Trillium, No. 2A, Jalan Tasik Utama 9, Sungai Besi", "57000", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital9 = new ClinicHospital("Hoof And Paw Veterinary Clinic And Surgery",
				"10-5-G, Jalan Jalil Perkasa 15, Arked Esplanad", "57000", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital10 = new ClinicHospital("Teoh Animal Clinic",
				"No 9-G, WP Kuala, Lumpur, Jalan Perubatan 4, Pandan Indah", "55100", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital11 = new ClinicHospital("Karu's Animal Centre & Surgery",
				"3(Ground Floor) Jubilee Court, Jalan Jubilee, Off, Jln Loke Yew", "55200", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital12 = new ClinicHospital("BANGSAR VETERINARY CLINIC AND SURGERY",
				"94, Lrg Maarof, Bangsar", "59000", "Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur",
				UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital13 = new ClinicHospital("C&D veterinary clinic",
				"15, Jalan 9/62a, Bandar Menjalara", "52200", "Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur",
				UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital14 = new ClinicHospital("VPAC - Vets for Pets Animal Clinic Solaris Mont Kiara",
				"5, Jalan Solaris 4, Solaris Mont Kiar", "50480", "Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur",
				UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital15 = new ClinicHospital("See Veterinary Medical Centre",
				"9, Jalan 1/149j, Bandar Baru Sri Petaling", "57000", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital16 = new ClinicHospital("Hayward Animal Clinic",
				" No. 437, Jalan Batu 41/2, Jalan Ampang", "50450", "Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur",
				UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital17 = new ClinicHospital("TTDI Veterinary Clinic",
				"119, Jalan Aminuddin Baki, Taman Tun Dr Ismail", "60000", "Kuala Lumpur",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital18 = new ClinicHospital("Living Hope Animal Clinic & Surgery",
				"30, Jalan 17/155c, Bukit Jalil", "57000", "Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur",
				UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital19 = new ClinicHospital("Veterinary Clinic Dwitasik",
				"34-G, Jalan Danau Lumayan 1, Pusat Perniagaan Danau Lumayan", "56000", "Cheras",
				"Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital20 = new ClinicHospital("Sacred Hearts Animal Clinic",
				" Jalan 20/38A, Taman Sri Sinar, Segambut", "51200", "Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur",
				UCHStatusEnum.Activated.getCode(), 0);
		clinicHospitals.add(clinicHospital);
		clinicHospitals.add(clinicHospital2);
		clinicHospitals.add(clinicHospital3);
		clinicHospitals.add(clinicHospital4);
		clinicHospitals.add(clinicHospital5);
		clinicHospitals.add(clinicHospital6);
		clinicHospitals.add(clinicHospital7);
		clinicHospitals.add(clinicHospital8);
		clinicHospitals.add(clinicHospital9);
		clinicHospitals.add(clinicHospital10);
		clinicHospitals.add(clinicHospital11);
		clinicHospitals.add(clinicHospital12);
		clinicHospitals.add(clinicHospital13);
		clinicHospitals.add(clinicHospital14);
		clinicHospitals.add(clinicHospital15);
		clinicHospitals.add(clinicHospital16);
		clinicHospitals.add(clinicHospital17);
		clinicHospitals.add(clinicHospital18);
		clinicHospitals.add(clinicHospital19);
		clinicHospitals.add(clinicHospital20);
		clinicHospitalRepository.saveAllAndFlush(clinicHospitals);
	}

	private void startupCHForDemo() {
		List<ClinicHospital> clinicHospitals = new ArrayList<ClinicHospital>();
		ClinicHospital clinicHospital = new ClinicHospital("Vet Clinic Demo 1", "Vet Clinic For Demo Only", "99999",
				"Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital2 = new ClinicHospital("Vet Clinic Demo 2", "Vet Clinic For Demo Only", "88888",
				"Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Activated.getCode(), 0);
		ClinicHospital clinicHospital3 = new ClinicHospital("Vet Clinic Demo 3", "Vet Clinic For Demo Only", "77777",
				"Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Processing.getCode(), 0);
		ClinicHospital clinicHospital4 = new ClinicHospital("Vet Clinic Demo 4", "Vet Clinic For Demo Only", "66666",
				"Kuala Lumpur", "Wilayah Persekutuan Kuala Lumpur", UCHStatusEnum.Blocked.getCode(), 0);
		clinicHospitals.add(clinicHospital);
		clinicHospitals.add(clinicHospital2);
		clinicHospitals.add(clinicHospital3);
		clinicHospitals.add(clinicHospital4);
		clinicHospitalRepository.saveAllAndFlush(clinicHospitals);
		approveCH(Long.toString(clinicHospital.getChId()), "111111-11-1111");
		approveCH(Long.toString(clinicHospital2.getChId()), "222222-22-2222");
	}
}
