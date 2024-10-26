package com.madari.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.madari.model.Privilege;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long>{
	//Role findByName(String name);
	Privilege findByName(String name);
	Privilege findById(long id);

	//List<Privilege> findByRoles(Role role);


}
