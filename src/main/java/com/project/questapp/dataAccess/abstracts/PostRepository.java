package com.project.questapp.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByUserId(int userId); 
}
