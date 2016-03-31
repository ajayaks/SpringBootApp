package com.vz.ts.vt.lighting.dto;

import java.io.Serializable;
import java.util.Date;

public class InputForToken implements Serializable{

	private static final long serialVersionUID = 3520084004631435496L;
	
	private String username;		
	
	private Date currentTime;

	public InputForToken(String username, Date currentTime) {
		super();
		this.username = username;
		this.currentTime = currentTime;
	}

	public String getUsername() {
		return username;
	}

	public Date getCurrentTime() {
		return currentTime;
	}
}
