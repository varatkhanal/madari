package com.vadin.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vadin.dto.UserDTO;
import com.vadin.model.Post;
import com.vadin.model.Users;
import com.vadin.model.response.MultipleListResponse;
import com.vadin.services.PostService;
import com.vadin.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

 
    final private PostService postService;
    
    final private UserService userService;
    
    public PostController(PostService postService,UserService userService) {
    	this.postService = postService;
    	this.userService = userService;	
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
    	
    	return postService.getPostById(postId);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<MultipleListResponse> getAllPostByUserId(@PathVariable String userId){
    	MultipleListResponse response =postService.getPostByUserId(userId);
    	return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody Post post, @RequestParam String userId) {
    	Users user = new Users();
    	UserDTO userDto = userService.getUserByUserId(userId);
    	ModelMapper modelMapper = new ModelMapper();
  	
    	user = modelMapper.map(userDto,Users.class);
    	post.setAuthor(user);
        return postService.createPost(post);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
        return postService.updatePost(postId, updatedPost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
