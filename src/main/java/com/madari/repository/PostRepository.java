package com.vadin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vadin.model.Post;
import com.vadin.model.Users;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findAll();
	List<Post> findPostByAuthor(Users user);

}
