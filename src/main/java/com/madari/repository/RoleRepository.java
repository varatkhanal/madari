package com.vadin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vadin.model.Role;
import com.vadin.model.Users;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByName(String name);
	Role findByUsers(Users user);
	Role findById(long id);
}
