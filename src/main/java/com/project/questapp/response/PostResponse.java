package com.project.questapp.response;

import java.util.List;

import com.project.questapp.entities.Post;

import lombok.Data;

@Data
public class PostResponse {
	
	private int id;
	private int userId;
	private String userName;
	private String title;
	private String text;
	
	private List<LikeResponse> postLikes;
	
	public PostResponse(Post entity,List<LikeResponse> likes) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUsername();
		this.title = entity.getTitle();
		this.text = entity.getText();
		this.postLikes = likes;
	}
}
