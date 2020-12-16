package com.app.vendas.web.controller;





import org.springframework.beans.factory.annotation.Autowired;
import com.app.vendas.core.IFacade;
import com.app.vendas.core.application.Result;
import com.app.vendas.domain.DomainEntity;



public abstract class BaseController <T extends DomainEntity, ID extends Object> {

	@Autowired
	protected IFacade<T, ID> appFacade;

	@Autowired
	protected Result<T> result;
	
	protected Class<? extends T> clazz;

	public BaseController(Class<? extends T> clazz) {
		this.clazz = clazz;
	}
	

}

