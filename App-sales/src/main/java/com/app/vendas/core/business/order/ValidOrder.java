package com.app.vendas.core.business.order;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.app.vendas.core.IStrategy;
import com.app.vendas.core.application.Result;
import com.app.vendas.core.dao.Impl.CustomerDAO;
import com.app.vendas.core.dao.Impl.OrderDAO;
import com.app.vendas.core.dao.Impl.OrderItemDAO;
import com.app.vendas.domain.Customer;
import com.app.vendas.domain.Order;
import com.app.vendas.domain.OrderItem;
import com.app.vendas.domain.enums.OrderStatus;

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
	        Customer customer = new Customer();
	        customer.setName(customerOptional.get().getName());
	        customer.setCpf(customerOptional.get().getCpf());
	        customer.setId(orderOptional.get().getCustomer().getId());
	        
	        Order order = new Order();
	        order.setTotal(orderOptional.get().getTotal());
	        order.setOrderDate(LocalDate.now());
	        order.setCustomer(customer);    
	        order.setStatus(OrderStatus.REALIZADO);
	        
	      
	        
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


