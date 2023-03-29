package com.project.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.response.LikeResponse;

public interface LikeService {
	List<LikeResponse> getAllLike(Optional<Integer> userId,Optional<Integer> postId);
	LikeResponse getLike(int likeId);
	LikeResponse createLike(LikeCreateRequest newLike);
	void deleteLike(int likeId);
}
