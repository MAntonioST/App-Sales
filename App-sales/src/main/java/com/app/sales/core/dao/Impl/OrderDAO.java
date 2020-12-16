package com.app.sales.core.dao.Impl;




import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.sales.core.repository.OrderRepository;
import com.app.sales.domain.Order;

@Repository("OrderDAO")
@Transactional
public class OrderDAO extends AbstractDAO<Order, Integer> {
	

	@Autowired
    public OrderRepository repository;

    public Optional<List<Order>> findByCustomer(Order order){ 	
    	return repository.findByCustomer(order);
    }

	public Optional<Order> findById(Integer id) {
		
		return repository.findById(id);
	}
	
    public Optional<Order> findByFetchItens(Integer id) {
		
		return repository.findByIdFetchItems(id);
	}

}
