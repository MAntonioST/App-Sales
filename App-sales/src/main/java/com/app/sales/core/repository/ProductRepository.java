package com.app.sales.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.sales.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

   
}
