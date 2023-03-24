package com.project.questapp.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.CommentService;
import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.abstracts.CommentRepository;
import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;

@Service
public class CommentManager implements CommentService{
	
	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	@Autowired
	public CommentManager(CommentRepository commentRepository, UserService userService, PostService postService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postService = postService;
	}

	@Override
	public List<Comment> getAllComments(Optional<Integer> userId, Optional<Integer> postId) {
		
		if(userId.isPresent() && postId.isPresent()) {
			return this.commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
		}else if(userId.isPresent()) {
			return this.commentRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			return this.commentRepository.findByPostId(postId.get());
		}else {
			return this.commentRepository.findAll();
		}
		
	}


	@Override
	public Comment getComment(int commentId) {
		return this.commentRepository.findById(commentId).orElse(null);
	}

	@Override
	public Comment createComment(CommentCreateRequest newCommentRequest) {
		// request üzerinden requestleri handlelıyoruz.
		User theUser = this.userService.getUser(newCommentRequest.getId());
		Post thePost = this.postService.getPost(newCommentRequest.getPostId());
		
		if(theUser != null && thePost != null) {
			Comment createdComment = new Comment(
					newCommentRequest.getId(),
					theUser,thePost,newCommentRequest.getText());
			
			return this.commentRepository.save(createdComment);
		}
		return null;
	}

	@Override
	public Comment updateComment(int commentId, CommentUpdateRequest request) {
		Optional<Comment> theComment = this.commentRepository.findById(commentId);
		
		if(theComment.isPresent()) {
			Comment toUpdate = theComment.get();
			toUpdate.setText(request.getText());
			
			return this.commentRepository.save(toUpdate);
		}
		return null;
	}

	@Override
	public void deleteComment(int commentId) {
		this.commentRepository.deleteById(commentId);
	}
	
	
}
