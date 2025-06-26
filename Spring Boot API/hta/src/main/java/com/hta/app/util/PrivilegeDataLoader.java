package com.hta.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hta.app.model.Privilege;
import com.hta.app.repository.PrivilegeRepository;

@Component
@Order(2)
public class PrivilegeDataLoader implements CommandLineRunner{

	@Autowired
	PrivilegeRepository privilegeRepository;

	@Override
	public void run(String... args) throws Exception {
		privilegeRepository.save( new Privilege(null, "Administrador"));
		privilegeRepository.save( new Privilege(null, "Colaborador"));
		privilegeRepository.save( new Privilege(null, "Cliente"));
	}
	
	
	
	
	
	
	

}
