package com.hta.app.service.Impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hta.app.model.Privilege;
import com.hta.app.repository.PrivilegeRepository;
import com.hta.app.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService{
	
	private final PrivilegeRepository privilegeRepository;

	public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
		super();
		this.privilegeRepository = privilegeRepository;
	}
	
	@Override
	public Iterable<Privilege> findAll() {
		return privilegeRepository.findAll();
	}
	
	@Override
	public Privilege findById(Long id) {
		Optional<Privilege> privilegeOpt = privilegeRepository.findById(id);
		if(privilegeOpt.isEmpty()) { 
			throw new IllegalStateException("Privilege with id: "+ id +" does not exist");
		}
		Privilege existingPrivilege = privilegeOpt.get();
		return existingPrivilege;
	}
	
	@Override
	public Privilege save(Privilege privilege) {
		privilege.setId(null); 
		Privilege newPrivilege = privilegeRepository.save(privilege);
		return newPrivilege;
	}
	
	@Override
	public Privilege update(Long id, Privilege privilege) {
		Privilege existingPrivilege = findById(id);
		existingPrivilege.setPrivilege(privilege.getPrivilege());
		Privilege updatedPrivilege = privilegeRepository.save(existingPrivilege);
		return updatedPrivilege;
	}
	
	@Override
	public void deleteById(Long id) {
        Privilege existingPrivilege = findById(id);
        privilegeRepository.delete(existingPrivilege);   
	}
	
	@Override
    public Set<String> getUsersWithSpecificPrivilegeId(Long id) {
       return null;
    }
		
	

}
