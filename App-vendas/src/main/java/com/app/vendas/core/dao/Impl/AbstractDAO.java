package com.app.vendas.core.dao.Impl;





import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.vendas.core.IDAO;
import com.app.vendas.domain.DomainEntity;




@Service
@Transactional
public abstract class AbstractDAO<T extends DomainEntity, R extends Object> implements IDAO<T, R> {
	
	final static Logger LOG = LoggerFactory.getLogger(AbstractDAO.class);
	
	
	private JpaRepository<T, R> repository;
	


	@Override
	public Optional<T> findById(R id) {
		return getRepository().findById(id);
	}
	
		
	@Override
	public Optional<List<T>> findAll() {
		List<T> listEntity = getRepository().findAll();
		return Optional.ofNullable(listEntity);
	}
	
	@Override
	public Optional<T> save(T aEntity) {
		aEntity = getRepository().save(aEntity);
		return Optional.of(aEntity);
	}
	
	@Override
	public Optional<T> update(T aEntity) {
		return save(aEntity);
	}

	@Override
	public Optional<T> delete(T aEntity) {
		getRepository().delete(aEntity);
		return Optional.of(aEntity);
	}

	public JpaRepository<T, R> getRepository() {
		return repository;
		
	}
}