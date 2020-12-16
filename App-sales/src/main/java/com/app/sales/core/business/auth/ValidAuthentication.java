package com.app.sales.core.business.auth;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.sales.core.IStrategy;
import com.app.sales.core.application.Result;
import com.app.sales.domain.User;
import com.app.sales.service.UserServiceImpl;

@Component
public class ValidAuthentication implements IStrategy<User>{
	
	
	@Autowired
	private PasswordEncoder passwordeEncoder;
	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public Result<User> process(User aEntity, Result<User> response) {
     
	        UserDetails user = userService.loadUserByUsername(aEntity.getUsername());
	        boolean isPasswordsMatch = passwordeEncoder.matches(aEntity.getPassword(), user.getPassword());
	        
	        User userS = new User();
	        	        
	        if(isPasswordsMatch) {
	        	userS.setPassword(user.getPassword());
	        	userS.setUsername(user.getUsername());
	        	response.add(userS);
	        	return response;
	        }
		        response.setMsg("Usuário ou senha inválida");
		        return response;
        }
	

}


