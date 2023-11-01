package com.itl.domain.entities.custservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.itl.domain.entities.base.Base;
import com.itl.domain.entities.masters.Address;

@Entity
@Audited(auditParents = { Base.class })
public class CustomerDetails extends Base {

	private static final long serialVersionUID = -1L;

	@Column(nullable = false)
	private Integer custSrNo = 1;

	@ManyToOne
	@JoinColumn(name = "fkCustomerPk")
	@LazyCollection(LazyCollectionOption.FALSE)
	private CustomerMst customer;

	@NotAudited
	@ElementCollection
	@JoinTable(name = "CustomerAddress", joinColumns = @JoinColumn(name = "CustomerDetail_ID"))
	@GenericGenerator(name = "sequence_gen", strategy = "sequence")
	@CollectionId(columns = { @Column(name = "Id") }, generator = "sequence_gen", type = @Type(type = "long"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Address> customerAddresses = new ArrayList<Address>();
	
	@Column(nullable = false, length = 8)
	private String caste="";
	
	@Column(nullable = false, length = 16)
	private String citizenship="";
	
	@Column(nullable = false, length = 8)
	private String customerCategory;
	
	@Column(nullable = false, length = 96)
	private String customerType="";
	
	@Column(nullable = true, length = 96)
	private String fatherName="";
	
	@Column(nullable = true, length = 96)
	private String husbandName="";
	
	@Column(nullable = true, length = 96)
	private String motherName="";
	
	@Column(nullable = true, length = 8)
	private String gender="";
	
	@Temporal(TemporalType.DATE)
	private Date kycExpiryDate;
	
	@Column(nullable = true, length = 8)
	private String maritalStatus="";
	
	@Column(nullable = true, length = 8)
	private String occupation="";
	
	@Column(nullable = true, length = 8)
	private String preferenceCategory="";
	
	@Column(nullable = true, length = 8)
	private String qualification="";
	
	@Column(nullable = true, length = 8)
	private String religion="";
	
	@Column(nullable = true, length = 8)
	private String residentialStatus="";
	
	@Column(nullable = true)
	private Boolean selfEmployed = true;
	
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

	public Collection<Address> getCustomerAddresses() {
		return customerAddresses;
	}

	public void setCustomerAddresses(Collection<Address> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getHusbandName() {
		return husbandName;
	}

	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getKycExpiryDate() {
		return kycExpiryDate;
	}

	public void setKycExpiryDate(Date kycExpiryDate) {
		this.kycExpiryDate = kycExpiryDate;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPreferenceCategory() {
		return preferenceCategory;
	}

	public void setPreferenceCategory(String preferenceCategory) {
		this.preferenceCategory = preferenceCategory;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getResidentialStatus() {
		return residentialStatus;
	}

	public void setResidentialStatus(String residentialStatus) {
		this.residentialStatus = residentialStatus;
	}

	public Boolean getSelfEmployed() {
		return selfEmployed;
	}

	public void setSelfEmployed(Boolean selfEmployed) {
		this.selfEmployed = selfEmployed;
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
