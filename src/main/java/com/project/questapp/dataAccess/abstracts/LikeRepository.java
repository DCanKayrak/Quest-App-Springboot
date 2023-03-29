package com.project.questapp.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Like;

public interface LikeRepository extends JpaRepository<Like,Integer>{
	public List<Like> findByUserIdAndPostId(int userId,int postId);
	public List<Like> findByUserId(int userId);
	public List<Like> findByPostId(int postId);
}
