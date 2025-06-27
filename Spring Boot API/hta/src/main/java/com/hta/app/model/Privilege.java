package com.hta.app.model;

import jakarta.persistence.*;

@Entity
@Table(name="privileges")
public class Privilege {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="privilege", length=20, nullable=false)
	private String privilege;
	
	public Privilege() {
		
	}

	public Privilege(Long id, String privilege) {
		super();
		this.id = id;
		this.privilege = privilege;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "Privilege [id= " + id + ", privilege= " + privilege + "]";
	}
	
	
	
	

}
