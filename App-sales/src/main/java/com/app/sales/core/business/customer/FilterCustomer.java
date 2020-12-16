package com.app.sales.core.business.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.sales.core.IStrategy;
import com.app.sales.core.application.Result;
import com.app.sales.core.dao.Impl.CustomerDAO;
import com.app.sales.domain.Customer;

@Component
public class FilterCustomer implements IStrategy<Customer>{
	
	@Autowired
	private  CustomerDAO customerDAO ;

	@Override
	public Result<Customer> process(Customer aEntity, Result<Customer> response) {
               
		  
    		Optional<Customer> customerOptional = Optional.ofNullable(aEntity);
    		if (!customerOptional.isPresent()) {
    			response.setMsg("Digite a busca");
    			return response;
    		}	
  		 
    		List<Customer> filtro = customerDAO.findFiltro(aEntity);
    		Optional<List<Customer>> filtroOptional = Optional.ofNullable(filtro);
    		if(filtroOptional.isPresent()) {
    			response.addEntities(filtroOptional.get());
    	        return response;
    		}else {
    			response.setMsg("Não foi possível encontrar Clientes, tente novamente");
    			return response;
    		}
    	}
}


