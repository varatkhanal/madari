package com.vadin.services;

import java.util.List;

import com.vadin.model.Post;
import com.vadin.model.response.MultipleListResponse;

public interface PostService {
	List<Post> getAllPosts();
	Post getPostById(Long id);
	void deletePost(Long postId);
	Post createPost(Post post);
	Post updatePost(Long postId, Post post);
	MultipleListResponse getPostByUserId(String userId);
}
