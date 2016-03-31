package com.vz.ts.vt.lighting.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vz.ts.vt.lighting.dto.UserRegistrationDto;
import com.vz.ts.vt.lighting.entity.User;
import com.vz.ts.vt.lighting.service.UserService;

@RestController
@RequestMapping(value = "/lt", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();		
		if(userService == null)
			throw new IllegalArgumentException("LoginService cannot be null");
		this.userService = userService;
	}
	
	// 1. POST to create a new USER
	// http://localhost:8097/lighting/register
	@RequestMapping(value="/user/register", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody UserRegistrationDto userDto){		
		User user = this.userService.save(userDto);
		
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		responseHeaders.setLocation(newPollUri);
			
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	// 2. PUT to update an existing User.
	@RequestMapping(value="/user/{userId}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@Valid @RequestBody UserRegistrationDto userDto, @PathVariable Long userId) {		
		this.userService.update(userDto, userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 4. GET to find a User by id
	// http://localhost:8097/lighting/registeredUsers/1
	@RequestMapping(value="user/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable Long userId) {
		User user = this.userService.findById(userId);		
		return new ResponseEntity<> (user, HttpStatus.OK);
	}


}
