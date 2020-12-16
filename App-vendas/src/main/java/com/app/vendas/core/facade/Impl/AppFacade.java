package com.app.vendas.core.facade.Impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.vendas.core.IFacade;
import com.app.vendas.core.IStrategy;
import com.app.vendas.core.application.Result;
import com.app.vendas.core.business.auth.ValidAuthentication;
import com.app.vendas.core.business.auth.ValidAuthorization;
import com.app.vendas.core.business.customer.FilterCustomer;
import com.app.vendas.core.business.customer.FindByCustomer;
import com.app.vendas.core.business.customer.ValidCustomer;
import com.app.vendas.core.business.order.FindByOrder;
import com.app.vendas.core.business.order.UpdateOrderStatus;
import com.app.vendas.core.business.order.ValidOrder;
import com.app.vendas.core.business.product.FilterProduct;
import com.app.vendas.core.business.product.FindByProduct;
import com.app.vendas.core.business.product.ValidProducts;
import com.app.vendas.domain.Customer;
import com.app.vendas.domain.DomainEntity;
import com.app.vendas.domain.Order;
import com.app.vendas.domain.Product;
import com.app.vendas.domain.User;




@SuppressWarnings("rawtypes")

@Service
public class AppFacade<T extends DomainEntity, R extends Object> implements IFacade<T, R> {

	private static final String SAVE = "SAVE";
	private static final String UPDATE = "UPDATE";
	private static final String FIND = "FIND";
	private static final String FINDALL = "FINDALL";
	private static final String DELETE = "DELETE";
   
    
	@Autowired
	private ValidCustomer saveCustomer;
	@Autowired
	private FilterCustomer filterCustomer;
	@Autowired
	private FindByCustomer findByCustomer;
   
	@Autowired
	private ValidProducts saveProducts;
	@Autowired
	private FilterProduct filterProducts;
	@Autowired
	private FindByProduct findByProducts;
	
	@Autowired
	private ValidOrder validOrder;
	@Autowired
	private FindByOrder findByOrder;	
	@Autowired
	private UpdateOrderStatus updateOrderStatus;
	
	@Autowired
	private ValidAuthorization validUser;
	@Autowired
	private ValidAuthentication authenticateUser;
	
	
	
	private Map<String, Map<String, List<IStrategy>>> rns;

    private Result<T> result;

   
	@PostConstruct
    public void initMap() {
      
       
        rns = new HashMap<String, Map<String, List<IStrategy>>>();
   
        // --------------------- Hash  Customer ------------------------------//

      
		List<IStrategy> rnsSaveCustomer = new ArrayList<>();
        List<IStrategy> rnsFindByCustomer = new ArrayList<>();
        List<IStrategy> rnsFindFiltro = new ArrayList<>();
       
        rnsSaveCustomer.add(saveCustomer);
       
        
        rnsFindByCustomer.add(findByCustomer);
        rnsFindFiltro.add(filterCustomer);
        
        Map<String, List<IStrategy>> rnsCustomer = new HashMap<>();
        rnsCustomer.put(SAVE, rnsSaveCustomer);
        rnsCustomer.put(FIND, rnsFindByCustomer);
        //rnsCliente.put(UPDATE, rnsAutentCliente);
        rnsCustomer.put(FINDALL, rnsFindFiltro);
       // rnsCliente.put(DELETE, rnsFindAllCliente);
        
        
        // --------------------- Hash  Products ------------------------------//
      
        List<IStrategy> rnsSaveProducts = new ArrayList<>();
        List<IStrategy> rnsFindByProducts = new ArrayList<>();
        List<IStrategy> rnsFindFiltroProduct = new ArrayList<>();
       
        rnsSaveProducts.add(saveProducts);
        rnsFindByProducts.add(findByProducts);
        rnsFindFiltroProduct.add(filterProducts);
        
        Map<String, List<IStrategy>> rnsProducts = new HashMap<>();
        rnsProducts.put(SAVE, rnsSaveProducts);
        rnsProducts.put(FIND, rnsFindByProducts);
        rnsProducts.put(FINDALL, rnsFindFiltroProduct);
    
        
     // --------------------- Hash  Orders ------------------------------//
        
        List<IStrategy> rnsSaveOrders = new ArrayList<>();
        List<IStrategy> rnsFindByOrder = new ArrayList<>();
        List<IStrategy> rnsUpdateOrderStatus = new ArrayList<>();
        
        rnsSaveOrders.add(validOrder);
        rnsFindByOrder.add(findByOrder);
        rnsUpdateOrderStatus.add(updateOrderStatus);
        
        Map<String, List<IStrategy>> rnsOrders = new HashMap<>();
        rnsOrders.put(SAVE, rnsSaveOrders);
        rnsOrders.put(FIND, rnsFindByOrder);
        rnsOrders.put(UPDATE, rnsUpdateOrderStatus);
        
  // --------------------- Hash  Users ------------------------------//
        
        List<IStrategy> rnsSaveUsers = new ArrayList<>();
        List<IStrategy> rnsFindUsers = new ArrayList<>();
        
        
        rnsSaveUsers.add(validUser);
        rnsFindUsers.add(authenticateUser);
        
        Map<String, List<IStrategy>> rnsUsers = new HashMap<>();
        rnsUsers.put(SAVE, rnsSaveUsers);
        rnsUsers.put(FIND, rnsFindUsers);
        
        rns.put(User.class.getName(), rnsUsers);
        rns.put(Customer.class.getName(), rnsCustomer);
        rns.put(Product.class.getName(), rnsProducts);
        rns.put(Order.class.getName(), rnsOrders);
     

    }

	
	@Override
	public  Result<T> save(T aEntity) {
		result = new Result<T>();  
		result = executeRules(aEntity, SAVE);
        if(result.hasMsg()){
            String msg = result.getMsg();
            result.setMsg(msg); 
        }
        return result;
	}

	@Override
	public  Result<T> update(T aEntity) {
		result = new Result<T>();  
		result = executeRules(aEntity, UPDATE);
        if(result.hasMsg()){
            String msg = result.getMsg();
            result.setMsg(msg); 
        }
        return result;
	}

	@Override
	public  Result<T> delete(T aEntity) {
		result = new Result<T>();   
		result = executeRules(aEntity, DELETE);
        if(result.hasMsg()){
            String msg = result.getMsg();
            result.setMsg(msg); 
        }
        return result;
	}

	
	@Override
	public Result<T> findAll(T aEntity) {
		result = new Result<T>();    
		result = executeRules(aEntity, FINDALL);
        if(result.hasMsg()){
            String msg = result.getMsg();
            result.setMsg(msg); 
        }
        return result;
	}

	@Override
	public Result<T> find(T aEntity) {
		result = new Result<T>();  
		result = executeRules(aEntity, FIND);
        if(result.hasMsg()){
            String msg = result.getMsg();
            result.setMsg(msg); 
        }
        return result;
	}
  
	
	@SuppressWarnings("unchecked")
	private Result<T> executeRules(T aEntity, String operation) {
        String nmClass = aEntity.getClass().getName();
        Map<String, List<IStrategy>> rules = rns.get(nmClass);
        if (rules != null) {
            List<IStrategy> typeRules = rules.get(operation);
            if (typeRules != null) {
                for (IStrategy<T> s : typeRules) {
                	result = s.process(aEntity, result);
        			
                    if(!result.hasMsg()){
                        return result;
                    }else {
	                    result.getMsg();
	                    return result;
                    }
                }
            }   
        }
                return null;  		
    }

}
