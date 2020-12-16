package com.app.sales.core.business.customer;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.sales.core.IStrategy;
import com.app.sales.core.application.Result;
import com.app.sales.core.dao.Impl.CustomerDAO;
import com.app.sales.domain.Customer;

@Component
public class ValidCustomer implements IStrategy<Customer>{
	
	@Autowired
	private  CustomerDAO customerDAO ;

	
	@Override
	public Result<Customer> process(Customer aEntity, Result<Customer> response) {
     
	        
		Optional<Customer> clienteOptional = Optional.ofNullable(aEntity);
        if(!clienteOptional.isPresent()) {
        	response.setMsg( "Não é possível salvar um objeto nulo");
        	 return response;
        }
        
               
        if (clienteOptional.get().getName() == null || clienteOptional.get().getName().isEmpty()) {
        	response.setMsg( "O nome é obrigatório ");
        	return response;
        }
            
        if(clienteOptional.get().getCpf() == null || clienteOptional.get().getCpf().length() < 14){
			response.setMsg("CPF deve conter 14 digitos!");
			return response;
		}else{
		   response.setMsg("CPF não pode ser válidado, pois entidade não é um cliente!");
	    }
		 
	       
	        if (aEntity != null && aEntity.getId() != null) {
				
	        	customerDAO.update(aEntity);
					response.add(aEntity);
				    return response;
		
	        } else {
	        	customerDAO.save(aEntity);
	        	    response.add(aEntity);
				    return response;
			}
	       
		}

}


