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

import com.hta.app.DTO.AddressDTO;
import com.hta.app.model.Address;
import com.hta.app.model.User;
import com.hta.app.repository.UserRepository;
import com.hta.app.service.AddressService;

@RestController
@RequestMapping("/api/v1/addresses")
@CrossOrigin(origins = "*")
public class AddressController {
	
AddressService addressService;

private final UserRepository userRepository;

public AddressController(AddressService addressService, UserRepository userRepository) {
	this.addressService = addressService;
	this.userRepository = userRepository;
}

@GetMapping
ResponseEntity<Iterable<Address>>getAllAddress(){
	Iterable<Address> Address= addressService.findAll();
	return ResponseEntity.ok(Address);

	}

@PutMapping("/{id}")
Address updateAddress(@PathVariable("id") Long id, @RequestBody Address address) {
	Address updateAddress = addressService.update(id, address);
	return updateAddress;
	}

@DeleteMapping("/{id}")
ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
addressService.deleteById(id);
return ResponseEntity.noContent().build();
}

@PostMapping
ResponseEntity<Address> createAddress(@RequestBody AddressDTO dto) {
    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrada con id: " + dto.getUserId()));

    Address address = new Address();
    address.setZipCode(dto.getZipCode());
	address.setNeighborhood(dto.getNeighborhood());
	address.setState(dto.getState());
	address.setStreetAndNumber(dto.getStreetAndNumber());
	address.setTown(dto.getTown()); 
	address.setUser(user);

	Address newAddress = addressService.save(address);
    return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
}


}
