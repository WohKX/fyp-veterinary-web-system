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
import com.apufyp.wohkx.vetassociatesystem.constant.UsertypeEnum;
import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.repository.PetOwnerRepository;
import com.apufyp.wohkx.vetassociatesystem.repository.UserRepository;
import com.apufyp.wohkx.vetassociatesystem.repository.VeterinarianRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private PetOwnerRepository petOwnerRepository;
	
	@Autowired
	private VeterinarianRepository veterinarianRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

	public void create(User user) {
		logger.info("Creating new user: " + user.getId());
		userRepository.saveAndFlush(user);
	}

	public void updateUser(User user) {
		logger.info("Updating user: " + user.getId());
		userRepository.saveAndFlush(user);
	}

	public User findByVerificationCode(String code) {
		logger.info("Finding user with verification code= " + code);
		return userRepository.findByVerificationCode(code);
	}

	public User findById(String id) {
		logger.info("Finding user: " + id);
		return userRepository.findById(id).get();
	}

	public boolean checkExistsById(String id) {
		logger.info("Checking user : " + id + "existing or not.");
		if (userRepository.findById(id).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public void updateUserStatus(String userId, String accStatus) {
		logger.info("Updating user status: " + userId + " set status to " + accStatus);
		User user = findById(userId);
		user.setAccStatus(accStatus);
		userRepository.saveAndFlush(user);
	}

	public String changePassw(User user, String oldPassw, String newPassw) {
		if (oldPassw.equals(user.getPassword())) {
			logger.info("User's current password is matched.");
			user.setPassword(newPassw);
			userRepository.saveAndFlush(user);
			return "Password updated.";
		} else {
			logger.info("Checking user's current password wrong.");
			return "Password does not updated, <br> please double check your current password.";
		}
	}

	public String petOwnerForgetPassword(String userId) {
		if (checkExistsById(userId)) {
			User user = findById(userId);
			String newPassword = RandomString.make(24);
			user.setPassword(newPassword);
			updateUser(user);
			logger.info("Changing password upon forget for : " + userId);
			try {
				sendPasswordPetOwner(user);
				return "Email has been sent, please check your mailbox for new password.";
			} catch (UnsupportedEncodingException | MessagingException e) {
				logger.warn(e.getMessage());
				return "Something wrong with the server, please try again later.";
			}
		} else {
			return "User does not exist.";
		}
	}

	public List<User> findByAccStatus(String accStatus) {
		logger.info("Finding users with account status: " + accStatus);
		return userRepository.findByAccStatus(accStatus);
	}
	
	public void startUpForDemo() {
		List<User> users = userRepository.findAll();
		if (users.size() < 5) {
			startupUserForDemo();
		}
	}

	private void sendPasswordPetOwner(User user) throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getId();
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
	
	private void startupUserForDemo() {
		List<User> users = new ArrayList<User>();
		User user = new User("ownerDemo1@gmail.com", UsertypeEnum.Pet_Owner.getCode(), "12345678", UCHStatusEnum.Blocked.getCode(),
					"Jeffry Wong");
		User user2 = new User("ownerDemo2@gmail.com", UsertypeEnum.Pet_Owner.getCode(), "12345678", UCHStatusEnum.Activated.getCode(),
				"James Ong");
		User user3 = new User("ownerDemo3@gmail.com", UsertypeEnum.Pet_Owner.getCode(), "12345678", UCHStatusEnum.Activated.getCode(),
				"Henry Chin");
		User user4 = new User("ownerDemo4@gmail.com", UsertypeEnum.Pet_Owner.getCode(), "12345678", UCHStatusEnum.Activated.getCode(),
				"Lim Xin Fei");
		User user5 = new User("111111-11-1111", UsertypeEnum.Veterinarian.getCode(), "12345678", UCHStatusEnum.Activated.getCode(),
				"Chun Jing Ming");
		User user6 = new User("222222-22-2222", UsertypeEnum.Veterinarian.getCode(), "12345678", UCHStatusEnum.Activated.getCode(),
				"Yong Ling Jian");
		User user7 = new User("333333-33-3333", UsertypeEnum.Veterinarian.getCode(), "12345678", UCHStatusEnum.Blocked.getCode(),
				"Pang Kin Sing");
		User user8 = new User("444444-44-4444", UsertypeEnum.Veterinarian.getCode(), "12345678", UCHStatusEnum.Processing.getCode(),
				"Wong Jun Wing");
		users.add(user);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);
		users.add(user8);
		userRepository.saveAllAndFlush(users);
		List<PetOwner> petOwners = new ArrayList<PetOwner>();
		PetOwner petOwner = new PetOwner(user.getId(), user.getName(), user);
		PetOwner petOwner2 = new PetOwner(user2.getId(), user2.getName(), user2);
		PetOwner petOwner3 = new PetOwner(user3.getId(), user3.getName(), user3);
		PetOwner petOwner4 = new PetOwner(user4.getId(), user4.getName(), user4);
		petOwners.add(petOwner);
		petOwners.add(petOwner2);
		petOwners.add(petOwner3);
		petOwners.add(petOwner4);
		petOwnerRepository.saveAllAndFlush(petOwners);
		List<Veterinarian> veterinarians = new ArrayList<Veterinarian>();
		Veterinarian veterinarian = new Veterinarian(user5.getId(), user5.getId(), user5.getName(), "vetDemo1@gmail.com", "0196483743");
		Veterinarian veterinarian2 = new Veterinarian(user6.getId(), user6.getId(), user6.getName(), "vetDemo2@gmail.com", "0168365983");
		Veterinarian veterinarian3 = new Veterinarian(user7.getId(), user7.getId(), user7.getName(), "vetDemo3@gmail.com");
		Veterinarian veterinarian4 = new Veterinarian(user8.getId(), user8.getId(), user8.getName(), "vetDemo4@gmail.com", "0196585247");
		veterinarian.setUser(user5);
		veterinarian2.setUser(user6);
		veterinarian3.setUser(user7);
		veterinarian4.setUser(user8);
		veterinarians.add(veterinarian);
		veterinarians.add(veterinarian2);
		veterinarians.add(veterinarian3);
		veterinarians.add(veterinarian4);
		veterinarianRepository.saveAllAndFlush(veterinarians);
	}
}
