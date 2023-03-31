package com.project.questapp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.abstracts.PostRepository;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.response.LikeResponse;
import com.project.questapp.response.PostResponse;

@Service
public class PostManager implements PostService{
	
	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;

	@Autowired
	public PostManager(PostRepository postRepository,UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.likeService = likeService;
	}

	@Override
	public List<PostResponse> getAllPosts(Optional<Integer> userId) {
		List<Post> list;
		if(userId.isPresent()) {
			list = this.postRepository.findByUserId(userId.get());
		}
		list = this.postRepository.findAll();
		return list.stream().map(p -> {
			List<LikeResponse> likes = likeService.getAllLike(Optional.ofNullable(null),Optional.of(p.getId()));
			return new PostResponse(p,likes);}).collect(Collectors.toList());
		
		
	}

	@Override
	public Post getPost(int userId) {
		return this.postRepository.findById(userId).orElse(null);
	}

	@Override
	public Post createPost(PostCreateRequest newPost) {
		User tempUser = this.userService.getUser(newPost.getUserId());
		if(tempUser == null) {
			return null;
		}
		Post toSave = new Post(newPost.getId(),tempUser,newPost.getTitle(),newPost.getText());
		return this.postRepository.save(toSave);
	}
	
	@Override
	public Post updatePost(int postId, PostUpdateRequest newPost) {
		Optional<Post> thePost = this.postRepository.findById(postId);
		
		if(thePost.isPresent()) {
			Post foundedPost = thePost.get();
			Post toSave = new Post(postId,foundedPost.getUser(),newPost.getTitle(),newPost.getText());
			
			return this.postRepository.save(toSave);
		}
		return null;
	}

	@Override
	public void deletePost(int postId) {
		this.postRepository.deleteById(postId);
	}


}
