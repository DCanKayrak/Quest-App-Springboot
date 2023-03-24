package com.project.questapp.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer>{

	List<Comment> findByUserIdAndPostId(Integer userId, Integer postId);

	List<Comment> findByUserId(Integer userId);

	List<Comment> findByPostId(Integer postId);

}
