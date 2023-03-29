package com.project.questapp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.abstracts.LikeRepository;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.response.LikeResponse;


@Service
public class LikeManager implements LikeService{
	
	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;
	
	
	@Autowired
	public LikeManager(LikeRepository likeRepository, UserService userService, PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}


	@Override
	public List<LikeResponse> getAllLike(Optional<Integer> userId, Optional<Integer> postId) {
		
		List<Like> list;
		if(userId.isPresent() && postId.isPresent()) {
			list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
		}else if(userId.isPresent()) {
			list = likeRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			list = this.likeRepository.findByPostId(postId.get());
		}else
			list = likeRepository.findAll();
		return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
	}

	@Override
	public LikeResponse getLike(int likeId) {
		Like theLike = this.likeRepository.findById(likeId).orElse(null);
		return new LikeResponse(theLike);
	}


	@Override
	public LikeResponse createLike(LikeCreateRequest newLike) {
		User theUser = this.userService.getUser(newLike.getUserId());
		Post thePost = this.postService.getPost(newLike.getPostId());
		
		if(theUser != null && thePost != null) {
			Like theLike = this.likeRepository.save(new Like(newLike.getId(),thePost,theUser));
			return new LikeResponse(theLike); 
		}else {
			throw new RuntimeException("Post Veya User Bulunamadı!");
		}
	}

	@Override
	public void deleteLike(int likeId) {
		this.likeRepository.deleteById(likeId);
		
	}

}
