package com.vz.ts.vt.lighting.service;

import com.vz.ts.vt.lighting.dto.UserRegistrationDto;
import com.vz.ts.vt.lighting.entity.User;

public interface UserService {
	
	public User findById(Long id);
	
	public User save(UserRegistrationDto userDto);
	
	public User update(UserRegistrationDto userDto, Long userId);
	
	public boolean delete(Long id);
	
	public User findByEmail(String email);
	
	public User findByAuthToken(String token);
	
	public int updateAuthToken(String authToken, Long Id);
	
}
