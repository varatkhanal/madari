package com.vadin.repository;

import org.springframework.data.repository.CrudRepository;

import com.vadin.model.Address;

public interface AddressRepository  extends CrudRepository<Address, Long>{

	Address findById(long id);
}
