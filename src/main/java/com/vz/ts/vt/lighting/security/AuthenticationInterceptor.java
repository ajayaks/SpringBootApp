package com.vz.ts.vt.lighting.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vz.ts.vt.lighting.service.LoginService;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private final LoginService loginService;
	
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	
	public AuthenticationInterceptor(LoginService loginService) {
		super();
		if (loginService == null) {
            throw new IllegalArgumentException("loginService cannot be null");
		}
		this.loginService = loginService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//TODO: Delegate token validity functionality to Login Service. 
		//TODO: Get the object of Authenticated User.
		// AuthenticatedUser authUser = this.loginService.validateAuthToken(request);
		LOGGER.info("In AuthenticationInterceptor, AUTH_TOKEN is : "+request.getHeader(AUTH_HEADER_NAME));
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {		
		super.afterCompletion(request, response, handler, ex);
		
	}


}
