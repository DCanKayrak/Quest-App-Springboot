package com.project.questapp.requests;

import lombok.Data;

@Data
public class PostCreateRequest {
	
	private int id;
	private int userId;
	private String title;
	private String text;
}
