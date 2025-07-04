package com.hta.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.hta.app.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	boolean existsByEmail(String email);

}
