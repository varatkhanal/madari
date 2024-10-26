package com.madari.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.madari.dto.PostDTO;
import com.madari.dto.UserDTO;
import com.madari.model.Post;
import com.madari.model.Users;
import com.madari.model.request.PostRequestModel;
import com.madari.model.response.MultipleListResponse;
import com.madari.model.response.PostResponseModel;
import com.madari.services.PostService;
import com.madari.services.UserService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    public List<PostResponseModel> getAllPosts() {
        List<PostDTO> postLists= postService.getAllPosts();
        ModelMapper modelMapper = new ModelMapper();
        List<PostResponseModel> responseList =  postLists.stream().map(post->modelMapper.map(post,PostResponseModel.class)).collect(Collectors.toList());
        return responseList;
    }
    

    @GetMapping("/{postId}")
    public PostResponseModel getPostById(@PathVariable Long postId) {
        ModelMapper modelMapper = new ModelMapper();
    	return modelMapper.map(postService.getPostById(postId),PostResponseModel.class);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<MultipleListResponse> getAllPostByUserId(@PathVariable String userId){
    	MultipleListResponse response =postService.getPostByUserId(userId);
    	return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public PostResponseModel createPost(@RequestBody PostRequestModel post, @RequestParam String userId) {
    	//Users user = new Users();
    	//UserDTO userDto = userService.getUserByUserId(userId);
    	ModelMapper modelMapper = new ModelMapper();
    	PostDTO postDto = modelMapper.map(post, PostDTO.class);  	
        return modelMapper.map(postService.createPost(postDto,userId),PostResponseModel.class);
    }

    @PutMapping("/{postId}")
    public PostResponseModel updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
        ModelMapper modelMapper = new ModelMapper();
    	return modelMapper.map(postService.updatePost(postId, updatedPost),PostResponseModel.class);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
