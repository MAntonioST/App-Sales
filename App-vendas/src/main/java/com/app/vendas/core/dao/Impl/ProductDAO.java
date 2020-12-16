package com.app.vendas.core.dao.Impl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.app.vendas.core.repository.ProductRepository;
import com.app.vendas.domain.Product;

@Repository("ProductDAO")
@Transactional
public class ProductDAO extends AbstractDAO<Product, Integer> {

	@Autowired
	public ProductRepository repository;

	@Transactional(readOnly = true)
	public List<Product> findFiltro(Product filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Product> example = Example.of(filtro, matcher);

		return repository.findAll(example);
	}

	public Optional<Product> findById(Integer id) {

		return repository.findById(id);
	}

}
