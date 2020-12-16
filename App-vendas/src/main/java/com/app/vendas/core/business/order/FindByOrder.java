package com.app.vendas.core.business.order;




import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.vendas.core.IStrategy;
import com.app.vendas.core.application.Result;
import com.app.vendas.core.dao.Impl.OrderDAO;
import com.app.vendas.domain.Order;



@Component
public class FindByOrder implements IStrategy<Order>{
	
	@Autowired
	private  OrderDAO orderDAO ;

	@Override
	public Result<Order> process(Order aEntity, Result<Order> response) {
     
		Optional<Order> orderOptional = Optional.ofNullable(aEntity);
		if (!orderOptional.isPresent()) {
			response.setMsg("Digite uma pedido válido");
			return response;
		}          
	               
		
        if(orderOptional.get().getId() != null) {
			Optional<Order> orderItens = orderDAO.findByFetchItens(aEntity.getId());
			if(orderItens.isPresent()) {				
				response.add(orderItens.get());
				return response;
			}else {
				response.setMsg("Não foi encontrado cadastrado desse Produto");
				return response;
			}
        }
		return null;
      
    }
	

}


