package com.itl.domain.entities.custservice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import com.itl.domain.entities.base.Base;

@Entity
@Audited(auditParents = { Base.class })
public class CustomerIntroducerMst extends Base {

	private static final long serialVersionUID = -1L;

	@Column(nullable = false)
	private Integer custSrNo = 1;
	
	@Column(nullable = false, length = 16)
	private String customerId = "";
	
	@Column(nullable = false, length = 16)
	private String introducerId="";
	
	@Temporal(TemporalType.DATE)
	private Date introducedDate;
	
	@ManyToOne
	@JoinColumn(name = "fkCustomerPk")
	@LazyCollection(LazyCollectionOption.FALSE)
	private CustomerMst customer;
	
	@Column(nullable = true)
	private Integer isActive = 1;

	@Column(nullable = false, length = 8)
	private String authStatus="";

	public Integer getCustSrNo() {
		return custSrNo;
	}

	public void setCustSrNo(Integer custSrNo) {
		this.custSrNo = custSrNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIntroducerId() {
		return introducerId;
	}

	public void setIntroducerId(String introducerId) {
		this.introducerId = introducerId;
	}

	public Date getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(Date introducedDate) {
		this.introducedDate = introducedDate;
	}

	public CustomerMst getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerMst customer) {
		this.customer = customer;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
}
