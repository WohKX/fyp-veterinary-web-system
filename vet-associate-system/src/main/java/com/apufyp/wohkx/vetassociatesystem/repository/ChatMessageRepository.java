package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.ChatMessage;
import com.apufyp.wohkx.vetassociatesystem.model.ChatRoom;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	@Query("SELECT m FROM ChatMessage m WHERE m.chatRoom=:chatRoom AND m.messageStatus=:messageStatus")
	List<ChatMessage> findNewByChatRoom(@Param("chatRoom") ChatRoom chatRoom,
			@Param("messageStatus") String messageStatus, Sort sort);

	@Query("SELECT m FROM ChatMessage m WHERE m.chatRoom=:chatRoom ORDER BY m.messageTime DESC")
	List<ChatMessage> findOldByChatRoomPageable(@Param("chatRoom") ChatRoom chatRoom, Pageable pageable);

	List<ChatMessage> findByChatRoomAndMessageStatusOrderByMessageTimeDesc(ChatRoom chatRoom, String messageStatus);
	
	List<ChatMessage> findTop20ByChatRoomAndMessageStatusOrderByMessageTimeDesc(ChatRoom chatRoom, String messageStatus);
	
	List<ChatMessage> findByChatRoom(ChatRoom chatRoom, Sort sort);

	List<ChatMessage> findByChatRoomAndMessageStatus(ChatRoom chatRoom, String messageStatus);
}
