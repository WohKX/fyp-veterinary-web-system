package com.apufyp.wohkx.vetassociatesystem.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.apufyp.wohkx.vetassociatesystem.constant.MessageStatusEnum;
import com.apufyp.wohkx.vetassociatesystem.model.ChatMessage;
import com.apufyp.wohkx.vetassociatesystem.model.ChatRoom;
import com.apufyp.wohkx.vetassociatesystem.model.User;
import com.apufyp.wohkx.vetassociatesystem.repository.ChatMessageRepository;
import com.apufyp.wohkx.vetassociatesystem.repository.ChatRoomRepository;

@Service
public class ChatRoomServices {

	@Autowired
	private UserServices userServices;

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	private static final Logger logger = LoggerFactory.getLogger(ChatRoomServices.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm MMM-dd");

	public ChatRoom getChatRoomByUsers(String userId1, String userId2) {
		User user1 = userServices.findById(userId1);
		User user2 = userServices.findById(userId2);
		logger.info("Finding chat room of" + userId1 + " and " + userId2);
		if (chatRoomRepository.findByUserOneAndUserTwo(user1, user2).size() > 0) {
			return chatRoomRepository.getByUserOneAndUserTwo(user1, user2);
		} else if (chatRoomRepository.findByUserOneAndUserTwo(user2, user1).size() > 0) {
			return chatRoomRepository.getByUserOneAndUserTwo(user2, user1);
		} else {
			return null;
		}
	}

	public void create(ChatRoom chatRoom) {
		logger.info("Creating new chat room for " + chatRoom);
		Date date = new Date();
		chatRoom.setMessageTime(date);
		chatRoomRepository.saveAndFlush(chatRoom);
	}

	public void createNewMessage(ChatMessage chatMessage) {
		logger.info("Creating new message for " + chatMessage);
		chatMessageRepository.saveAndFlush(chatMessage);
	}

	public void update(ChatRoom chatRoom) {
		logger.info("Updating chat room " + chatRoom.getId());
		Date date = new Date();
		chatRoom.setMessageTime(date);
		System.out.println(date);
		System.out.println(date);
		System.out.println(date);
		System.out.println(date);
		System.out.println(date);
		System.out.println(date);

		chatRoomRepository.saveAndFlush(chatRoom);
	}

	public List<ChatRoom> getChatRoomListByUser(String userId) {
		logger.info("Getting all chat room for " + userId);
		User user = userServices.findById(userId);
		if (chatRoomRepository.findByUserOne(user).size() > 0 || chatRoomRepository.findByUserTwo(user).size() > 0) {
			List<ChatRoom> chatRooms = new LinkedList<ChatRoom>();
			chatRooms.addAll(chatRoomRepository.getAllChatRoomByUser(user, Sort.by(Direction.DESC,"messageTime")));
			int count = 0;
			for (ChatRoom c : chatRooms) {
				List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoom(c, Sort.by("messageTime"));
				if (chatMessages.size() > 0) {
					for (ChatMessage m : chatMessages) {
						if (!(m.getMessageStatus().equals(MessageStatusEnum.RECEIVED.toString())
								|| (m.getSender().equals(user)))) {
							count++;
						}
					}
				}
				c.setNewMessageCount(count);
			}
			return chatRooms;
		} else {
			logger.info("No chat room found for " + userId);
			return null;
		}
	}

	public ChatRoom getChatRoomById(String id) {
		logger.info("Getting chat room: " + id);
		return chatRoomRepository.findById(Long.parseLong(id)).get();
	}

	public int getNotificationCount(String userId) {
		logger.info("Counting new message for " + userId);
		User user = userServices.findById(userId);
		if (chatRoomRepository.findByUserOne(user).size() > 0 || chatRoomRepository.findByUserTwo(user).size() > 0) {
			List<ChatRoom> chatRooms = new LinkedList<ChatRoom>();
			chatRooms.addAll(chatRoomRepository.getAllChatRoomByUser(user, Sort.by("messageTime")));
			int count = 0;
			for (ChatRoom c : chatRooms) {
				List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoom(c, Sort.by("messageTime"));
				if (chatMessages.size() > 0) {
					for (ChatMessage m : chatMessages) {
						if (!(m.getMessageStatus().equals(MessageStatusEnum.RECEIVED.toString())
								|| (m.getSender().equals(user)))) {
							count++;
						}
					}
				}
			}
			return count;
		} else {
			logger.info("No chat room found for " + userId);
			return 0;
		}

	}

	public List<ChatMessage> receiveMessage(String sessionUserId, String userId2) {
		List<ChatMessage> returnList = new LinkedList<ChatMessage>();
		ChatRoom chatRoom = getChatRoomByUsers(sessionUserId, userId2);
		User sessionUser = userServices.findById(sessionUserId);
		logger.info("Receiving new message for " + sessionUserId + " from " + userId2);
		List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoom(chatRoom, Sort.by("messageTime"));
		for (ChatMessage m : chatMessages) {
			if (!(m.getMessageStatus().equals(MessageStatusEnum.RECEIVED.toString())
					|| (m.getSender().equals(sessionUser)))) {
				m.setTimeString(dateFormat.format(m.getMessageTime()));
				returnList.add(m);
			}
		}
		updateStatuses(returnList, MessageStatusEnum.RECEIVED);
		return returnList;
	}

	public List<ChatMessage> getMessageByChatRoom(ChatRoom chatRoom) {
		List<ChatMessage> chatMessages = new LinkedList<ChatMessage>();
		List<ChatMessage> oldList = new LinkedList<ChatMessage>();
		List<ChatMessage> notifiedList = new LinkedList<ChatMessage>();
		List<ChatMessage> deliveredList = new LinkedList<ChatMessage>();
		logger.info("Getting old message for " + chatRoom.getId());

		oldList.addAll(chatMessageRepository.findTop20ByChatRoomAndMessageStatusOrderByMessageTimeDesc(chatRoom,
				MessageStatusEnum.RECEIVED.toString()));
		logger.info("Getting new message for " + chatRoom.getId());
		notifiedList.addAll(chatMessageRepository.findByChatRoomAndMessageStatusOrderByMessageTimeDesc(chatRoom,
				MessageStatusEnum.NOTIFIED.toString()));
		deliveredList.addAll(chatMessageRepository.findByChatRoomAndMessageStatusOrderByMessageTimeDesc(chatRoom,
				MessageStatusEnum.DELIVERED.toString()));
		if (oldList.size() > 0) {
			chatMessages.addAll(reOrdering(oldList));
		}
		if (notifiedList.size() > 0) {
			chatMessages.addAll(reOrdering(notifiedList));
			updateStatuses(notifiedList, MessageStatusEnum.RECEIVED);
		}
		if (deliveredList.size() > 0) {
			chatMessages.addAll(reOrdering(deliveredList));
			updateStatuses(deliveredList, MessageStatusEnum.RECEIVED);
		}
		if (chatMessages.size() > 0) {
			for (ChatMessage m : chatMessages) {
				m.setTimeString(dateFormat.format(m.getMessageTime()));
			}
		}
		return chatMessages;
	}

	public List<ChatMessage> getAllMessageByChatRoom(ChatRoom chatRoom) {
		List<ChatMessage> chatMessages = new LinkedList<ChatMessage>();
		logger.info("Getting all message for " + chatRoom.getId());
		chatMessages.addAll(chatMessageRepository.findByChatRoom(chatRoom, Sort.by("messageTime")));
		if (chatMessages.size() > 0) {
			for (ChatMessage m : chatMessages) {
				m.setTimeString(dateFormat.format(m.getMessageTime()));
			}
		}
		return chatMessages;
	}

	public void updateMessageStatusUponNotified(List<ChatRoom> chatRooms) {
		List<ChatMessage> allNewChatMessages = new ArrayList<ChatMessage>();
		for (ChatRoom r : chatRooms) {
			List<ChatMessage> messages = chatMessageRepository.findByChatRoomAndMessageStatus(r,
					MessageStatusEnum.DELIVERED.toString());
			if (messages.size() > 0) {
				allNewChatMessages.addAll(messages);
			}
		}
		updateStatuses(allNewChatMessages, MessageStatusEnum.NOTIFIED);
	}

	private void updateStatuses(List<ChatMessage> chatMessages, MessageStatusEnum status) {
		for (ChatMessage c : chatMessages) {
			c.setMessageStatus(status.toString());
		}
		logger.info("Updating the message status to :" + status);
		chatMessageRepository.saveAllAndFlush(chatMessages);
	}

	private List<ChatMessage> reOrdering(List<ChatMessage> chatMessages) {
		Collections.reverse(chatMessages);
		return chatMessages;
	}
}