package com.project.questapp.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {
	private int id;
	private int userId;
	private int postId;
}
