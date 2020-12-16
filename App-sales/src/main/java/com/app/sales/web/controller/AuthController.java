package com.app.sales.web.controller;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.sales.config.security.jwt.JwtService;
import com.app.sales.core.dto.CredentialDTO;
import com.app.sales.core.dto.TokenDTO;
import com.app.sales.domain.User;





@RestController
@RequestMapping("/api/usuarios")
public class AuthController extends BaseController<User, String>{
	
	
	public AuthController() {
		super(User.class);
	}
	
  @Autowired
  private JwtService jwtService;

	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseEntity<?> save(@RequestBody User aEntity) {
		   
		    result = this.appFacade.save(aEntity);
			Optional<User> entity = Optional.ofNullable(result.getEntity());
			if (entity.isPresent() && Stream.of(entity.get()).count() > 0)
				return ResponseEntity.ok(entity.get());
			else if(result.hasMsg())
				return ResponseEntity.badRequest().body(result.getMsg());
			else
				return ResponseEntity.notFound().build();

	}
	
	
	@PostMapping("auth")
	public @ResponseBody ResponseEntity<?> authentication(@RequestBody CredentialDTO credential) {
		 
		User user = User
		      .builder()
		      .username(credential.getUsername())
		      .password(credential.getPassword())
		      .build();
		result = this.appFacade.find(user);  
        
		Optional<?> entity = Optional.ofNullable(result.getEntity());
		if (entity.isPresent() && Stream.of(entity.get()).count() > 0) {			
			 String token = jwtService.generateToken(user);
			return ResponseEntity.ok(new TokenDTO(user.getUsername(), token));		
		}else if(result.hasMsg()) {
			return ResponseEntity.badRequest().body(result.getMsg());
		}else
			return ResponseEntity.notFound().build();
	

	}
	
	@PostMapping("findfiltro")
	public @ResponseBody ResponseEntity<?> find(User filtro) {
		 
		result = this.appFacade.findAll(filtro);
		if (result.hasMsg())
			return ResponseEntity.badRequest().body(result.getMsg());
		if (result.hasEntities()) {
			return ResponseEntity.ok().body(result.getEntities());
		} else if (result.hasEntity()) {
			return ResponseEntity.ok().body(result.getEntity());
		} else
			return ResponseEntity.notFound().build();
		
		
	}

	
}