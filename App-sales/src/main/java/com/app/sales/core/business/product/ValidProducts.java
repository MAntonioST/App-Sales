package com.app.sales.core.business.product;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.sales.core.IStrategy;
import com.app.sales.core.application.Result;
import com.app.sales.core.dao.Impl.ProductDAO;
import com.app.sales.domain.Product;

@Component
public class ValidProducts implements IStrategy<Product>{
	
	@Autowired
	private  ProductDAO productDAO ;

	@Override
	public Result<Product> process(Product aEntity, Result<Product> response) {

	              
	        
	        Optional<Product> productOptional = Optional.ofNullable(aEntity);
	        if(!productOptional.isPresent()) {
	        	response.setMsg( "Não é possível salvar um objeto nulo");
	        	 return response;
	        }
	        
	               
	        if (productOptional.get().getDescription() == null || productOptional.get().getDescription().isEmpty()) {
	        	response.setMsg( "A descrição do produto é obrigatória");
	        	return response;
	        }
	            
	        if(productOptional.get().getPrice() == null
					|| productOptional.get().getPrice().compareTo(BigDecimal.ZERO) < 1){
				response.setMsg("O preco do produto é obrigatório!");
				return response;
	        }
	       
	        if (aEntity != null && aEntity.getId() != null) {
				
	        	productDAO.update(aEntity);
					response.add(aEntity);
				    return response;
		
	        } else {
	        	productDAO.save(aEntity);
	        	    response.add(aEntity);
				    return response;
			}
	        
	        	       
		}

}


