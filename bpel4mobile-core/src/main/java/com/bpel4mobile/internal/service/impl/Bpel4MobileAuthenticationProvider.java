package com.bpel4mobile.internal.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.bpel4mobile.internal.service.UserDataProvider;
import com.google.common.collect.Lists;

@Component
public class Bpel4MobileAuthenticationProvider implements AuthenticationProvider {
 
	@Autowired
	private UserDataProvider userDataProvider;
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        if (!userDataProvider.authenticate(username, password)) {
            throw new BadCredentialsException("Username not found or wrong password.");
        }
        
         Collection<? extends GrantedAuthority> authorities = Lists.newArrayList();
 
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }
 
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}