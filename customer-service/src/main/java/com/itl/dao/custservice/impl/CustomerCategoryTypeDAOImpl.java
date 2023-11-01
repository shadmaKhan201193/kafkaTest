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
import com.itl.dao.custservice.CustomerCategoryTypeDAO;
import com.itl.domain.entities.custservice.CustomerCategoryTypeMst;
import com.itl.exceptions.NGException;

@Repository("customerCategoryTypeDAO")
public class CustomerCategoryTypeDAOImpl extends JpaDAOImpl<Long, CustomerCategoryTypeMst> 
	implements CustomerCategoryTypeDAO {

	private static final Logger logger = LoggerFactory.getLogger(CustomerCategoryTypeDAOImpl.class);
	
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
		logger.debug("Inside getEntityManagerFactory method");
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		logger.debug("Inside setEntityManager method");
		this.entityManagerFactory = entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		logger.debug("Inside getEntityManager method");
		return entityManager;
	}
	
	public CustomerCategoryTypeMst getPrimaryKey(Long Id) throws NGException {
		logger.debug("Inside getPrimaryKey method ");
		logger.info("PK ID= " + Id);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("Id", Id);

		List<CustomerCategoryTypeMst> rMaaz = findByNamedQueryAndNamedParams("CustomerCategoryTypeMst.getUniqueCustomerCategoryType", queryParams);
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
	
	
	public List<CustomerCategoryTypeMst> getByCustomerCategoryTypeName(String CustomerCategoryTypeName) throws NGException {
		logger.debug("Inside getByCustomerCategoryTypeName method ");
		logger.info("CustomerCategoryTypeName "  +CustomerCategoryTypeName);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("customerCategoryTypeName", CustomerCategoryTypeName);

		List<CustomerCategoryTypeMst> rMaaz = findByNamedQueryAndNamedParams("CustomerCategoryTypeMst.getCustomerCategoryTypeName", queryParams);
		if (null == rMaaz) {
			logger.info("Inside if condition");
			return null;
		} else if (rMaaz != null && rMaaz.size() == 0) {
			logger.info("Inside elseif condition");
			return null;
		}
		logger.debug("Exit from getByCustomerCategoryTypeName method");
		return rMaaz;
	}
	
	
	
	public List<CustomerCategoryTypeMst> getByCustomerCategoryTypeId(String CustomerCategoryTypeId) throws NGException {
		logger.debug("Inside getByCustomerCategoryTypeId method ");
		logger.info("CustomerCategoryTypeId "  +CustomerCategoryTypeId);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("customerCategoryTypeId", CustomerCategoryTypeId);

		List<CustomerCategoryTypeMst> rMaaz = findByNamedQueryAndNamedParams("CustomerCategoryTypeMst.getCustomerCategoryTypeId", queryParams);
		if (null == rMaaz) {
			logger.info("Inside if condition");
			return null;
		} else if (rMaaz != null && rMaaz.size() == 0) {
			logger.info("Inside elseif condition");
			return null;
		}
		logger.debug("Exit from getByCustomerCategoryTypeId method");
		return rMaaz;
	}
}
