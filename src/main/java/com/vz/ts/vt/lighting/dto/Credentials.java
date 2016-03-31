package com.vz.ts.vt.lighting.dto;

import java.io.Serializable;

public class Credentials implements Serializable{

	private static final long serialVersionUID = 3520084004631435496L;
	
	private String email;
	
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
