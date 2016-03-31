package com.vz.ts.vt.lighting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vz.ts.vt.lighting.dto.AuthenticatedUser;
import com.vz.ts.vt.lighting.dto.Credentials;
import com.vz.ts.vt.lighting.service.LoginService;

@RestController
@RequestMapping(value = "/lt", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		super();		
		if(loginService == null)
			throw new IllegalArgumentException("LoginService cannot be null");
		this.loginService = loginService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticatedUser> login(@RequestBody Credentials credentials) {
		AuthenticatedUser authUser = this.loginService.authenticateUser(credentials);
		if(authUser!=null){
			return new ResponseEntity<>(authUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

}
