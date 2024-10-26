package com.madari.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.madari.dto.UserDTO;
import com.madari.exceptions.EmailAlreadyExistsException;

public interface UserService extends UserDetailsService{
    UserDTO createUser(UserDTO userDto) throws EmailAlreadyExistsException, UsernameNotFoundException;
    UserDTO getUser(String usernme);
	UserDTO getUserByUserId(String userId);
	UserDTO updateUser(String userId, UserDTO userDto);
    void deleteUser(String userId);
	List<UserDTO> getUsers(int page, int limit);
}
