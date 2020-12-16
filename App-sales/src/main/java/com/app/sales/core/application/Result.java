package com.app.sales.core.application;




import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;



@Component
public class Result<T> extends ApplicationEntity {

	private String msg;
	private List<T> entities = new ArrayList<T>();
	private T entity;

	
	
	public Result() {
	
	}

	public Result(String msg, List<T> entities, T entity) {
		super();
		this.msg = msg;
		this.entities = entities;
		this.entity = entity;
	}
	
	
	public Result(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
		

	public List<T> getEntities() {
		return entities;
	}

	public void addEntities(List<T> entities) {
		this.entities = entities;
	}

	public T getEntity() {
		return entity;
	}

		
	public void add(T entity) {
	    this.entity = entity;
	  }

	public boolean hasMsg() {
		return !(msg == null) && !(msg.isEmpty());
	}
	
	public boolean hasEntities() {
		return !(entities == null) && !(entities.isEmpty());
	}
	
	public boolean hasEntity() {
		return !(entity == null) && !(entity.toString().isEmpty());
	}

}
