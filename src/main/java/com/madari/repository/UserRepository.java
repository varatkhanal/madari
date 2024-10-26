package com.vadin.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.vadin.model.Users;


@Repository
public interface UserRepository extends PagingAndSortingRepository<Users, Long> {
	Users findByEmail(String email);
	Users findByUserId(String userId);
	Users findByUserName(String userName);
}