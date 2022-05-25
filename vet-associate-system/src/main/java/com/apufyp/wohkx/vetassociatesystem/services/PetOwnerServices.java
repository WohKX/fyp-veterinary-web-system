package com.apufyp.wohkx.vetassociatesystem.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.repository.PetOwnerRepository;

@Service
public class PetOwnerServices {

	@Autowired
	private PetOwnerRepository petOwnerRepository;

	@Autowired
	private JavaMailSender mailSender;

	private static final Logger logger = LoggerFactory.getLogger(PetOwnerServices.class);

	public List<PetOwner> findAll() {
		logger.info("Getting all pet owner accounts from database.");
		return petOwnerRepository.findAll();
	}

	public PetOwner findById(String id) {
		logger.info("Finding pet owner: " + id);
		return petOwnerRepository.findById(id).get();
	}

	
	public List<PetOwner> findByName(String name){
		logger.info("Finding veterinarian name containing: " + name);
		return petOwnerRepository.findByUsernameContainingIgnoreCase(name);
	}
	
	public String updatePetOwner(PetOwner petOwner) {
		logger.info("Updating pet owner: " + petOwner.getId());
		petOwnerRepository.saveAndFlush(petOwner);
		return ("Profile for " + petOwner.getId() + " has been updated.");
	}

	public void create(PetOwner petOwner, HttpServletRequest request, User user) {
		logger.info("Creating new pet owner: " + petOwner.getId());
		petOwnerRepository.saveAndFlush(petOwner);
		try {
			sendVerificationEmail(user, request.getRequestURL().toString().replace(request.getServletPath(), ""));
		} catch (UnsupportedEncodingException | MessagingException e) {
			logger.warn(e.getMessage());
		}
	}

	private void sendVerificationEmail(User user, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getId();
		String fromAddress = "wkxtp056443@gmail.com";
		String senderName = "Veterinarian Associating Smart System";
		String subject = "Please verify your registration";
		String content = "Dear,<br>" + "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>"
				+ "Veterinarian Associating Smart System.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		String verifyURL = siteURL + "/register/verifyEmail?code=" + user.getVerificationCode();
		content = content.replace("[[URL]]", verifyURL);
		helper.setText(content, true);
		mailSender.send(message);
	}
}
