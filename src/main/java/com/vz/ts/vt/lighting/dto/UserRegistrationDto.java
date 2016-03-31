package com.vz.ts.vt.lighting.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRegistrationDto implements Serializable{
	
	private static final long serialVersionUID = 1568899600808865186L;
	
	private String firstName;
	
	private String lastName;
	
	private String userId;

	private String email;
	
	private String password;
	
}
