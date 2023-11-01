package com.itl.web.dto;

import com.itl.baseAbstract.BaseAbstract;

public class CustomerVO extends  BaseAbstract{

	private String customerId = "";
	private Integer tenantId ;
	private String customerFullName = "";

	private String customerFullDisplayName = "";
	
	private String firstName="";
	
	private String lastName="";
	
	private String middleName="";
	
	private String dateOfBirth="";
	
	private String mobileNumber="";
	
	private String emailId="";
	
	private String caste;
	
	private String citizenship="";
	
	private String customerCategory;
	
	private String customerType="";
	
	private String fatherName="";
	
	private String husbandName="";
	
	private String motherName="";
	
	private String gender="";
	
	private String kycExpiryDate="";
	
	private String maritalStatus="";
	
	private String occupation="";
	
	private String preferenceCategory="";
	
	private String qualification="";
	
	private String religion="";
	
	private String residentialStatus="";
	
	private String selfEmployed = "";
	
	private String introducerId="";
	private String introducedDate;
	private String isActive="";
	private String isDeleted="";
	
	
	

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	private String authStatus="";
	
	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
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

	public String getKycExpiryDate() {
		return kycExpiryDate;
	}

	public void setKycExpiryDate(String kycExpiryDate) {
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

	public String getSelfEmployed() {
		return selfEmployed;
	}

	public void setSelfEmployed(String selfEmployed) {
		this.selfEmployed = selfEmployed;
	}

	public String getIntroducerId() {
		return introducerId;
	}

	public void setIntroducerId(String introducerId) {
		this.introducerId = introducerId;
	}
	
	public String getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(String introducedDate) {
		this.introducedDate = introducedDate;
	}

	@Override
	public String getPrimaryKey() {
		
	//	return getPrimaryKeyData();
		return getTenantId()+"|"+getCustomerId();
	}
	
}