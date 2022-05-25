package com.apufyp.wohkx.vetassociatesystem.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apufyp.wohkx.vetassociatesystem.constant.UCHStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.constant.UsertypeEnum;
import com.apufyp.wohkx.vetassociatesystem.model.Admin;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.repository.AdminRepository;

@Service
public class AdminServices {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserServices userServices;

	private static final Logger logger = LoggerFactory.getLogger(AdminServices.class);

	public Admin findById(String id) {
		logger.info("Finding admin: " + id);
		User user = userServices.findById(id);
		return adminRepository.findByUser(user);
	}

	public void create(Admin admin) {
		logger.info("Creating new admin: " + admin.getId());
		adminRepository.saveAndFlush(admin);
	}

	public boolean checkExistsById(String id) {
		logger.info("Checking admin : " + id + "existing or not.");
		if (adminRepository.findById(id).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public int numberOfAdmin() {
		logger.info("Checking current admin number.");
		List<Admin> adminList = adminRepository.findAll();
		return adminList.size();
	}

	public void startUp() {
		int i = numberOfAdmin();
		if (i < 1) {
			User user = new User(("Admin: " + Integer.toString(i + 1)), UsertypeEnum.Administrator.getCode(),
					"12345678", UCHStatusEnum.Activated.getCode(), ("Admin: " + Integer.toString(i + 1)));
			userServices.create(user);
			Admin admin = new Admin("000818-14-0055", "Woh Kai Xuan", ("Admin :" + Integer.toString(i + 1)), user);
			create(admin);
		}
	}
}
