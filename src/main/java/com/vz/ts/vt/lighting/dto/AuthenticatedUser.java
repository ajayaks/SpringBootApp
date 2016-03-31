package com.vz.ts.vt.lighting.dto;

import java.io.Serializable;

public class AuthenticatedUser implements Serializable{
	
	private static final long serialVersionUID = -4320983208274810426L;
	
	private String userId;
	
	private String email;
	
	private String clientId;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	private String authHeaderName;
	
	private String authToken;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthHeaderName() {
		return authHeaderName;
	}

	public void setAuthHeaderName(String authHeaderName) {
		this.authHeaderName = authHeaderName;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Override
	public String toString() {
		return "AuthenticatedUser [userId=" + userId + ", email=" + email + ", clientId=" + clientId
				+ ", authHeaderName=" + authHeaderName + ", authToken=" + authToken + "]";
	}	
	
}


