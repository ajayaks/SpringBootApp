package com.vz.ts.vt.lighting.service;

import javax.servlet.http.HttpServletRequest;

import com.vz.ts.vt.lighting.dto.AuthenticatedUser;
import com.vz.ts.vt.lighting.dto.Credentials;

public interface LoginService {
	
	AuthenticatedUser authenticateUser(Credentials credentials);
	
	AuthenticatedUser validateAuthToken(HttpServletRequest request);
	
	AuthenticatedUser refershAuthToken(HttpServletRequest request);
	
	boolean logout();

}
