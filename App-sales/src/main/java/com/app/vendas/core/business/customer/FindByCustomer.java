package com.app.vendas.core.business.customer;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.vendas.core.IStrategy;
import com.app.vendas.core.application.Result;
import com.app.vendas.core.dao.Impl.CustomerDAO;
import com.app.vendas.domain.Customer;

@Component
public class FindByCustomer implements IStrategy<Customer>{
	
	@Autowired
	private  CustomerDAO customerDAO ;

	@Override
	public Result<Customer> process(Customer aEntity, Result<Customer> response) {
     
		Optional<Customer> customerOptional = Optional.ofNullable(aEntity);
		if (!customerOptional.isPresent()) {
			response.setMsg("Digite uma busca válida");
			return response;
		}          
	               
		Optional<Customer> userOptional = Optional.ofNullable(aEntity);
        if(userOptional.isPresent() && userOptional.get().getId() != null) {
			Optional<Customer> usuarioId = customerDAO.findById(aEntity.getId());
			if(usuarioId.isPresent()) {
				response.add(usuarioId.get());
				return response;
			}else {
				response.setMsg("Não foi encontrado cadastrado desse cliente");
				return response;
			}
        }
		return null;
      
    }

}


