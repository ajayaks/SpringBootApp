package com.vz.ts.vt.lighting.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vz.ts.vt.lighting.dto.UserRegistrationDto;
import com.vz.ts.vt.lighting.entity.ClientInfo;
import com.vz.ts.vt.lighting.entity.Role;
import com.vz.ts.vt.lighting.entity.User;
import com.vz.ts.vt.lighting.entity.UserDetails;
import com.vz.ts.vt.lighting.repository.UserDetailsRepository;
import com.vz.ts.vt.lighting.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	 
	private UserRepository userRepository;
	
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserDetailsRepository userDetailsRepository) {
		super();
		if (userRepository == null) {
            throw new IllegalArgumentException("User Repository cannot be null");
        }else if(userDetailsRepository == null){
        	throw new IllegalArgumentException("User DetailsRepository cannot be null");
        }
		this.userRepository = userRepository;
		this.userDetailsRepository = userDetailsRepository;
	}

	@Override
	public User findById(Long id) {
		try {
			User domainUser = userRepository.findById(id);
			return domainUser;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false)
	public User save(UserRegistrationDto userDto){
		try {
			// Create UserDetails
			UserDetails userDetails = new UserDetails();
			userDetails.setFirstName(userDto.getFirstName());
			userDetails.setLastName(userDto.getLastName());
			// Save UserDetails into Database first
			UserDetails userDetailsDB = userDetailsRepository.save(userDetails);
			// Create User
			User user = new User();
			user.setCreatedDate(new Date());
			user.setClientInfo(new ClientInfo(1l)); // Default Client Info for Demo
			user.setEmail(userDto.getEmail());
			user.setUserId(userDto.getUserId());
			user.setPassword(userDto.getPassword());
			user.setRoles(new Role(1L));	// Give Admin Role for Demo
			user.setUserDetails(userDetailsDB);
			
			User userFromDb = userRepository.save(user);
			return userFromDb;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int updateAuthToken(String authToken, Long userId) {
		return this.userRepository.updateAuthToken(authToken, userId);
	}

	@Override
	public User update(UserRegistrationDto userDto, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByAuthToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
