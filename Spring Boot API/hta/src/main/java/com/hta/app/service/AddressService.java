package com.hta.app.service;

import java.util.Set;

import com.hta.app.model.Address;

public interface AddressService {
	
	Iterable<Address> findAll();
	Address findById(Long id);
	Address save(Address address);
	Address update(Long id, Address address);
	void deleteById(Long id);
	Set<String> getUsersWithSpecificAddressId(Long id);

}
