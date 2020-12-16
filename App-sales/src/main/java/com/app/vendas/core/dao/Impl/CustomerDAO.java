package com.app.vendas.core.dao.Impl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.app.vendas.core.repository.CustomerRepository;
import com.app.vendas.domain.Customer;

@Repository("CustomerDAO")
@Transactional

public class CustomerDAO extends AbstractDAO<Customer, Integer> {

	@Autowired
	private CustomerRepository repository;

	@Transactional(readOnly = true)
	public List<Customer> findFiltro(Customer filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Customer> example = Example.of(filtro, matcher);

		return repository.findAll(example);
	}

	public Optional<Customer> findById(Integer id) {

		return repository.findById(id);
	}

	

}
