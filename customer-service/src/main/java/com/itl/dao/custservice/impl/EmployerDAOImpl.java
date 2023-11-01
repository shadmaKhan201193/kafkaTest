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
import com.itl.dao.custservice.EmployerDAO;
import com.itl.domain.entities.custservice.EmployerMst;
import com.itl.exceptions.NGException;

@Repository("employerDAO")
public class EmployerDAOImpl extends JpaDAOImpl<Long, EmployerMst> 
	implements EmployerDAO {

	private static final Logger logger = LoggerFactory.getLogger(EmployerDAOImpl.class);
	
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
	
	public EmployerMst getPrimaryKey(Long Id) throws NGException {
		logger.debug("Inside getPrimaryKey method ");
		logger.info("PK ID= " + Id);

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("Id", Id);

		List<EmployerMst> rMaaz = findByNamedQueryAndNamedParams("EmployerMst.getUniqueEmployer", queryParams);
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
	
	
	public List<EmployerMst> getByEmployerName(String EmployerName) throws NGException {
		logger.debug("Inside getByEmployerName method ");
		logger.info("EmployerName "  +EmployerName);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("employerName", EmployerName);

		List<EmployerMst> rMaaz = findByNamedQueryAndNamedParams("EmployerMst.getEmployerName", queryParams);
		if (null == rMaaz) {
			logger.info("Inside if condition");
			return null;
		} else if (rMaaz != null && rMaaz.size() == 0) {
			logger.info("Inside elseif condition");
			return null;
		}
		logger.debug("Exit from getByEmployerName method");
		return rMaaz;
	}
	
	public List<EmployerMst> getByEmployerId(String EmployerId) throws NGException {
		logger.debug("Inside getByEmployerId method ");
		logger.info("EmployerId "  +EmployerId);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("employerId", EmployerId);

		List<EmployerMst> rMaaz = findByNamedQueryAndNamedParams("EmployerMst.getEmployerId", queryParams);
		if (null == rMaaz) {
			logger.info("Inside if condition");
			return null;
		} else if (rMaaz != null && rMaaz.size() == 0) {
			logger.info("Inside elseif condition");
			return null;
		}
		logger.debug("Exit from getByEmployerId method");
		return rMaaz;
	}
}
