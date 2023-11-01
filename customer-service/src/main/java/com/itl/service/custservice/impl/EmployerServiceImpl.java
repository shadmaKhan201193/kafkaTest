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
import com.itl.dao.custservice.EmployerDAO;
import com.itl.domain.entities.custservice.EmployerMst;
import com.itl.exceptions.NGException;
import com.itl.service.base.impl.NGServiceImpl;
import com.itl.service.custservice.EmployerService;

@Service("employerService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = NGException.class)
public class EmployerServiceImpl extends NGServiceImpl<Long, EmployerMst>
	implements EmployerService {

	private static final Logger logger = LoggerFactory.getLogger(EmployerServiceImpl.class);
	
	@Autowired
	protected EmployerDAO employerDAO;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostConstruct
	public void init() throws Exception {
		super.setDAO((JPADAO) employerDAO);
	}

	@PreDestroy
	public void destroy() {
	}

	@Override
	public void setEntityManagerOnDao(EntityManager entityManager) {
		logger.info("Inside setEntityManagerOnDao method :: " + entityManager);
		employerDAO.setEntityManager(entityManager);
	}
	
	public EmployerMst getPrimaryKey(Long Id) throws NGException {
		logger.info("Inside getPrimaryKey method :: " + Id);

		EmployerMst opStat = employerDAO.getPrimaryKey(Id);
		if (null == opStat) {
			return null;
		} else {
			return opStat;
		}
	}

	public List<EmployerMst> getByEmployerName(String employerName) throws NGException {
		logger.debug("Enter Inside getByEmployerName method");
		logger.info("Info getByEmployerName method :: " + employerName);
		List<EmployerMst> opStat = employerDAO.getByEmployerName(employerName);
		logger.debug("Exit from  getByEmployerName method");

		return opStat;
	}
	
	public List<EmployerMst> getByEmployerId(String employerId) throws NGException {
		logger.debug("Enter Inside getByEmployerId method");
		logger.info("Info getByEmployerId method :: " + employerId);
		List<EmployerMst> opStat = employerDAO.getByEmployerId(employerId);
		logger.debug("Exit from  getByEmployerId method");

		return opStat;
	}
	
	public EmployerMst saveOrUpdate(String loginId, EmployerMst entity) throws NGException {
		logger.info("PK>>ID::" + entity.getId());
		EmployerMst rMaaz = getPrimaryKey(entity.getId());
		try {
			if (null == rMaaz) {
				return super.saveOrUpdate(loginId, entity);
			} else {
				// ---> update mode
				EmployerMst rmfa = rMaaz;
				Mapper mapper = new DozerBeanMapper();
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
