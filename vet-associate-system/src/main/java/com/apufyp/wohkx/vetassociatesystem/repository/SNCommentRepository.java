package com.apufyp.wohkx.vetassociatesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apufyp.wohkx.vetassociatesystem.model.SNComment;
import com.apufyp.wohkx.vetassociatesystem.model.SocialNetwork;

@Repository
public interface SNCommentRepository extends JpaRepository<SNComment, Long>{

	List<SNComment> findTop3BySnIdOrderByCommentTimeDesc(SocialNetwork snId);
		
	List<SNComment> findBySnIdOrderByCommentTime(SocialNetwork snId);
}
