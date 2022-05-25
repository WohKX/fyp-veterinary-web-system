package com.apufyp.wohkx.vetassociatesystem.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.apufyp.wohkx.vetassociatesystem.constant.ChatbotTypeEnum;
import com.apufyp.wohkx.vetassociatesystem.model.Chatbot;
import com.apufyp.wohkx.vetassociatesystem.services.AdminServices;
import com.apufyp.wohkx.vetassociatesystem.services.ChatbotServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@RequestMapping("/chatbot")
public class ChatbotController {

	@Autowired
	private ChatbotServices chatbotServices;

	@Autowired
	private AdminServices adminServices;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/admin/add")
	public String adminAdd(@RequestParam(name = "id", required = false) String id, ModelMap map) {
		if (id == null || id == "") {
			List<String> classes = chatbotServices.findClasses();
			map.addAttribute("chatbot", null);
			map.addAttribute("classes", classes);
		} else {
			Chatbot chatbot = chatbotServices.findById(id);
			map.addAttribute("chatbot", chatbot);
		}
		return "admin/chatbot/chatbotManage";
	}

	@PostMapping("/admin/view")
	public String adminView(@RequestParam("id") String id, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return "redirect:/chatbot/admin/add?id=" + id;
		} else {
			return "redirect:/admin";
		}
	}

	@PostMapping("/admin/edit")
	public String adminEditChatbot(@RequestParam("id") String id, @RequestParam("questions") String questions,
			@RequestParam("answers") String answers, @RequestParam("manageChatbotButton") String manageChatbotButton,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			String msg = "";
			if (manageChatbotButton.equals("Save")) {
				Chatbot chatbot = chatbotServices.findById(id);
				chatbot.setAnswers(answers);
				chatbot.setQuestions(questions);
				chatbot.setEditors(adminServices.findById(UID));
				msg = chatbotServices.updateChatbot(chatbot);
			} else if (manageChatbotButton.equals("Delete")) {
				msg = chatbotServices.deleteChatbotById(id);
			}
			return "redirect:/nav/admin?adminNavButton=ManageChatbot&msg=" + msg;
		} else {
			return "redirect:/admin";
		}

	}

	@PostMapping("/admin/create")
	public String adminCreateChatbot(@RequestParam("classes") String classes,
			@RequestParam("questions") String questions, @RequestParam("answers") String answers, ModelMap map,
			HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			Chatbot chatbot = new Chatbot(questions, answers, adminServices.findById(UID));
			chatbot.setClasses(classes);
			String msg = chatbotServices.create(chatbot);
			return "redirect:/nav/admin?adminNavButton=ManageChatbot&msg=" + msg;
		} else {
			return "redirect:/admin";
		}
	}

	@PostMapping("/send")
	public @ResponseBody ObjectNode getChatbotResponse(String messageContent) {
		String response = chatbotServices.getReply(messageContent);
		ObjectNode objectNode = objectMapper.createObjectNode();
		if (response.equals(ChatbotTypeEnum.Veterinarian.toString())
				|| response.equals(ChatbotTypeEnum.ClinicHospital.toString())
				|| response.equals(ChatbotTypeEnum.Profile.toString())
				|| response.equals(ChatbotTypeEnum.Ticket.toString()) || response.equals("Communicate with Vet now!")
				|| response.equals("Sorry, I cannot answer the question, please raise an inquiry here.")) {
			objectNode.put("responseType", "button");
			if (response.equals(ChatbotTypeEnum.ClinicHospital.toString())) {
				objectNode.put("responseValue", "SearchCH");
				objectNode.put("response", "Search clinic/hospital here.");
			} else if (response.equals(ChatbotTypeEnum.Veterinarian.toString())) {
				objectNode.put("responseValue", "SearchVet");
				objectNode.put("response", "Search veterinarian and start conversation.");
			} else if (response.equals(ChatbotTypeEnum.Profile.toString())) {
				objectNode.put("responseValue", "Profile");
				objectNode.put("response", "Edit profile or password.");
			} else if (response.equals(ChatbotTypeEnum.Ticket.toString())) {
				objectNode.put("responseValue", "Inquiry");
				objectNode.put("response", "Raise any problem you met.");
			} else if (response.equals("Communicate with Vet now!")) {
				objectNode.put("responseValue", "SearchVet");
				objectNode.put("response", response);
			} else {
				objectNode.put("responseValue", "Inquiry");
				objectNode.put("response", response);
			}

		} else {
			objectNode.put("responseType", "text");
			objectNode.put("response", response);
		}
		return objectNode;
	}
}
