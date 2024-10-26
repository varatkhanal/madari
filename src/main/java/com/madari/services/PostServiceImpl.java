package com.vadin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vadin.model.Course;
import com.vadin.model.Experience;
import com.vadin.model.Post;
import com.vadin.model.Users;
import com.vadin.model.response.MultipleListResponse;
import com.vadin.repository.PostRepository;
import com.vadin.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
    	Optional<Post> postOptional = postRepository.findById(postId);
    	Post post = postOptional.orElseThrow();
        return post;
    }
    public MultipleListResponse getPostByUserId(String userId){
    	List<Post> returnedPost = new ArrayList<>();
    	Users user = userRepository.findByUserId(userId);
    	
    	List<Post> allPosts = postRepository.findPostByAuthor(user);
    	List<Experience> experiences = new ArrayList<>();
    	List<Course> courses = new ArrayList<>();
    	
    	for (Post post : allPosts) {
    	    if (post instanceof Experience) {
    	        experiences.add((Experience) post);
    	    } else if (post instanceof Course) {
    	        courses.add((Course) post);
    	    }
    	}
    	MultipleListResponse response = new MultipleListResponse();
    	response.setExperienceList(experiences);
    	response.setCourseList(courses);

    	return response;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post updatedPost) {
		
        // Implement logic to update the post
        // You can use postRepository to fetch and update the post
        // Be sure to handle error cases appropriately
    	return updatedPost;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }


}

