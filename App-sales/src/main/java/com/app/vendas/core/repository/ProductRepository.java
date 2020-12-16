package com.app.vendas.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.app.vendas.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

   
}
