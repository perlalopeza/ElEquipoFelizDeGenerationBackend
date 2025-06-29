package com.hta.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street_and_number", columnDefinition = "TEXT", nullable = false)
    private String streetAndNumber;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String neighborhood;

    @Column(name = "zip_code", nullable = false)
    private Long zipCode;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String town;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String state;
    
    @ManyToOne 
	@JoinColumn(name="users_id", nullable=false) 
    private User user;
    
    
    public Address() {
    	
    }


	public Address(Long id, String streetAndNumber, String neighborhood, Long zipCode, String town, String state,
			User user) {
		super();
		this.id = id;
		this.streetAndNumber = streetAndNumber;
		this.neighborhood = neighborhood;
		this.zipCode = zipCode;
		this.town = town;
		this.state = state;
		this.user = user;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStreetAndNumber() {
		return streetAndNumber;
	}


	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}


	public String getNeighborhood() {
		return neighborhood;
	}


	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}


	public Long getZipCode() {
		return zipCode;
	}


	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}


	public String getTown() {
		return town;
	}


	public void setTown(String town) {
		this.town = town;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [id=");
		builder.append(id);
		builder.append(", streetAndNumber=");
		builder.append(streetAndNumber);
		builder.append(", neighborhood=");
		builder.append(neighborhood);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append(", town=");
		builder.append(town);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}

	
	

  
}