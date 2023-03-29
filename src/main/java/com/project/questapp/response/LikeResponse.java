package com.project.questapp.response;

import com.project.questapp.entities.Like;

import lombok.Data;

@Data
public class LikeResponse {
	private int id;
	private int userId;
	private int postId;
	
	public LikeResponse(Like like) {
		this.id = like.getId();
		this.userId = like.getUser().getId();
		this.postId = like.getPost().getId();
	}
}
