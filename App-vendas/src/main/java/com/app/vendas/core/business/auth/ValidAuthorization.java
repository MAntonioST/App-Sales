package com.app.vendas.core.business.auth;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.app.vendas.core.IStrategy;
import com.app.vendas.core.application.Result;
import com.app.vendas.core.dao.Impl.UserDAO;
import com.app.vendas.domain.User;

@Component
public class ValidAuthorization implements IStrategy<User>{
	
	@Autowired
	private  UserDAO userDAO ;

	@Autowired
	private PasswordEncoder passwordeEncoder;
	
	@Override
	public Result<User> process(User aEntity, Result<User> response) {
     
	        
		Optional<User> clienteOptional = Optional.ofNullable(aEntity);
        if(!clienteOptional.isPresent()) {
        	response.setMsg( "Não é possível verificar dados de usuário nulo");
        	 return response;
        }
        
               
        if (clienteOptional.get().getUsername() == null || clienteOptional.get().getUsername().isEmpty()) {
        	response.setMsg( "O login é obrigatório ");
        	return response;
        }
            
        if(clienteOptional.get().getPassword() == null || clienteOptional.get().getPassword().length() < 8){
			response.setMsg("A senha não é válida");
			return response;
        }
		
		 
	       
	        if (aEntity != null && aEntity.getId() != null) {
				
	        	userDAO.update(aEntity);
					response.add(aEntity);
				    return response;
		
	        } else {
	        	String passwordEncrypt = passwordeEncoder.encode(aEntity.getPassword());
	    		aEntity.setPassword(passwordEncrypt);
	        	userDAO.save(aEntity);
	        	response.add(aEntity);
				    return response;
			}
	       
        }

}


