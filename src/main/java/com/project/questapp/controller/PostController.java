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

import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.entities.Post;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<Post> getAllPosts(@RequestParam Optional<Integer> userId){
		return this.postService.getAllPosts(userId);
	}
	
	@GetMapping("/{postId}")
	public Post getPost(@PathVariable int userId){
		return this.postService.getPost(userId);
	}
	
	@PostMapping
	public Post createPost(@RequestBody PostCreateRequest thePost) {
		return this.postService.createPost(thePost);
	}
	
	@PutMapping("/{postId}")
	public Post updatePost(@PathVariable int postId,@RequestBody PostUpdateRequest newPost) {
		return this.postService.updatePost(postId,newPost);
	}
	
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable int postId) {
		this.postService.deletePost(postId);
	}
}
