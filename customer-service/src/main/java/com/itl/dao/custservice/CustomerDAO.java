package com.itl.dao.custservice;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.itl.dao.base.JPADAO;
import com.itl.domain.entities.custservice.CustomerMst;
import com.itl.exceptions.NGException;

public interface CustomerDAO extends JPADAO<CustomerMst, Long> {

	public EntityManagerFactory getEntityManagerFactory();
	
	public CustomerMst getPrimaryKey(Long Id) throws NGException;
	
	public List<CustomerMst> getByCustomerName(String customerName) throws NGException;
	
	public List<CustomerMst> getByCustomerId(String customerId) throws NGException;
	
	public List<CustomerMst> getByAuthStatus(String authStatus) throws NGException;
	
	public List<CustomerMst> getByDeleted() throws NGException;
}
