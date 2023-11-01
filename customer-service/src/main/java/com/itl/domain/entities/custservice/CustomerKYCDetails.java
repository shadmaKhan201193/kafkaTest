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
public class CustomerKYCDetails extends Base {

	private static final long serialVersionUID = -1L;

	@Column(nullable = false)
	private Integer custSrNo = 1;

	@ManyToOne
	@JoinColumn(name = "fkCustomerPk")
	@LazyCollection(LazyCollectionOption.FALSE)
	private CustomerMst customer;
	
	@Column(nullable = false, length = 16)
	private String customerId = "";
	
	@Column(nullable = false,length = 8)
	private String docTypeId = "";
	
	@Column(nullable = false,length = 24)
	private String refId="";
	
	@Temporal(TemporalType.DATE)
	private Date validFrom;
	
	@Temporal(TemporalType.DATE)
	private Date validTo;
	
	@Column(nullable = false,length = 48)
	private String IssuedBy="";
	
	@Column(nullable = true)
	private Integer isActive = 1;

	@Column(nullable = true, length = 8)
	private String authStatus="";

	public Integer getCustSrNo() {
		return custSrNo;
	}

	public void setCustSrNo(Integer custSrNo) {
		this.custSrNo = custSrNo;
	}

	public CustomerMst getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerMst customer) {
		this.customer = customer;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDocTypeId() {
		return docTypeId;
	}

	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getIssuedBy() {
		return IssuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		IssuedBy = issuedBy;
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