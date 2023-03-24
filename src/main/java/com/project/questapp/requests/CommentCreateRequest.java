package com.project.questapp.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
	
	private int id;
	private int userId;
	private int postId;
	private String text;
}
