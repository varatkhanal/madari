package com.madari.repository;

import org.springframework.data.repository.CrudRepository;

import com.madari.model.Address;

public interface AddressRepository  extends CrudRepository<Address, Long>{

	Address findById(long id);
}
