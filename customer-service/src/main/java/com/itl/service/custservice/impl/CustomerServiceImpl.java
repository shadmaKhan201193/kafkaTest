package com.itl.service.custservice.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itl.dao.base.JPADAO;
import com.itl.dao.custservice.CustomerDAO;
import com.itl.domain.entities.custservice.CustomerMst;
import com.itl.exceptions.NGException;
import com.itl.service.base.impl.NGServiceImpl;
import com.itl.service.custservice.CustomerService;

@Service("customerService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = NGException.class)
public class CustomerServiceImpl extends NGServiceImpl<Long, CustomerMst>
	implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	protected CustomerDAO customerDAO;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostConstruct
	public void init() throws Exception {
		super.setDAO((JPADAO) customerDAO);
	}

	@PreDestroy
	public void destroy() {
	}

	@Override
	public void setEntityManagerOnDao(EntityManager entityManager) {
		logger.info("Inside setEntityManagerOnDao method :: " + entityManager);

		customerDAO.setEntityManager(entityManager);
	}
	
	public CustomerMst getPrimaryKey(Long Id) throws NGException {
		logger.info("Inside getPrimaryKey method :: " + Id);

		if (Id == null)
			return null;
		CustomerMst opStat = customerDAO.getPrimaryKey(Id);
		if (null == opStat) {
			return null;
		} else {
			return opStat;
		}
	}

	
	public List<CustomerMst> getByCustomerName(String customerName) throws NGException {
		logger.debug("Enter Inside getByCustomerName method");
		logger.info("Info getByCustomerName method :: " + customerName);
		List<CustomerMst> opStat = customerDAO.getByCustomerName(customerName);
		logger.debug("Exit from  getByCustomerName method");
		return opStat;
	}
	
	public List<CustomerMst> getByCustomerId(String customerId) throws NGException {
		logger.debug("Enter Inside getByCustomerId method");
		logger.info("Info getByCustomerId method :: " + customerId);
		List<CustomerMst> opStat = customerDAO.getByCustomerId(customerId);
		logger.debug("Exit from  getByCustomerId method");

		return opStat;
	}
	
	public List<CustomerMst> getByAuthStatus(String authStatus) throws NGException {
		logger.debug("Enter Inside getByAuthStatus method");
		logger.info("Info getByAuthStatus method :: " + authStatus);
		List<CustomerMst> opStat = customerDAO.getByAuthStatus(authStatus);
		logger.debug("Exit from  getByAuthStatus method");

		return opStat;
	}
	
	public List<CustomerMst> getByDeleted() throws NGException {
		logger.debug("Enter Inside getByDeleted method");
		List<CustomerMst> opStat = customerDAO.getByDeleted();
		logger.debug("Exit from  getByDeleted method");
		return opStat;
	}
	
	public CustomerMst saveOrUpdate(String loginId, CustomerMst entity) throws NGException {
		logger.info("PK>>ID::" + entity.getId());
		CustomerMst rMaaz = getPrimaryKey(entity.getId());
		try {
			if (null == rMaaz) {
				return super.saveOrUpdate(loginId, entity);
			} else {
				// ---> update mode
				CustomerMst rmfa = rMaaz;
				Mapper mapper = new DozerBeanMapper();
				BeanMappingBuilder builderMinusAudit = new BeanMappingBuilder() {
					@Override
					protected void configure() {
					mapping(CustomerMst.class, CustomerMst.class)
					.exclude("customerDetail")
					.exclude("introducerDetail")
					.exclude("kycDetail");
					}
					};
					((DozerBeanMapper) mapper).addMapping(builderMinusAudit);


				mapper.map(entity, rmfa);
				return super.saveOrUpdate(loginId, rmfa);
			}
		} catch (Exception e) {
			logger.info("Error for PK");
			e.printStackTrace();
			logger.info("Error for PK>>ID::" + entity.getId());
		}
		logger.debug("Exit from  saveOrUpdate method");

		return null;
	}
}
