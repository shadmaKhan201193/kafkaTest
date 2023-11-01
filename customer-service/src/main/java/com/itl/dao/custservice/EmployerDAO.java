package com.itl.dao.custservice;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.itl.dao.base.JPADAO;
import com.itl.domain.entities.custservice.EmployerMst;
import com.itl.exceptions.NGException;

public interface EmployerDAO extends JPADAO<EmployerMst, Long> {

	public EntityManagerFactory getEntityManagerFactory();
	
	public EmployerMst getPrimaryKey(Long Id) throws NGException;
	
	public List<EmployerMst> getByEmployerName(String employerName) throws NGException;
	
	public List<EmployerMst> getByEmployerId(String employerId) throws NGException;
	
}
