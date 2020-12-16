package com.app.vendas.core.business.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.vendas.core.IStrategy;
import com.app.vendas.core.application.Result;
import com.app.vendas.core.dao.Impl.ProductDAO;
import com.app.vendas.domain.Product;

@Component
public class FilterProduct implements IStrategy<Product>{
	
	@Autowired
	private  ProductDAO productDAO ;

	@Override
	public Result<Product> process(Product aEntity, Result<Product> response) {
               
		  
    		Optional<Product> productOptional = Optional.ofNullable(aEntity);
    		if (!productOptional.isPresent()) {
    			response.setMsg("Digite uma busca válida");
    			return response;
    		}	
  		 
    		List<Product> filtro = productDAO.findFiltro(aEntity);
    		Optional<List<Product>> filtroOptional = Optional.ofNullable(filtro);
    		if(filtroOptional.isPresent()) {
    			response.addEntities(filtroOptional.get());
    	        return response;
    		}else {
    			response.setMsg("Não foi possível encontrar Produtos, tente novamente");
    			return response;
    		}
    	}
}


