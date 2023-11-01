package com.itl.domain.entities.custservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import com.itl.domain.entities.base.Base;

@Entity
@Audited(auditParents = { Base.class })
@NamedQueries({ @NamedQuery(name = "CustomerMst.getUniqueCustomer", query = "SELECT e FROM CustomerMst e WHERE e.id=:Id"),
		@NamedQuery(name = "CustomerMst.getCustomerName", query = "SELECT e FROM CustomerMst e WHERE e.customerFullName=:customerName"),
		@NamedQuery(name = "CustomerMst.getCustomerId", query = "SELECT e FROM CustomerMst e WHERE e.customerId=:customerId"),
		@NamedQuery(name = "CustomerMst.getByAuthStatus", query = "SELECT e FROM CustomerMst e WHERE e.authStatus=:authStatus AND e.isDeleted=:isDeleted"),
		@NamedQuery(name = "CustomerMst.getByDeleted", query = "SELECT e FROM CustomerMst e WHERE e.isDeleted=:isDeleted")
})
public class CustomerMst extends Base {

	private static final long serialVersionUID = -1L;

	@Column(nullable = false, length = 16)
	private String customerId = "";

	@Column(nullable = false, length = 96)
	private String customerFullName = "";

	@Column(nullable = false, length = 96)
	private String customerFullDisplayName = "";
	
	@Column(nullable = false, length = 96)
	private String firstName="";
	
	@Column(nullable = false, length = 96)
	private String lastName="";
	
	@Column(nullable = false, length = 96)
	private String middleName="";
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(nullable = false, length = 16)
	private String mobileNumber="";
	
	@Column(nullable = true, length = 64)
	private String emailId="";
	
	@Column(nullable = true)
	private Integer isActive = 1;

	@Column(nullable = true, length = 8)
	private String authStatus="";
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CustomerDetails> customerDetail = new ArrayList<CustomerDetails>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CustomerIntroducerMst> introducerDetail = new ArrayList<CustomerIntroducerMst>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CustomerKYCDetails> kycDetail = new ArrayList<CustomerKYCDetails>();

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	public String getCustomerFullDisplayName() {
		return customerFullDisplayName;
	}

	public void setCustomerFullDisplayName(String customerFullDisplayName) {
		this.customerFullDisplayName = customerFullDisplayName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public List<CustomerDetails> getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(List<CustomerDetails> customerDetail) {
		this.customerDetail = customerDetail;
	}

	public List<CustomerIntroducerMst> getIntroducerDetail() {
		return introducerDetail;
	}

	public void setIntroducerDetail(List<CustomerIntroducerMst> introducerDetail) {
		this.introducerDetail = introducerDetail;
	}

	public List<CustomerKYCDetails> getKycDetail() {
		return kycDetail;
	}

	public void setKycDetail(List<CustomerKYCDetails> kycDetail) {
		this.kycDetail = kycDetail;
	}
}