package com.app.sales.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sales.domain.OrderItem;



@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
  

   
}
