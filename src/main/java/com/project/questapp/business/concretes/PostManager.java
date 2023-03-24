package com.project.questapp.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.abstracts.PostRepository;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;

@Service
public class PostManager implements PostService{
	
	private PostRepository postRepository;
	private UserService userService;

	@Autowired
	public PostManager(PostRepository postRepository,UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	@Override
	public List<Post> getAllPosts(Optional<Integer> userId) {
		if(userId.isPresent()) {
			return this.postRepository.findByUserId(userId.get());
		}
		return this.postRepository.findAll();
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
