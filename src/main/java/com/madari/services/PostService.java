package com.madari.services;

import java.util.List;

import com.madari.dto.PostDTO;
import com.madari.model.Post;
import com.madari.model.response.MultipleListResponse;

public interface PostService {
	List<PostDTO> getAllPosts();
	PostDTO getPostById(Long id);
	void deletePost(Long postId);
	PostDTO createPost(PostDTO post, String userId);
	PostDTO updatePost(Long postId, Post post);
	MultipleListResponse getPostByUserId(String userId);
}
