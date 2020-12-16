package com.app.sales.domain;



import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DomainEntity implements IEntity, Serializable  {

	
	private static final long serialVersionUID = 1L;

}
