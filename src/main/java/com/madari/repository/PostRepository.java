package com.madari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madari.model.Post;
import com.madari.model.Users;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findAll();
	List<Post> findPostByAuthor(Users user);

}
