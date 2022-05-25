package com.apufyp.wohkx.vetassociatesystem.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.apufyp.wohkx.vetassociatesystem.model.Admin;
import com.apufyp.wohkx.vetassociatesystem.model.PetOwner;
import com.apufyp.wohkx.vetassociatesystem.model.SNComment;
import com.apufyp.wohkx.vetassociatesystem.model.SocialNetwork;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.model.Veterinarian;
import com.apufyp.wohkx.vetassociatesystem.services.AdminServices;
import com.apufyp.wohkx.vetassociatesystem.services.PetOwnerServices;
import com.apufyp.wohkx.vetassociatesystem.services.SocialNetworkServices;
import com.apufyp.wohkx.vetassociatesystem.services.UserServices;
import com.apufyp.wohkx.vetassociatesystem.services.VeterinarianServices;

@Controller
@RequestMapping(path = "/home")
public class HomeController {

	@Autowired
	private SocialNetworkServices socialNetworkServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private AdminServices adminServices;

	@Autowired
	private PetOwnerServices petOwnerServices;

	@Autowired
	private VeterinarianServices veterinarianServices;

	@GetMapping("/admin/homePosts")
	public String redirectAdminHome(ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			Admin admin = adminServices.findById(UID);
			map.addAllAttributes(socialNetworkServices.get20Posts());
			map.addAttribute("admin", admin);
			return "admin/posts/home";
		} else {
			return "redirect:/admin";
		}
	}

	@PostMapping("/admin")
	public String adminHome(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return "redirect:/home/admin/homePosts";
		} else {
			return "redirect:/admin";
		}
	}

	@PostMapping("/admin/newPost")
	public String adminNewPost(@RequestParam("descriptions") String descriptions,
			@RequestParam(name = "snImage", required = false) MultipartFile snImage, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			SocialNetwork socialNetwork = new SocialNetwork(userServices.findById(UID), descriptions);
			if (!snImage.isEmpty()) {
				String contentType = snImage.getContentType();
				byte[] data = snImage.getBytes();
				String imageString = Base64.getEncoder().encodeToString(data);
				socialNetwork.setImageType(contentType);
				socialNetwork.setSnImage(imageString);
			}
			socialNetworkServices.create(socialNetwork);
			return "redirect:/home/admin/homePosts";
		} else {
			return "redirect:/admin";
		}
	}

	@GetMapping("/admin/searchPost")
	public String redirectAdminSearch(@RequestParam("searchBar") String searchBar, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			Admin admin = adminServices.findById(UID);
			List<SocialNetwork> socialNetworks = new LinkedList<SocialNetwork>();
			List<SocialNetwork> socialNetworkList = socialNetworkServices.searchByUserAndDescription(searchBar);
			if (socialNetworkList != null) {
				socialNetworks.addAll(socialNetworkList);
			}
			map.addAttribute("socialNetworks", socialNetworks);
			map.addAttribute("admin", admin);
			return "admin/posts/home";
		} else {
			return "redirect:/admin";
		}
	}

	@PostMapping("/admin/search")
	public String adminSearch(@RequestParam("searchBar") String searchBar, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return "redirect:/home/admin/searchPost?searchBar=" + searchBar;
		} else {
			return "redirect:/admin";
		}
	}

	@GetMapping("/admin/view")
	public String adminView(@RequestParam("socialNetworkId") String socialNetworkId, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			User user = userServices.findById(UID);
			SocialNetwork socialNetwork = socialNetworkServices.getById(socialNetworkId);
			map.addAttribute("user", user);
			map.addAttribute("socialNetwork", socialNetwork);
			return "admin/posts/viewPost";
		} else {
			return "redirect:/admin";
		}

	}

	@PostMapping("/admin/comment")
	public String adminComment(@RequestParam("socialNetworkId") String socialNetworkId,
			@RequestParam("descriptions") String descriptions, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			User user = userServices.findById(UID);
			SNComment comment = new SNComment(socialNetworkServices.getById(socialNetworkId), user, descriptions);
			socialNetworkServices.writeComment(comment);
			return "redirect:/home/admin/view?socialNetworkId=" + socialNetworkId;
		} else {
			return "redirect:/admin";
		}
	}

	@GetMapping("/petOwner/homePosts")
	public String redirectPetOwnerHome(@RequestParam(name = "messageCount", required = false) String messageCount,
			ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			PetOwner petOwner = petOwnerServices.findById(UID);
			map.addAllAttributes(socialNetworkServices.get20Posts());
			map.addAttribute("petOwner", petOwner);
			if (messageCount != null || messageCount != "") {
				map.addAttribute("messageCount", messageCount);
			}
			return "petOwner/posts/home";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/petOwner")
	public String petOwnerHome(ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return "redirect:/home/petOwner/homePosts";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/petOwner/newPost")
	public String petOwnerNewPost(@RequestParam("descriptions") String descriptions,
			@RequestParam(name = "snImage", required = false) MultipartFile snImage, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			SocialNetwork socialNetwork = new SocialNetwork(userServices.findById(UID), descriptions);
			if (!snImage.isEmpty()) {
				String contentType = snImage.getContentType();
				byte[] data = snImage.getBytes();
				String imageString = Base64.getEncoder().encodeToString(data);
				socialNetwork.setImageType(contentType);
				socialNetwork.setSnImage(imageString);
			}
			socialNetworkServices.create(socialNetwork);
			return "redirect:/home/petOwner/homePosts";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/petOwner/searchPost")
	public String redirectPetOwnerSearch(@RequestParam("searchBar") String searchBar, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			PetOwner petOwner = petOwnerServices.findById(UID);
			List<SocialNetwork> socialNetworks = new LinkedList<SocialNetwork>();
			List<SocialNetwork> socialNetworkList = socialNetworkServices.searchByUserAndDescription(searchBar);
			if (socialNetworkList != null) {
				socialNetworks.addAll(socialNetworkList);
			}
			map.addAttribute("socialNetworks", socialNetworks);
			map.addAttribute("petOwner", petOwner);
			return "petOwner/posts/home";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/petOwner/search")
	public String petOwnerSearch(@RequestParam("searchBar") String searchBar, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return "redirect:/home/petOwner/searchPost?searchBar=" + searchBar;
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/petOwner/view")
	public String petOwnerView(@RequestParam("socialNetworkId") String socialNetworkId, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			User user = userServices.findById(UID);
			SocialNetwork socialNetwork = socialNetworkServices.getById(socialNetworkId);
			map.addAttribute("user", user);
			map.addAttribute("socialNetwork", socialNetwork);
			return "petOwner/posts/viewPost";
		} else {
			return "redirect:/";
		}

	}

	@PostMapping("/petOwner/comment")
	public String petOwnerComment(@RequestParam("socialNetworkId") String socialNetworkId,
			@RequestParam("descriptions") String descriptions, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			User user = userServices.findById(UID);
			SNComment comment = new SNComment(socialNetworkServices.getById(socialNetworkId), user, descriptions);
			socialNetworkServices.writeComment(comment);
			return "redirect:/home/petOwner/view?socialNetworkId=" + socialNetworkId;
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/vet/homePosts")
	public String redirectVetHome(@RequestParam(name = "messageCount", required = false) String messageCount,
			ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			Veterinarian veterinarian = veterinarianServices.findById(UID);
			map.addAllAttributes(socialNetworkServices.get20Posts());
			map.addAttribute("veterinarian", veterinarian);
			if (messageCount != null || messageCount != "") {
				map.addAttribute("messageCount", messageCount);
			}
			return "vet/posts/home";
		} else {
			return "redirect:/vet";
		}
	}

	@PostMapping("/vet")
	public String vetHome(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return "redirect:/home/vet/homePosts";
		} else {
			return "redirect:/vet";
		}
	}

	@PostMapping("/vet/newPost")
	public String vetNewPost(@RequestParam("descriptions") String descriptions,
			@RequestParam(name = "snImage", required = false) MultipartFile snImage, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			SocialNetwork socialNetwork = new SocialNetwork(userServices.findById(UID), descriptions);
			if (!snImage.isEmpty()) {
				String contentType = snImage.getContentType();
				byte[] data = snImage.getBytes();
				String imageString = Base64.getEncoder().encodeToString(data);
				socialNetwork.setImageType(contentType);
				socialNetwork.setSnImage(imageString);
			}
			socialNetworkServices.create(socialNetwork);
			return "redirect:/home/vet/homePosts";
		} else {
			return "redirect:/vet";
		}
	}

	@GetMapping("/vet/searchPost")
	public String redirectVetSearch(@RequestParam("searchBar") String searchBar, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			Veterinarian veterinarian = veterinarianServices.findById(UID);
			List<SocialNetwork> socialNetworks = new LinkedList<SocialNetwork>();
			List<SocialNetwork> socialNetworkList = socialNetworkServices.searchByUserAndDescription(searchBar);
			if (socialNetworkList != null) {
				socialNetworks.addAll(socialNetworkList);
			}
			map.addAttribute("socialNetworks", socialNetworks);
			map.addAttribute("veterinarian", veterinarian);
			return "vet/posts/home";
		} else {
			return "redirect:/vet";
		}
	}

	@PostMapping("/vet/search")
	public String vetSearch(@RequestParam("searchBar") String searchBar, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return "redirect:/home/vet/searchPost?searchBar=" + searchBar;
		} else {
			return "redirect:/vet";
		}
	}

	@GetMapping("/vet/view")
	public String vetView(@RequestParam("socialNetworkId") String socialNetworkId, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			User user = userServices.findById(UID);
			SocialNetwork socialNetwork = socialNetworkServices.getById(socialNetworkId);
			map.addAttribute("user", user);
			map.addAttribute("socialNetwork", socialNetwork);
			return "vet/posts/viewPost";
		} else {
			return "redirect:/vet";
		}
	}

	@PostMapping("/vet/comment")
	public String vetComment(@RequestParam("socialNetworkId") String socialNetworkId,
			@RequestParam("descriptions") String descriptions, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			User user = userServices.findById(UID);
			SNComment comment = new SNComment(socialNetworkServices.getById(socialNetworkId), user, descriptions);
			socialNetworkServices.writeComment(comment);
			return "redirect:/home/vet/view?socialNetworkId=" + socialNetworkId;
		} else {
			return "redirect:/vet";
		}
	}
}
