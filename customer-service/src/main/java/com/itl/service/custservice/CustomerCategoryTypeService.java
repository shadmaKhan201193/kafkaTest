package com.itl.service.custservice;

import java.util.List;

import com.itl.domain.entities.custservice.CustomerCategoryTypeMst;
import com.itl.exceptions.NGException;
import com.itl.service.base.NGService;

public interface CustomerCategoryTypeService extends NGService {

	public CustomerCategoryTypeMst getPrimaryKey(Long Id) throws NGException;

	public List<CustomerCategoryTypeMst> getByCustomerCategoryTypeName(String customerCategoryTypeName) throws NGException;
	
	public List<CustomerCategoryTypeMst> getByCustomerCategoryTypeId(String customerCategoryTypeId) throws NGException;
	
	public CustomerCategoryTypeMst saveOrUpdate(String loginId, CustomerCategoryTypeMst entity) throws NGException;
}
