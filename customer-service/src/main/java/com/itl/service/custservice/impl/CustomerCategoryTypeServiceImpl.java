package com.itl.service.custservice.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itl.dao.base.JPADAO;
import com.itl.dao.custservice.CustomerCategoryTypeDAO;
import com.itl.domain.entities.custservice.CustomerCategoryTypeMst;
import com.itl.exceptions.NGException;
import com.itl.service.base.impl.NGServiceImpl;
import com.itl.service.custservice.CustomerCategoryTypeService;

@Service("customerCategoryTypeService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = NGException.class)
public class CustomerCategoryTypeServiceImpl extends NGServiceImpl<Long, CustomerCategoryTypeMst>
	implements CustomerCategoryTypeService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerCategoryTypeServiceImpl.class);
	
	@Autowired
	protected CustomerCategoryTypeDAO customerCategoryTypeDAO;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostConstruct
	public void init() throws Exception {
		super.setDAO((JPADAO) customerCategoryTypeDAO);
	}

	@PreDestroy
	public void destroy() {
	}

	@Override
	public void setEntityManagerOnDao(EntityManager entityManager) {
		logger.info("Inside setEntityManagerOnDao method :: " + entityManager);

		customerCategoryTypeDAO.setEntityManager(entityManager);
	}
	
	
	public CustomerCategoryTypeMst getPrimaryKey(Long Id) throws NGException {
		logger.info("Inside getPrimaryKey method :: " + Id);

		CustomerCategoryTypeMst opStat = customerCategoryTypeDAO.getPrimaryKey(Id);
		if (null == opStat) {
			logger.info("Inside if block");
			return null;
		} else {
			logger.info("Inside else block");
			return opStat;
		}
	}

	public List<CustomerCategoryTypeMst> getByCustomerCategoryTypeName(String customerCategoryTypeName) throws NGException {
		logger.debug("Enter Inside getByCustomerCategoryTypeName method");
		logger.info("Info getByCustomerCategoryTypeName method :: " + customerCategoryTypeName);
		List<CustomerCategoryTypeMst> opStat = customerCategoryTypeDAO.getByCustomerCategoryTypeName(customerCategoryTypeName);
		
		logger.debug("Exit from  getByCustomerCategoryTypeName method");

		return opStat;
	}
	
	
	public List<CustomerCategoryTypeMst> getByCustomerCategoryTypeId(String customerCategoryTypeId) throws NGException {
		logger.debug("Enter Inside getByCustomerCategoryTypeId method");
		logger.info("Info getByCustomerCategoryTypeId method :: " + customerCategoryTypeId);

		List<CustomerCategoryTypeMst> opStat = customerCategoryTypeDAO.getByCustomerCategoryTypeId(customerCategoryTypeId);
		logger.debug("Exit from  customerCategoryTypeId method");
		return opStat;
	}
	
	
	public CustomerCategoryTypeMst saveOrUpdate(String loginId, CustomerCategoryTypeMst entity) throws NGException {
		logger.info("PK>>ID::" + entity.getId());
		CustomerCategoryTypeMst rMaaz = getPrimaryKey(entity.getId());
		try {
			if (null == rMaaz) {
				return super.saveOrUpdate(loginId, entity);
			} else {
				// ---> update mode
				CustomerCategoryTypeMst rmfa = rMaaz;
				Mapper mapper = new DozerBeanMapper();
				mapper.map(entity, rmfa);
				return super.saveOrUpdate(loginId, rmfa);
			}
		} catch (Exception e) {
			logger.info("Error for PK");
			e.printStackTrace();
			logger.info("Error for PK>>ID::" + entity.getId());
		}
		return null;
	}
}
