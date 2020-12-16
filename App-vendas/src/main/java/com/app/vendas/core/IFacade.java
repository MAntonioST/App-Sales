package com.app.vendas.core;

import com.app.vendas.core.application.Result;
import com.app.vendas.domain.DomainEntity;
import com.app.vendas.domain.IEntity;

public interface IFacade <T extends DomainEntity, R extends Object> extends IEntity {
	
	public Result<T> save(T aEntity);
	public Result<T> update(T aEntity);
	public Result<T> delete(T aEntity);
	public Result<T> findAll(T aEntity);
	public Result<T> find(T aEntity);

}
