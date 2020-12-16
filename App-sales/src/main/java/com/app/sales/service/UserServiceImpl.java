package com.app.sales.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.sales.core.dao.Impl.UserDAO;
import com.app.sales.domain.User;






@Service
public class UserServiceImpl implements UserDetailsService {


	
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 User user = userDAO
	                .findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

	        String[] roles = user.isAdmin() ?
	        		new String[]{"ADMIN", "USER"} : new String[]{"USER"};

	        return org.springframework.security.core.userdetails.User
	                .builder()
	                .username(user.getUsername())
	                .password(user.getPassword())
	                .roles(roles)
	                .build();
	}

	
}
