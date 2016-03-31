package com.vz.ts.vt.lighting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.vz.ts.vt.lighting.security.AuthenticationInterceptor;
import com.vz.ts.vt.lighting.service.LoginService;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
	    registry.addInterceptor(new AuthenticationInterceptor(loginService)).excludePathPatterns("/login");
	    //TODO: Add intercepter for Authorization Here.
	    
	}

}
