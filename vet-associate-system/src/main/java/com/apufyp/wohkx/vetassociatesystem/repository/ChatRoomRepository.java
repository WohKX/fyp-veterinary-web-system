package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.ChatRoom;
import com.apufyp.wohkx.vetassociatesystem.model.User;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	List<ChatRoom> findByUserOneAndUserTwo(User userOne, User userTwo);

	ChatRoom getByUserOneAndUserTwo(User userOne, User userTwo);

	@Query("SELECT c FROM ChatRoom c WHERE c.userOne =:userId OR c.userTwo =:userId")
	List<ChatRoom> getAllChatRoomByUser(@Param("userId") User userId, Sort sort);
	
	List<ChatRoom> findByUserOne(User user);
	
	List<ChatRoom> findByUserTwo(User user);
}
