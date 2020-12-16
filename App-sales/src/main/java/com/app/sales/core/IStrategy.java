package com.app.sales.core;

import com.app.sales.core.application.Result;

public interface IStrategy<T> {

	public Result<T> process(T aEntity, Result<T> response);

}
