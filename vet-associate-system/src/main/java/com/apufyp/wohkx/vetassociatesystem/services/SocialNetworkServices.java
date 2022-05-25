package com.apufyp.wohkx.vetassociatesystem.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;
import com.apufyp.wohkx.vetassociatesystem.model.SNComment;
import com.apufyp.wohkx.vetassociatesystem.model.SocialNetwork;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.repository.SNCommentRepository;
import com.apufyp.wohkx.vetassociatesystem.repository.SocialNetworkRepository;

@Service
public class SocialNetworkServices {

	@Autowired
	private SocialNetworkRepository socialNetworkRepository;

	@Autowired
	private SNCommentRepository snCommentRepository;

	@Autowired
	private VeterinarianServices veterinarianServices;

	@Autowired
	private PetOwnerServices petOwnerServices;

	private static final Logger logger = LoggerFactory.getLogger(SocialNetworkServices.class);

	private static final SimpleDateFormat postDateFormat = new SimpleDateFormat("HH:mm MMMM-dd(yyyy)");

	private static final SimpleDateFormat commentDateFormat = new SimpleDateFormat("HH:mm MMM-dd");

	public ModelMap get20Posts() {
		ModelMap map = new ModelMap();
		List<SocialNetwork> socialNetworks = new LinkedList<SocialNetwork>();
		socialNetworks.addAll(socialNetworkRepository.findTop20ByOrderBySnTimeDesc());
		if (socialNetworks.size() > 0) {
			for (SocialNetwork s : socialNetworks) {
				List<SNComment> comments = snCommentRepository.findTop3BySnIdOrderByCommentTimeDesc(s);
				for (SNComment c : comments) {
					c.setTimeString(commentDateFormat.format(c.getCommentTime()));
				}
				s.setComments(comments);
				s.setTimeString(postDateFormat.format(s.getSnTime()));
			}
		}
		map.addAttribute("socialNetworks", socialNetworks);
		return map;
	}

	public SocialNetwork getById(String snId) {
		logger.info("Getting the information for " + snId);
		SocialNetwork socialNetwork = socialNetworkRepository.findById(Long.parseLong(snId)).get();
		List<SNComment> comments = snCommentRepository.findBySnIdOrderByCommentTime(socialNetwork);
		for (SNComment c : comments) {
			c.setTimeString(commentDateFormat.format(c.getCommentTime()));
		}
		socialNetwork.setComments(comments);
		return socialNetwork;
	}

	public List<SocialNetwork> searchByUserAndDescription(String userInput) {
		List<SocialNetwork> socialNetworks = new LinkedList<SocialNetwork>();
		List<User> users = new ArrayList<User>();
		logger.info("Searching the post written by user with name contains " + userInput);
		List<Veterinarian> veterinarianList = veterinarianServices.findByName(userInput);
		List<PetOwner> petOwnerList = petOwnerServices.findByName(userInput);

		if (veterinarianList.size() > 0) {
			for (Veterinarian v : veterinarianList) {
				users.add(v.getUser());
			}
		}
		if (petOwnerList.size() > 0) {
			for (PetOwner p : petOwnerList) {
				users.add(p.getUser());
			}
		}
		if (users.size() > 0) {
			for (User u : users) {
				socialNetworks.addAll(socialNetworkRepository.findByWritter(u));
			}
		}
		logger.info("Searching the post contains " + userInput);
		socialNetworks.addAll(socialNetworkRepository.findByDescriptionsContainingIgnoreCase(userInput));
		if (socialNetworks != null) {
			for (SocialNetwork s : socialNetworks) {
				List<SNComment> comments = snCommentRepository.findTop3BySnIdOrderByCommentTimeDesc(s);
				for (SNComment c : comments) {
					c.setTimeString(commentDateFormat.format(c.getCommentTime()));
				}
				s.setComments(comments);
				s.setTimeString(postDateFormat.format(s.getSnTime()));
			}
		}
		return socialNetworks;

	}

	public void create(SocialNetwork socialNetwork) {
		logger.info("Creating the post " + socialNetwork);
		socialNetworkRepository.saveAndFlush(socialNetwork);
	}

	public void writeComment(SNComment snComment) {
		logger.info("Creating new comment on the post " + snComment.getSnId());
		snCommentRepository.saveAndFlush(snComment);
	}
}
