package com.hta.app.service.Impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hta.app.model.User;
import com.hta.app.repository.UserRepository;
import com.hta.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();

	}

	@Override
	public User findById(Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		if(userOpt.isEmpty()) { 
			throw new IllegalStateException("User with id: "+ id +" does not exist");
		}
		User existingUser = userOpt.get();
		return existingUser;
	}

	@Override
	public User save(User user) {
		user.setId(null); 
		User newUser = userRepository.save(user);
		return newUser;

	}

	@Override
	public User update(Long id, User user) {
		User existingUser = findById(id);
		existingUser.setName(user.getName());
		existingUser.setLastName(user.getLastName());
		existingUser.setPhone(user.getPhone());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		//existingUser.setCreatedAt(user.getCreatedAt());
		existingUser.setPrivilege(user.getPrivilege());
		
		User updatedUser = userRepository.save(existingUser);
		return updatedUser;
	}

	@Override
	public void deleteById(Long id) {
		User existingUser = findById(id);
		userRepository.delete(existingUser);
	}

	@Override
	public Set<String> getUsersWithSpecificUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}