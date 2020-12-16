package com.app.vendas.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.vendas.domain.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    
   
}
