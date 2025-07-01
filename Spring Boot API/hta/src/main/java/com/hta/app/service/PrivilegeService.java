package com.hta.app.service;

import java.util.Set;

import com.hta.app.model.Privilege;

public interface PrivilegeService {
	
	Iterable<Privilege> findAll();
	Privilege findById(Long id);
	Privilege save(Privilege privilege);
	Privilege update(Long id, Privilege privilege);
	void deleteById(Long id);
	Set<String> getUsersWithSpecificRoleId(Long id);
	
}
