package com.app.sales.core.dao.Impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.sales.core.repository.UserRepository;
import com.app.sales.domain.User;

@Repository("UserDAO")
@Transactional
public class UserDAO extends AbstractDAO<User, Integer> {

	@Autowired
	private UserRepository repository;

//   @Transactional(readOnly = true)
//   public List<UserAuth> findFiltro(UserAuth filtro){
//	   ExampleMatcher matcher = ExampleMatcher
//								.matching()
//								.withIgnoreCase()
//								.withStringMatcher(
//				                   ExampleMatcher.StringMatcher.CONTAINING);
//      Example<Customer> example = Example.of(filtro, matcher);
//
//      return repository.findAll(example);
//   }

	public Optional<User> findById(Integer id) {
		return repository.findById(id);
	}

	public Optional<User> findByUsername(String username) {
		return repository.findByUsername(username);
	}

}
