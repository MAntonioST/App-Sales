package com.app.sales.web.controller;





import org.springframework.beans.factory.annotation.Autowired;

import com.app.sales.core.IFacade;
import com.app.sales.core.application.Result;
import com.app.sales.domain.DomainEntity;



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

