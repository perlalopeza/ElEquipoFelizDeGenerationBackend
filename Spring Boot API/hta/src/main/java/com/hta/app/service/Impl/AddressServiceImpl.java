package com.hta.app.service.Impl;

import java.util.Optional;
import java.util.Set;

import com.hta.app.model.Address;
import com.hta.app.repository.AddressRepository;
import com.hta.app.service.AddressService;

public class AddressServiceImpl implements AddressService {
	
	AddressRepository addressRepository;

	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	@Override
	public Iterable<Address> findAll() {
	  
		return addressRepository.findAll();
	}
	
	@Override
	public Address findById(Long id) {
		
			Optional<Address> addressOpt = addressRepository.findById(id);
			if(addressOpt.isEmpty()) { 
				throw new IllegalStateException("Address with id: "+ id +" does not exist");
			}
			Address existingAddress = addressOpt.get();
			return existingAddress;
		};

		@Override
		public Address save(Address address) {
			address.setId(null); 
			Address newAddress = addressRepository.save(address);
			
			return newAddress;
		}
		
		@Override
		public Address update(Long id, Address address) {
			Address existingAddress = findById(id);
			
			existingAddress.setZipCode(address.getZipCode());
			existingAddress.setNeighborhood(address.getNeighborhood());
			existingAddress.setState(address.getState());
			existingAddress.setStreetAndNumber(address.getStreetAndNumber());
			existingAddress.setTown(address.getTown());
			
			Address updatedAddress = addressRepository.save(existingAddress);
			return updatedAddress;
		}
		
		@Override
		public void deleteById(Long id) {
			Address existingAddress = findById(id);
			addressRepository.delete(existingAddress);
			
		}

		@Override
		public Set<String> getUsersWithSpecificAddressId(Long id) {
			return null;
		}
}
