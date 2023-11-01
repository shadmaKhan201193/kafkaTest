package com.itl.dao.custservice;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.itl.dao.base.JPADAO;
import com.itl.domain.entities.custservice.CustomerCategoryTypeMst;
import com.itl.exceptions.NGException;

public interface CustomerCategoryTypeDAO extends JPADAO<CustomerCategoryTypeMst, Long> {

	public EntityManagerFactory getEntityManagerFactory();
	
	public CustomerCategoryTypeMst getPrimaryKey(Long Id) throws NGException;
	
	public List<CustomerCategoryTypeMst> getByCustomerCategoryTypeName(String customerCategoryTypeName) throws NGException;
	
	public List<CustomerCategoryTypeMst> getByCustomerCategoryTypeId(String customerCategoryTypeId) throws NGException;
	
}
