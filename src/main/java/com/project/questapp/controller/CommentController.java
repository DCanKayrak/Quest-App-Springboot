package com.project.questapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.business.abstracts.CommentService;
import com.project.questapp.entities.Comment;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping
	public List<Comment> getAllComments(
			@RequestParam Optional<Integer> userId,
			@RequestParam Optional<Integer> postId){
		return this.commentService.getAllComments(userId,postId);
	}
	
	@GetMapping("/{commentId}")
	public Comment getComment(@PathVariable int commentId) {
		return this.commentService.getComment(commentId);
	}
	
	@PostMapping
	public Comment createComment(@RequestBody CommentCreateRequest commentRequest) {
		return this.commentService.createComment(commentRequest);
	}
	
	@PutMapping("/{commentId}")
	public Comment updateComment(@PathVariable int commentId,@RequestBody CommentUpdateRequest commentUpdateRequest) {
		return this.commentService.updateComment(commentId,commentUpdateRequest);
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteComment(@PathVariable int commentId) {
		this.commentService.deleteComment(commentId);
	}
	
}
