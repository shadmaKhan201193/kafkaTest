package com.itl.dao.custservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itl.dao.base.impl.JpaDAOImpl;
import com.itl.dao.custservice.CustomerDAO;
import com.itl.domain.entities.custservice.CustomerMst;
import com.itl.exceptions.NGException;

@Repository("customerDAO")
public class CustomerDAOImpl extends JpaDAOImpl<Long, CustomerMst> 
	implements CustomerDAO {

	private static final Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);
	
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@PersistenceContext(unitName = "PRODTECH")
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
		super.setEntityManager(entityManager);
	}

	@PostConstruct
	public void init() {
		super.setEntityManagerFactory(entityManagerFactory);
		super.setEntityManager(entityManager);
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public CustomerMst getPrimaryKey(Long Id) throws NGException {
		logger.debug("Inside getPrimaryKey method ");
		logger.info("PK ID= " + Id);

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("Id", Id);

		List<CustomerMst> rMaaz = findByNamedQueryAndNamedParams("CustomerMst.getUniqueCustomer", queryParams);
		if (null == rMaaz) {
			logger.info("Inside if condition");
			return null;
		} else if (rMaaz != null && rMaaz.size() == 0) {
			logger.info("Inside elseif condition");
			return null;
		}
		logger.debug("Exit getPrimaryKey Method");
		return rMaaz.get(0);
	}
	
	public List<CustomerMst> getByCustomerName(String customerName) throws NGException {
		logger.debug("Inside getByCustomerName method ");
		logger.info("CustomerName "  +customerName);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("customerName", customerName);

		List<CustomerMst> rMaaz = findByNamedQueryAndNamedParams("CustomerMst.getCustomerName", queryParams);
		if (null == rMaaz) {
			logger.info("Inside if condition");
			return null;
		} else if (rMaaz != null && rMaaz.size() == 0) {
			logger.info("Inside elseif condition");
			return null;
		}
		logger.debug("Exit from getByCustomerName method");
		return rMaaz;
	}
	
	public List<CustomerMst> getByCustomerId(String customerId) throws NGException {
		logger.debug("Inside getByCustomerId method ");
		logger.info("CustomerId "  +customerId);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("customerId", customerId);

		List<CustomerMst> rMaaz = findByNamedQueryAndNamedParams("CustomerMst.getCustomerId", queryParams);
		if (null == rMaaz) {
			logger.info("Inside if condition");
			return null;
		} else if (rMaaz != null && rMaaz.size() == 0) {
			logger.info("Inside elseif condition");
			return null;
		}
		logger.debug("Exit from getByCustomerId method");
		return rMaaz;
	}
	
	
	public List<CustomerMst> getByAuthStatus(String authStatus) throws NGException {
		logger.debug("Inside getByAuthStatus method ");
		logger.info("AuthStatus "  +authStatus);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("authStatus", authStatus);
		queryParams.put("isDeleted",Boolean.FALSE);

		List<CustomerMst> rMaaz = findByNamedQueryAndNamedParams("CustomerMst.getByAuthStatus", queryParams);
		if (null == rMaaz) {
			logger.info("Inside if condition");
			return null;
		} else if (rMaaz != null && rMaaz.size() == 0) {
			logger.info("Inside elseif condition");
			return null;
		}
		logger.debug("Exit from getByAuthStatus method");
		return rMaaz;
	}
	
	public List<CustomerMst> getByDeleted() throws NGException {
		logger.debug("Inside getByDeleted method ");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("isDeleted",Boolean.TRUE);

		List<CustomerMst> rMaaz = findByNamedQueryAndNamedParams("CustomerMst.getByDeleted", queryParams);
		if (null == rMaaz) {
			logger.info("Inside if condition");
			return null;
		} else if (rMaaz != null && rMaaz.size() == 0) {
			logger.info("Inside elseif condition");
			return null;
		}
		logger.debug("Exit from getByDeleted method");
		return rMaaz;
	}
}
