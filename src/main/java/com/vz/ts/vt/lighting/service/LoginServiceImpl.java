package com.vz.ts.vt.lighting.service;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vz.ts.vt.lighting.dto.AuthenticatedUser;
import com.vz.ts.vt.lighting.dto.Credentials;
import com.vz.ts.vt.lighting.dto.InputForToken;
import com.vz.ts.vt.lighting.entity.User;
import com.vz.ts.vt.lighting.util.TokenHandler;

@Service
public class LoginServiceImpl implements LoginService{
	
	private UserService userService;
	
	private final TokenHandler tokenHandler;
	
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	
	private static final String SECRET = "9SyECk96oDsTmXfogIieDI0cD/8FpnojlYSUJT5U9I/FGVmBz5oskmjOR8cbXTvoPjX+Pq/T/b1PqpHX0lYm0oCBjXWICA==";
	
	@Autowired
	public LoginServiceImpl(UserService userService) {
		super();
		if (userService == null) {
            throw new IllegalArgumentException("User Service cannot be null");
        }
		this.userService = userService;
		this.tokenHandler = new TokenHandler(SECRET.getBytes());
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false)
	public AuthenticatedUser authenticateUser(Credentials credentials){
		User dbuser = userService.findByEmail(credentials.getEmail());
		if(dbuser!=null && dbuser.getPassword().equals(credentials.getPassword())){
			AuthenticatedUser authUser = new AuthenticatedUser();
			authUser.setUserId(dbuser.getUserId());
			authUser.setEmail(dbuser.getEmail());
			authUser.setClientId(dbuser.getClientInfo().getClientId());
			authUser.setAuthToken(this.tokenHandler.createTokenForUser(new InputForToken(authUser.getUserId(), new Date())));
			authUser.setAuthHeaderName(AUTH_HEADER_NAME);
			//Save token into database
			this.userService.updateAuthToken(authUser.getAuthToken(), dbuser.getId());
			return authUser;
		}
		return null;
	}

	@Override
	public AuthenticatedUser validateAuthToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticatedUser refershAuthToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
