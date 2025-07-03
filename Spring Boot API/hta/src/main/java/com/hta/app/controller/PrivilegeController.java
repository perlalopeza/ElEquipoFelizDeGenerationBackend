package com.hta.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hta.app.model.Privilege;
import com.hta.app.model.Product;
import com.hta.app.service.PrivilegeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/privileges")
public class PrivilegeController {
	
	PrivilegeService privilegeService;

	public PrivilegeController(PrivilegeService privilegeService) {
		super();
		this.privilegeService = privilegeService;
	}
	

	
	@GetMapping("/{id}")
	Privilege getPrivilegeById(@PathVariable("id")Long id) {
		Privilege existingPrivilege = privilegeService.findById(id);
		return existingPrivilege;
	}
	
	
	
	@PutMapping("/{id}")
	Privilege updatePrivilege(@PathVariable("id") Long id, @RequestBody Privilege privilege) {
		Privilege updatedPrivilege = privilegeService.update(id, privilege);
		return updatedPrivilege;
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deletePrivilege(@PathVariable("id") Long id) {
		privilegeService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	ResponseEntity<Privilege> createPrivilege(@RequestBody Privilege privilege) {
		Privilege newPrivilege = privilegeService.save(privilege);
		return new ResponseEntity<Privilege>(newPrivilege, HttpStatus.CREATED);
	}
	
	
	
	
	
}
