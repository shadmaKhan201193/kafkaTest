package com.itl.service.custservice;

import java.util.List;

import com.itl.domain.entities.custservice.EmployerMst;
import com.itl.exceptions.NGException;
import com.itl.service.base.NGService;

public interface EmployerService extends NGService {

	public EmployerMst getPrimaryKey(Long Id) throws NGException;

	public List<EmployerMst> getByEmployerName(String employerName) throws NGException;
	
	public List<EmployerMst> getByEmployerId(String employerId) throws NGException;
	
	public EmployerMst saveOrUpdate(String loginId, EmployerMst entity) throws NGException;
}
