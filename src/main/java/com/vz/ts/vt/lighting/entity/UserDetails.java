package com.vz.ts.vt.lighting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "T_USER_DETAIL")
public class UserDetails implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "zipcode")
	private int zipcode;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "phone")
	private String phone;
	

	public UserDetails() {

	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city
				+ ", state=" + state + ", zipcode=" + zipcode + ", country=" + country + ", phone=" + phone + "]";
	}
	
	
	
}
