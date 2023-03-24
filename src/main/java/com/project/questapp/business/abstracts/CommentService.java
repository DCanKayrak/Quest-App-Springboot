package com.project.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.project.questapp.entities.Comment;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;

public interface CommentService {

	public List<Comment> getAllComments(Optional<Integer> userId, Optional<Integer> postId);

	public Comment getComment(int commentId);
	
	public Comment createComment(CommentCreateRequest commentRequest);
	
	public Comment updateComment(int commentId, CommentUpdateRequest request);

	public void deleteComment(int commentId);
}
