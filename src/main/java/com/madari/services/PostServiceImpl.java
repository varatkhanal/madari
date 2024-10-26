package com.madari.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madari.dto.PostDTO;
import com.madari.model.Course;
import com.madari.model.Experience;
import com.madari.model.Post;
import com.madari.model.Users;
import com.madari.model.response.MultipleListResponse;
import com.madari.repository.PostRepository;
import com.madari.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    

    public List<PostDTO> getAllPosts() {
    	
    	List<Post> postList = postRepository.findAll();
    	ModelMapper modelMapper = new ModelMapper();
    	List<PostDTO> postDtos = postList.stream().map(post->modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());;
    	
        return postDtos;
    }

    public PostDTO getPostById(Long postId) {
    	Optional<Post> postOptional = postRepository.findById(postId);
    	Post post = postOptional.orElseThrow();
    	ModelMapper modelMapper = new ModelMapper();
    	PostDTO postDto = modelMapper.map(post, PostDTO.class);
        return postDto;
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

    public PostDTO createPost(PostDTO postDto, String userId) {
    	ModelMapper modelMapper = new ModelMapper();  	
    	Users user = userRepository.findByUserId(userId);
    	Post post = modelMapper.map(postDto,Post.class);
    	post.setAuthor(user);
    	Post savedPost = postRepository.save(post);
    	PostDTO savedDto = modelMapper.map(savedPost,PostDTO.class);
        return savedDto;
    }

    public PostDTO updatePost(Long postId, Post updatedPost) {
		
        // Implement logic to update the post
        // You can use postRepository to fetch and update the post
        // Be sure to handle error cases appropriately
    	PostDTO postDto = new PostDTO();
    	return postDto;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

	


}

