package com.hta.app.service;

import java.util.Set;

import com.hta.app.model.User;

public interface UserService {
	Iterable<User> findAll();
	User findById(Long id);
	User save(User user);
	User update(Long id, User user);
	void deleteById(Long id);
	Set<String> getUsersWithSpecificUserId(Long id);
	User findByEmail(String email);
}
