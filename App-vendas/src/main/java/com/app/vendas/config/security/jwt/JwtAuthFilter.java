package com.app.vendas.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.vendas.service.UserServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter{

	private JwtService jwtService;
	private UserServiceImpl userService;
	
	
	public JwtAuthFilter(JwtService jwtService, UserServiceImpl userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}


	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		
		if( authorization != null && authorization.startsWith("Bearer") ) {
			String token = authorization.split(" ")[1];
			boolean isValid = jwtService.isValidateToken(token);
			
			if(isValid) {
				String usernameUser = jwtService.getUsernameUser(token);
				UserDetails username = userService.loadUserByUsername(usernameUser);
				UsernamePasswordAuthenticationToken user = new
						UsernamePasswordAuthenticationToken(username,null,
								username.getAuthorities());
				user.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
