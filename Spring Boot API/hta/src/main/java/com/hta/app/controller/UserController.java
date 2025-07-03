package com.hta.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hta.app.DTO.UserDTO;
import com.hta.app.model.Privilege;
import com.hta.app.model.Product;
import com.hta.app.model.User;
import com.hta.app.repository.PrivilegeRepository;
import com.hta.app.service.UserService;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/v1/users")
public class UserController {
    UserService userService;
    private final PrivilegeRepository privilegeRepository;
    
	public UserController(UserService userService, PrivilegeRepository privilegeRepository) {
		
		this.userService = userService;
		this.privilegeRepository = privilegeRepository;
	}
    
		
	@GetMapping("/{id}")
	User getUserById(@PathVariable("id")Long id) {
		User existingUser = userService.findById(id);
		return existingUser;
	}
	
	
	
	@PutMapping("/{id}")
	User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		User updatedUser = userService.update(id, user);
		return updatedUser;
		}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
	userService.deleteById(id);
	return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	ResponseEntity<User> createUser(@RequestBody UserDTO dto) {
	    Privilege privilege = privilegeRepository.findById(dto.getPrivilegeId())
	        .orElseThrow(() -> new RuntimeException("Privilegio no encontrado con id: " + dto.getPrivilegeId()));

	    User user = new User();
	    user.setName(dto.getName());
	    user.setLastName(dto.getLastName());
	    user.setPhone(dto.getPhone());
	    user.setEmail(dto.getEmail());
	    user.setPassword(dto.getPassword());
	    user.setPrivilege(privilege);
	    
	   

	    User newUser = userService.save(user);
	    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	
}
