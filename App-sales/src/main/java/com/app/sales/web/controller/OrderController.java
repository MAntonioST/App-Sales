package com.app.sales.web.controller;



import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.sales.core.dto.OrderDTO;
import com.app.sales.core.dto.OrderItemDTO;
import com.app.sales.core.dto.UpdateOrderStatusDTO;
import com.app.sales.domain.Order;
import com.app.sales.domain.OrderItem;
import com.app.sales.domain.enums.OrderStatus;






@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController<Order, String>{

	
	
	public OrderController() {
		super(Order.class);
	}

	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public  ResponseEntity<?> saveOrder(@RequestBody Order aEntity) {
		   
		   
		    result = this.appFacade.save(aEntity);
			Optional<?> entity = Optional.ofNullable(result.getEntity());
			if (entity.isPresent() && Stream.of(entity.get()).count() > 0)
				return ResponseEntity.ok(entity.get());
			else if(result.hasMsg())
				return ResponseEntity.badRequest().body(result.getMsg());
			else
				return ResponseEntity.noContent().build();

	}
	
	@PostMapping("{id}")
	public  ResponseEntity<?> orderById(@PathVariable Integer id) {
		
		Order aEntity = new Order();
		aEntity.setId(id);
		result = this.appFacade.find(aEntity);		
		OrderDTO order = convertOrder(result.getEntity());		
		Optional<OrderDTO> entity = Optional.ofNullable(order);	
		if (entity.isPresent() && Stream.of(entity.get()).count() > 0)
			return ResponseEntity.ok(entity.get());
		else if(result.hasMsg())
			return ResponseEntity.badRequest().body(result.getMsg());
		else
			return ResponseEntity.noContent().build();
	

	}
	
	@PostMapping("findfiltro")
	public  ResponseEntity<?> findFiltro(Order filtro) {
		
		result = this.appFacade.findAll(filtro);
		if (result.hasMsg())
			return ResponseEntity.badRequest().body(result.getMsg());
		if (result.hasEntities()) {
			return ResponseEntity.ok().body(result.getEntities());
		} else if (result.hasEntity()) {
			return ResponseEntity.ok().body(result.getEntity());
		} else
			return ResponseEntity.noContent().build();
		
		
	}
	
	
	@PatchMapping("{id}")
	public  ResponseEntity<?> updateOrderStatus( @PathVariable("id") Integer id , 
			                                     @RequestBody UpdateOrderStatusDTO  dto) {
		Order order = new Order();
		OrderStatus orderStatus = OrderStatus.valueOf(dto.getNewStatus());
	    order.setId(id);
	    order.setStatus(orderStatus);
	    
	    result = appFacade.update(order);
		if (result.hasMsg())
			return ResponseEntity.badRequest().body(result.getMsg());
		else if (!result.hasMsg()) {
			return ResponseEntity.ok().body(result.getEntity());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	private OrderDTO convertOrder(Order order){
        return OrderDTO
                .builder()
                .code(order.getId())
                .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getCustomer().getCpf())
                .nameCustomer(order.getCustomer().getName())
                .total(order.getTotal())
                .status(order.getStatus().name())
                .items(convertList(order.getItems()))
                .build();
    }

    private List<OrderItemDTO> convertList(List<OrderItem> items){
        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }
        return items.stream().map(
                item -> OrderItemDTO
                            .builder().productDescription(item.getProduct().getDescription())
                            .unitPrice(item.getProduct().getPrice())
                            .quantity(item.getQuantity())
                            .build()
        ).collect(Collectors.toList());
    }

	
}
