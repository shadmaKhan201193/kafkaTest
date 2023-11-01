package com.itl.service.custservice;

import java.util.List;

import com.itl.domain.entities.custservice.CustomerMst;
import com.itl.exceptions.NGException;
import com.itl.service.base.NGService;

public interface CustomerService extends NGService {

	public CustomerMst getPrimaryKey(Long Id) throws NGException;

	public List<CustomerMst> getByCustomerName(String customerName) throws NGException;
	
	public List<CustomerMst> getByCustomerId(String customerId) throws NGException;
	
	public CustomerMst saveOrUpdate(String loginId, CustomerMst entity) throws NGException;
	
	public List<CustomerMst> getByAuthStatus(String authStatus) throws NGException;
	
	public List<CustomerMst> getByDeleted() throws NGException;
}
