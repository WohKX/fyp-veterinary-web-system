package com.apufyp.wohkx.vetassociatesystem.controllers;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.apufyp.wohkx.vetassociatesystem.constant.MessageStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.ChatMessage;
import com.apufyp.wohkx.vetassociatesystem.model.ChatRoom;
import com.apufyp.wohkx.vetassociatesystem.services.ChatRoomServices;
import com.apufyp.wohkx.vetassociatesystem.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@RequestMapping("/messages")
public class MessageController {

	@Autowired
	private UserServices userServices;

	@Autowired
	private ChatRoomServices chatRoomServices;

	@Autowired
	private ObjectMapper objectMapper;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm MMM-dd");

	@GetMapping("/petOwner")
	public String petOwnerstartMessage(@RequestParam("veterinarianId") String veterinarianId, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			ChatRoom chatRoom = chatRoomServices.getChatRoomByUsers(UID, veterinarianId);
			List<ChatMessage> chatMessages = new LinkedList<ChatMessage>();
			if (chatRoom == null) {
				chatRoom = new ChatRoom(userServices.findById(UID), userServices.findById(veterinarianId));
				chatRoomServices.create(chatRoom);
			} else {
				chatMessages.addAll(chatRoomServices.getMessageByChatRoom(chatRoom));
			}
			if (chatRoom.getUserTwo().getId().equals(UID)) {
				map.addAttribute("currentUser", chatRoom.getUserOne());
			} else {
				map.addAttribute("currentUser", chatRoom.getUserTwo());
			}
			map.addAttribute("chatMessages", chatMessages);
			map.addAttribute("UID", UID);
			return "petOwner/chats/chatMessage";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/petOwner/listChatRoom")
	public String getPetOwnerChatRoom(ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			List<ChatRoom> chatRoomList = chatRoomServices.getChatRoomListByUser(UID);
			chatRoomServices.updateMessageStatusUponNotified(chatRoomList);
			map.addAttribute("chatRoomList", chatRoomList);
			map.addAttribute("UID", UID);
			return "petOwner/chats/chatRoomList";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/petOwner/getMsg")
	public String getPetOwnerMsg(@RequestParam("currentUser") String currentUser, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			ChatRoom chatRoom = chatRoomServices.getChatRoomByUsers(currentUser, UID);
			List<ChatMessage> chatMessages = new LinkedList<ChatMessage>();
			if (chatRoom != null) {
				chatMessages.addAll(chatRoomServices.getMessageByChatRoom(chatRoom));
			}
			if (chatRoom.getUserTwo().getId().equals(UID)) {
				map.addAttribute("currentUser", chatRoom.getUserOne());
			} else {
				map.addAttribute("currentUser", chatRoom.getUserTwo());
			}
			map.addAttribute("chatMessages", chatMessages);
			map.addAttribute("UID", UID);
			return "petOwner/chats/chatMessage";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/petOwner/getAllMsg")
	public String getPetOwnerAllMsg(@RequestParam("currentUser") String currentUser, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			ChatRoom chatRoom = chatRoomServices.getChatRoomByUsers(currentUser, UID);
			List<ChatMessage> chatMessages = chatRoomServices.getAllMessageByChatRoom(chatRoom);
			if (chatRoom.getUserTwo().getId().equals(UID)) {
				map.addAttribute("currentUser", chatRoom.getUserOne());
			} else {
				map.addAttribute("currentUser", chatRoom.getUserTwo());
			}
			map.addAttribute("chatMessages", chatMessages);
			map.addAttribute("UID", UID);
			return "petOwner/chats/chatMessage";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/vet")
	public String vetStartMessage(@RequestParam("veterinarianId") String veterinarianId, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			ChatRoom chatRoom = chatRoomServices.getChatRoomByUsers(UID, veterinarianId);
			List<ChatMessage> chatMessages = new LinkedList<ChatMessage>();
			if (chatRoom == null) {
				chatRoom = new ChatRoom(userServices.findById(UID), userServices.findById(veterinarianId));
				chatRoomServices.create(chatRoom);
			} else {
				chatMessages.addAll(chatRoomServices.getMessageByChatRoom(chatRoom));
			}
			if (chatRoom.getUserTwo().getId().equals(UID)) {
				map.addAttribute("currentUser", chatRoom.getUserOne());
			} else {
				map.addAttribute("currentUser", chatRoom.getUserTwo());
			}
			map.addAttribute("chatMessages", chatMessages);
			map.addAttribute("UID", UID);
			return "vet/chats/chatMessage";
		} else {
			return "redirect:/vet";
		}
	}

	@GetMapping("/vet/listChatRoom")
	public String getVetChatRoom(ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			List<ChatRoom> chatRoomList = chatRoomServices.getChatRoomListByUser(UID);
			chatRoomServices.updateMessageStatusUponNotified(chatRoomList);
			map.addAttribute("chatRoomList", chatRoomList);
			map.addAttribute("UID", UID);
			return "vet/chats/chatRoomList";
		} else {
			return "redirect:/vet";
		}
	}

	@GetMapping("/vet/getMsg")
	public String getVetMsg(@RequestParam("currentUser") String currentUser, ModelMap map, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			ChatRoom chatRoom = chatRoomServices.getChatRoomByUsers(currentUser, UID);
			List<ChatMessage> chatMessages = new LinkedList<ChatMessage>();
			if (chatRoom != null) {
				chatMessages.addAll(chatRoomServices.getMessageByChatRoom(chatRoom));
			}
			if (chatRoom.getUserTwo().getId().equals(UID)) {
				map.addAttribute("currentUser", chatRoom.getUserOne());
			} else {
				map.addAttribute("currentUser", chatRoom.getUserTwo());
			}


			map.addAttribute("chatMessages", chatMessages);
			map.addAttribute("UID", UID);
			return "vet/chats/chatMessage";
		} else {
			return "redirect:/vet";
		}

	}

	@GetMapping("/vet/getAllMsg")
	public String getVetAllMsg(@RequestParam("currentUser") String currentUser, ModelMap map,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			ChatRoom chatRoom = chatRoomServices.getChatRoomByUsers(currentUser, UID);
			List<ChatMessage> chatMessages = chatRoomServices.getAllMessageByChatRoom(chatRoom);
			if (chatRoom.getUserTwo().getId().equals(UID)) {
				map.addAttribute("currentUser", chatRoom.getUserOne());
			} else {
				map.addAttribute("currentUser", chatRoom.getUserTwo());
			}
			map.addAttribute("chatMessages", chatMessages);
			map.addAttribute("UID", UID);
			return "vet/chats/chatMessage";
		} else {
			return "redirect:/vet";
		}

	}

	@GetMapping("/count")
	public @ResponseBody ObjectNode countNewMessage(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			int messageCount = chatRoomServices.getNotificationCount(UID);
			ObjectNode objectNode = objectMapper.createObjectNode();
			objectNode.put("messageCount", messageCount);
			return objectNode;
		} else {
			return null;
		}
	}

	@PostMapping("/receive")
	public @ResponseBody ObjectNode receiveMessage(String currentUser, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			ChatRoom chatRoom = chatRoomServices.getChatRoomByUsers(UID, currentUser);
			chatRoomServices.update(chatRoom);
			List<ChatMessage> messages = chatRoomServices.receiveMessage(UID, currentUser);
			for (ChatMessage m : messages) {
				m.setTimeString(dateFormat.format(m.getMessageTime()));
			}
			ObjectNode objectNode = objectMapper.createObjectNode();
			objectNode.put("count", messages.size());
			objectNode.putPOJO("messages", messages);
			return objectNode;
		} else {
			return null;
		}
	}

	@PostMapping("/send")
	public @ResponseBody ObjectNode sendMessage(String currentUser, String messageContent, String messageType,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String UID = (String) session.getAttribute(session.getId());
			ChatRoom chatRoom = chatRoomServices.getChatRoomByUsers(currentUser, UID);
			ChatMessage chatMessage = new ChatMessage(userServices.findById(UID),
					MessageStatusEnum.DELIVERED.toString());
			chatMessage.setMessageType(messageType);
			if (chatRoom != null) {
				chatMessage.setChatRoom(chatRoom);
				chatRoomServices.update(chatRoom);
			} else {
				ChatRoom newChatRoom = new ChatRoom(userServices.findById(UID), userServices.findById(currentUser));
				chatRoomServices.create(newChatRoom);
				chatMessage.setChatRoom(newChatRoom);
			}
			if (messageType.equals("TEXT")) {
				chatMessage.setMessageText(messageContent);
			} else if (messageType.equals("IMAGE")) {
				chatMessage.setMessageImage(messageContent);
			}
			chatRoomServices.createNewMessage(chatMessage);
			ObjectNode objectNode = objectMapper.createObjectNode();
			chatMessage.setTimeString(dateFormat.format(chatMessage.getMessageTime()));
			objectNode.putPOJO("message", chatMessage);
			return objectNode;
		} else {
			return null;
		}
	}
}
