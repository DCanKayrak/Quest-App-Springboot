package com.project.questapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.response.LikeResponse;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
	private LikeService theLikeService;

	@Autowired
	public LikeController(LikeService theLikeService) {
		this.theLikeService = theLikeService;
	}
	
	
	@GetMapping
	public List<LikeResponse> getAllPosts(@RequestParam Optional<Integer> userId,@RequestParam Optional<Integer> postId){
		return this.theLikeService.getAllLike(userId,postId);
	}
	
	@GetMapping("/{likeId}")
	public LikeResponse getLike(@PathVariable int likeId){
		return this.theLikeService.getLike(likeId);
	}
	
	@PostMapping
	public LikeResponse createLike(@RequestBody LikeCreateRequest theLike) {
		return this.theLikeService.createLike(theLike);
	}
	
	
	@DeleteMapping("/{likeId}")
	public void deletePost(@PathVariable int likeId) {
		this.theLikeService.deleteLike(likeId);
	}
	
}
