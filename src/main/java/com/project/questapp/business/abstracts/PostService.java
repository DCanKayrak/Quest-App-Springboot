package com.project.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.project.questapp.entities.Post;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;

public interface PostService{
	List<Post> getAllPosts(Optional<Integer> userId);
	Post getPost(int userId);
	Post createPost(PostCreateRequest newPost);
	Post updatePost(int postId, PostUpdateRequest newPost);
	void deletePost(int postId);
}
