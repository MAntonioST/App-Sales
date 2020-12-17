package com.app.sales.core.business.order;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.sales.core.IStrategy;
import com.app.sales.core.application.Result;
import com.app.sales.core.dao.Impl.CustomerDAO;
import com.app.sales.core.dao.Impl.OrderDAO;
import com.app.sales.core.dao.Impl.OrderItemDAO;
import com.app.sales.domain.Customer;
import com.app.sales.domain.Order;
import com.app.sales.domain.OrderItem;
import com.app.sales.domain.enums.OrderStatus;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class ValidOrder implements IStrategy<Order>{
	

	private final OrderDAO orderDAO ;
	private final  CustomerDAO customerDAO ;
	private final OrderItemDAO orderItemDAO ;
  
	@Transactional
	@Override
	public Result<Order> process(Order aEntity, Result<Order> response) {

		Optional<Order> orderOptional = Optional.ofNullable(aEntity);
	        if(!orderOptional.isPresent()) {
	        	response.setMsg( "Não é possível salvar um pedido vazio");
	        	 return response;
	        }
	        //buscar o cliente do pedido
	        Customer idCustomer =  orderOptional.get().getCustomer();
	        Optional<Customer> customerOptional = customerDAO.findById(idCustomer.getId());
	        if(!customerOptional.isPresent()) {
	        	response.setMsg( "Código de cliente inválido");
	        	return response;
	        }
	        Customer customer = Customer
	        		.builder()
	        		.name(customerOptional.get().getName())
	        		.cpf(customerOptional.get().getCpf())
	        		.id(orderOptional.get().getCustomer().getId())
	                .build();
	        
	        Order order = Order
	        		.builder()
	        		.total(orderOptional.get().getTotal())
	        		.OrderDate(LocalDate.now())
	        		.customer(customer)
	        		.status(OrderStatus.REALIZADO)
	        		.build();
	                
	      	        
	        if(!isValid(orderOptional.get().getItems())){           	
	        	response.setMsg( "Não é possível realizar um pedido sem items.");
		        return response;	         
	        }    
	        
	        List<OrderItem> listItens = new ArrayList<OrderItem>();
	        
		    for(OrderItem orderList : orderOptional.get().getItems() ) {			           	
	            OrderItem orderItem = new OrderItem();
		        orderItem.setQuantity(orderList.getQuantity());
		        orderItem.setProduct(orderList.getProduct());
		        orderItem.setOrder(order);
		        listItens.add(orderItem);		           		            
	         }
	                          	           
	        order.setItems(listItens);	 
	        
            orderDAO.save(order);
		    orderItemDAO.saveAllOrderItens(listItens);		      
	        response.add(order);
	        return response;
	       
		}
	
	public boolean isValid(List<OrderItem> list) {
		return list != null && !list.isEmpty();
	}
}


