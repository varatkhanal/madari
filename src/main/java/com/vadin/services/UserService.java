package com.vadin.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.vadin.dto.UserDTO;

public interface UserService extends UserDetailsService{
    UserDTO createUser(UserDTO userDto);
    UserDTO getUser(String usernme);
	UserDTO getUserByUserId(String userId);
	UserDTO updateUser(String userId, UserDTO userDto);
    void deleteUser(String userId);
	List<UserDTO> getUsers(int page, int limit);
}
