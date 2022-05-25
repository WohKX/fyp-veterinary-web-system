package com.apufyp.wohkx.vetassociatesystem.services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.apufyp.wohkx.vetassociatesystem.constant.UCHStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.repository.VeterinarianRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class VeterinarianServices {

	@Autowired
	private UserServices userServices;

	@Autowired
	private VeterinarianRepository veterinarianRepository;

	@Autowired
	private JavaMailSender mailSender;

	private static final Logger logger = LoggerFactory.getLogger(VeterinarianServices.class);

	public List<Veterinarian> findAll() {
		logger.info("Getting all veterinarian accounts from database.");
		return veterinarianRepository.findAll();
	}

	public Veterinarian findById(String id) {
		logger.info("Finding veterinarian: " + id);
		return veterinarianRepository.findById(id).get();
	}

	public List<Veterinarian> findByName(String name) {
		logger.info("Finding veterinarian name containing: " + name);
		return veterinarianRepository.findByRealNameContainingIgnoreCase(name);
	}

	public void create(Veterinarian veterinarian) {
		logger.info("Creating new veterinarian: " + veterinarian.getId());
		veterinarianRepository.saveAndFlush(veterinarian);
	}

	public List<Veterinarian> getActiveVetAcc() {
		logger.info("Finding veterinarian with active account status.");
		List<User> activeUsers = userServices.findByAccStatus(UCHStatusEnum.Activated.getCode());
		List<String> userIds = new ArrayList<String>();
		for (User u : activeUsers) {
			userIds.add(u.getId());
		}
		return veterinarianRepository.findAllById(userIds);

	}

	public String vetForgetPassword(String userId, String email) {
		if (userServices.checkExistsById(userId)) {
			User user = userServices.findById(userId);
			Veterinarian veterinarian = findById(userId);
			if (veterinarian.getEmail().equals(email)) {
				String newPassword = RandomString.make(24);
				user.setPassword(newPassword);
				userServices.updateUser(user);
				logger.info("Changing password upon forget for : " + userId);
				try {
					sendPassword(user, veterinarian);
					return "Email has been sent, please check your mailbox for new password.";
				} catch (UnsupportedEncodingException | MessagingException e) {
					logger.warn(e.getMessage());
					return "Something wrong with the server, please try again later.";
				}
			} else {
				return "Your email inserted does not match.";
			}
		} else {
			return "User does not exist.";
		}
	}

	public String updateVet(Veterinarian veterinarian) {
		logger.info("Updating veterinarian : " + veterinarian.getId());
		veterinarianRepository.saveAndFlush(veterinarian);
		return ("Profile for " + veterinarian.getRealName() + " has been updated.");
	}

	private void sendPassword(User user, Veterinarian veterinarian)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = veterinarian.getEmail();
		String fromAddress = "wkxtp056443@gmail.com";
		String senderName = "Veterinarian Associating Smart System";
		String subject = "Please verify your registration";
		String content = "Dear,<br>" + "Your password has been reset as:<br>" + "<h3>[[Passwords]]</h3><br>"
				+ "Please use the password above to login, remember to change your password afterwards."
				+ "Thank you,<br>" + "Veterinarian Associating Smart System.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		content = content.replace("[[Passwords]]", user.getPassword());
		helper.setText(content, true);
		mailSender.send(message);
	}

}
