package com.hta.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")

public class Category {
	
	// referencia a atributos en X tabla
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //este dato es AutoIncremental
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	
	public Category () {
		
	}

	// inicializar
	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	// agarra lo que tiene el dato, sin tocar el dato
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	// convierte en STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id= ");
		builder.append(id);
		builder.append(", name= ");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	

}
