package com.app.vendas.core;

import com.app.vendas.core.application.Result;

public interface IStrategy<T> {

	public Result<T> process(T aEntity, Result<T> response);

}
