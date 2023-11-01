package com.itl.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class D009011VO implements Serializable {
	private long id;

	private List<Object> CustObject = new ArrayList<Object>();

	private Integer version;

	private Integer memberCode;

	private String customerStatus;

	private String customerCategory;

	private String customerType;

	// @DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateOfApplication;

	private String nameTitle = "";

	// private String memberFName = "";

	private String memberMName = "";

	private String memberLName = "";

	private String dateOfBirth;

	private String religion = "";

	private String memberCaste = "";

	private String subCaste = "";

	private String qualification = "";

	private String memberGender = "";

	private Date memberDOB;

	private String bloodGroup = "";

	private String resAddProofID = "";

	private Date dateOfIssue;

	private String refDocNo = "";

	private String nationality = "";

	private Integer residentYN = 0;

	private String residentialStatus = "";

	private String address1 = "";

	private String address2 = "";

	private String address3 = "";

	private String countryCode = "";

	private String stateCode = "";

	private String districtCode = "";

	private String municipalityCode = "";

	private String villageCode = "";

	private String pinCode = "";

	private String isdtelephoneNo = "";

	private String phone = "";

	private String isdmobileNo1 = "";

	private String mobileNo1 = "";

	private String isdmobileNo2 = "";

	private String mobileNo2 = "";

	private String occupation = "";

	private String emailId = "";

	private String isdfax = "";

	private String fax = "";

	private Integer rating = 0;

	private Integer introducerConfirmedYN = 0;

	private String introducerCustNo = "";

	private String introducerCustName = "";

	// for Corporate
	private String corporateName = "";

	private String orgType = "";

	private String businessType = "";

	private int noOfBranches = 0;

	// @DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date commencementDate;

	private String placeOfEstablishment = "";

	// @DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateOfEstablishment;

	private String maritalStatus;

	private Integer ttlMaleChild;

	private Integer ttlfemaleChild;

	private Integer ttlFamilyMbr;

	private String frzReasonCd;

	private String reason;

	private String mbrMaritalStatus;
	private String totalMaleChild;
	private String totalFemaleChild;
	private String totalFamilyMbr;
	private String freezeReason;

	private Date photoIdExpiryDate = null;
	private String photoIdProofType = "";
	private String homeTelNoISD = "";
	private String homeTelNo = "";
	private String npaRiskRating = "";
	private String occupationType = "";
	private Integer pepYN = 0;
	private String relOff = "";
	private Integer staffYN = 0;
	private String staffId = "";
	private String AMLRating = "";
	private String HNWCategory = "";
	private String NPARating = "";
	private String spouseName = "";

	private String fatherSpouseName = "";

	// @DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date freezeDate;

	private String authStatus;

	private Integer conCurChkYN;

	private Integer uaeYN;

	private Double tdsProjection = 0D;
	private Double tdsProvision = 0D;
	private Date tdsFrm15SubDt;
	private Double intProjected = 0D;
	private Double intProvision = 0D;
	private Integer tdsYN = 1;
	private Double tdsPercentage = 0D;
	private String tdsReasonCd = "";

	private String memberTypeCode = "";
	private Date memberDate;
	private String serviceManager = "";
	private String groupCode = "";
	private String groupName = "";
	private Integer customerBranch;
	private String dematId = "";
	private String dematAccountNo = "";
	private String bankName = "";
	private String bankAccountNo = "";
	private String bankIFSCCode = "";
	private Integer KYCAvailableYN = 0;

	// CORPORATE FIELDS - START
	private String shortName = "";
	private String businessOperationCountry = "";
	private String subBusinessType = "";
	private String registrationNo = "";
	private Integer operationYears = 0;
	private String taxResidenceStatus = "";
	private String pan = "";
	private String taxIdNo = "";
	private Integer form60YN;
	private String gstNo = "";

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date gstRegDate;

	private String vatRegNo = "";

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date vatRegDate;

	private String salesTaxRegNo = "";

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date salesTaxRegDate;

	private String ieCode = "";

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date ieCodeRegDate;

	private String tel1CountryCode = "";
	private String tel2CountryCode = "";
	private String faxCountryCode = "";
	private String mobile1CountryCode = "";
	private String mobile2CountryCode = "";
	private String contactPerson = "";
	private String annualTurnover = "";
	private String noOfEmployees = "";
	private String riskRating = "";
	private String affiliatedEntityYN = "";
	private String affiliationType = "";
	private String operationCountry = "";
	private String custReason = "";
	private String specialInstruct1 = "";
	private String specialInstruct2 = "";
	private String docType = "";
	private String docDetails = "";
	private String proprietorCustId = "";
	private String proprietorCustName = "";
	private String proprietorAddress = "";
	private String associationType = "";
	private String mlProprietorDetails = "";
	private String mlKycDetails = "";
	private String website = "";
	private String proofType = "";
	private String idNumber = "";
	private String mlBusinessCommunDetails = "";
	private String addressDocId = "";
	private String primaryKeyData = "";
	private Integer ownershipPercentage = 0;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date issuedDate;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date expiryDate;

	private String issuedBy = "";
	private String nameAsInDocument = "";

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date recievedDate;

	private Date kycExpiryDate = null;

	// private Date lastModifiedDate;

	private byte[] kycDoc;
	MultipartFile doc;

	private String addressType = "";
	// CORPORATE FIELDS - END

	// Added for KAFALAH
	private String nationalId = "";

	private String legalEntity;

	private String firstnameArabic = "";
	private String lastnameArabic = "";
	private String companynameArabic = "";
	private String smeSize = "";
	private String cR700number = "";
	private String memberFName = "";

	public String getMemberFName() {
		return memberFName;
	}

	public void setMemberFName(String memberFName) {
		this.memberFName = memberFName;
	}

	public String getCompanynameArabic() {
		return companynameArabic;
	}

	public void setCompanynameArabic(String companynameArabic) {
		this.companynameArabic = companynameArabic;
	}

	public String getSmeSize() {
		return smeSize;
	}

	public void setSmeSize(String smeSize) {
		this.smeSize = smeSize;
	}

	public String getcR700number() {
		return cR700number;
	}

	public void setcR700number(String cR700number) {
		this.cR700number = cR700number;
	}

	public String getFirstnameArabic() {
		return firstnameArabic;
	}

	public void setFirstnameArabic(String firstnameArabic) {
		this.firstnameArabic = firstnameArabic;
	}

	public String getLastnameArabic() {
		return lastnameArabic;
	}

	public void setLastnameArabic(String lastnameArabic) {
		this.lastnameArabic = lastnameArabic;
	}

	public String getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	// Added for KAFALAH - ends

	public Double getTdsProjection() {
		return tdsProjection;
	}

	public void setTdsProjection(Double tdsProjection) {
		this.tdsProjection = tdsProjection;
	}

	public Double getTdsProvision() {
		return tdsProvision;
	}

	public void setTdsProvision(Double tdsProvision) {
		this.tdsProvision = tdsProvision;
	}

	public Double getIntProjected() {
		return intProjected;
	}

	public void setIntProjected(Double intProjected) {
		this.intProjected = intProjected;
	}

	public String getMemberTypeCode() {
		return memberTypeCode;
	}

	public void setMemberTypeCode(String memberTypeCode) {
		this.memberTypeCode = memberTypeCode;
	}

	public Date getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(Date memberDate) {
		this.memberDate = memberDate;
	}

	public String getNPARating() {
		return NPARating;
	}

	public void setNPARating(String nPARating) {
		NPARating = nPARating;
	}

	public String getHomeTelNoISD() {
		return homeTelNoISD;
	}

	public void setHomeTelNoISD(String homeTelNoISD) {
		this.homeTelNoISD = homeTelNoISD;
	}

	public String getAMLRating() {
		return AMLRating;
	}

	public void setAMLRating(String aMLRating) {
		AMLRating = aMLRating;
	}

	public String getHNWCategory() {
		return HNWCategory;
	}

	public void setHNWCategory(String hNWCategory) {
		HNWCategory = hNWCategory;
	}

	public String getHomeTelNo() {
		return homeTelNo;
	}

	public void setHomeTelNo(String homeTelNo) {
		this.homeTelNo = homeTelNo;
	}

	public Date getPhotoIdExpiryDate() {
		return photoIdExpiryDate;
	}

	public void setPhotoIdExpiryDate(Date photoIdExpiryDate) {
		this.photoIdExpiryDate = photoIdExpiryDate;
	}

	public String getPhotoIdProofType() {
		return photoIdProofType;
	}

	public void setPhotoIdProofType(String photoIdProofType) {
		this.photoIdProofType = photoIdProofType;
	}

	public String getNpaRiskRating() {
		return npaRiskRating;
	}

	public void setNpaRiskRating(String npaRiskRating) {
		this.npaRiskRating = npaRiskRating;
	}

	public String getOccupationType() {
		return occupationType;
	}

	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public long getId() {
		return id;
	}

	public String getMbrMaritalStatus() {
		return mbrMaritalStatus;
	}

	public void setMbrMaritalStatus(String mbrMaritalStatus) {
		this.mbrMaritalStatus = mbrMaritalStatus;
	}

	public String getTotalMaleChild() {
		return totalMaleChild;
	}

	public void setTotalMaleChild(String totalMaleChild) {
		this.totalMaleChild = totalMaleChild;
	}

	public String getTotalFemaleChild() {
		return totalFemaleChild;
	}

	public void setTotalFemaleChild(String totalFemaleChild) {
		this.totalFemaleChild = totalFemaleChild;
	}

	public String getTotalFamilyMbr() {
		return totalFamilyMbr;
	}

	public void setTotalFamilyMbr(String totalFamilyMbr) {
		this.totalFamilyMbr = totalFamilyMbr;
	}

	public String getFreezeReason() {
		return freezeReason;
	}

	public void setFreezeReason(String freezeReason) {
		this.freezeReason = freezeReason;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(Integer memberCode) {
		this.memberCode = memberCode;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
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

	public Date getDateOfApplication() {
		return dateOfApplication;
	}

	public void setDateOfApplication(Date dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}

	/*
	 * public String getMemberFName() { return memberFName; }
	 * 
	 * public void setMemberFName(String memberFName) { this.memberFName =
	 * memberFName; }
	 */

	public String getMemberMName() {
		return memberMName;
	}

	public void setMemberMName(String memberMName) {
		this.memberMName = memberMName;
	}

	public String getMemberLName() {
		return memberLName;
	}

	public void setMemberLName(String memberLName) {
		this.memberLName = memberLName;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getMemberCaste() {
		return memberCaste;
	}

	public void setMemberCaste(String caste) {
		this.memberCaste = caste;
	}

	public String getSubCaste() {
		return subCaste;
	}

	public void setSubCaste(String subCaste) {
		this.subCaste = subCaste;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public Date getMemberDOB() {
		return memberDOB;
	}

	public void setMemberDOB(Date memberDOB) {
		this.memberDOB = memberDOB;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getResAddProofID() {
		return resAddProofID;
	}

	public void setResAddProofID(String resAddProofID) {
		this.resAddProofID = resAddProofID;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getRefDocNo() {
		return refDocNo;
	}

	public void setRefDocNo(String refDocNo) {
		this.refDocNo = refDocNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getResidentialStatus() {
		return residentialStatus;
	}

	public void setResidentialStatus(String residentialStatus) {
		this.residentialStatus = residentialStatus;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getMunicipalityCode() {
		return municipalityCode;
	}

	public void setMunicipalityCode(String municipalityCode) {
		this.municipalityCode = municipalityCode;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	public String getIsdtelephoneNo() {
		return isdtelephoneNo;
	}

	public void setIsdtelephoneNo(String isdtelephoneNo) {
		this.isdtelephoneNo = isdtelephoneNo;
	}

	public String getIsdmobileNo1() {
		return isdmobileNo1;
	}

	public void setIsdmobileNo1(String isdmobileNo1) {
		this.isdmobileNo1 = isdmobileNo1;
	}

	public String getMobileNo1() {
		return mobileNo1;
	}

	public void setMobileNo1(String mobileNo1) {
		this.mobileNo1 = mobileNo1;
	}

	public String getIsdmobileNo2() {
		return isdmobileNo2;
	}

	public void setIsdmobileNo2(String isdmobileNo2) {
		this.isdmobileNo2 = isdmobileNo2;
	}

	public String getMobileNo2() {
		return mobileNo2;
	}

	public void setMobileNo2(String mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getIsdfax() {
		return isdfax;
	}

	public void setIsdfax(String isdfax) {
		this.isdfax = isdfax;
	}

	public String getIntroducerCustNo() {
		return introducerCustNo;
	}

	public void setIntroducerCustNo(String introducerCustNo) {
		this.introducerCustNo = introducerCustNo;
	}

	public String getIntroducerCustName() {
		return introducerCustName;
	}

	public void setIntroducerCustName(String introducerCustName) {
		this.introducerCustName = introducerCustName;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public int getNoOfBranches() {
		return noOfBranches;
	}

	public void setNoOfBranches(int noOfBranches) {
		this.noOfBranches = noOfBranches;
	}

	public Date getCommencementDate() {
		return commencementDate;
	}

	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}

	public String getPlaceOfEstablishment() {
		return placeOfEstablishment;
	}

	public void setPlaceOfEstablishment(String placeOfEstablishment) {
		this.placeOfEstablishment = placeOfEstablishment;
	}

	public Date getDateOfEstablishment() {
		return dateOfEstablishment;
	}

	public void setDateOfEstablishment(Date dateOfEstablishment) {
		this.dateOfEstablishment = dateOfEstablishment;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getTtlMaleChild() {
		return ttlMaleChild;
	}

	public void setTtlMaleChild(Integer ttlMaleChild) {
		this.ttlMaleChild = ttlMaleChild;
	}

	public Integer getTtlfemaleChild() {
		return ttlfemaleChild;
	}

	public void setTtlfemaleChild(Integer ttlfemaleChild) {
		this.ttlfemaleChild = ttlfemaleChild;
	}

	public Integer getTtlFamilyMbr() {
		return ttlFamilyMbr;
	}

	public void setTtlFamilyMbr(Integer ttlFamilyMbr) {
		this.ttlFamilyMbr = ttlFamilyMbr;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getFreezeDate() {
		return freezeDate;
	}

	public void setFreezeDate(Date freezeDate) {
		this.freezeDate = freezeDate;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(String serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getCustomerBranch() {
		return customerBranch;
	}

	public void setCustomerBranch(Integer customerBranch) {
		this.customerBranch = customerBranch;
	}

	public String getDematId() {
		return dematId;
	}

	public void setDematId(String dematId) {
		this.dematId = dematId;
	}

	public String getDematAccountNo() {
		return dematAccountNo;
	}

	public void setDematAccountNo(String dematAccountNo) {
		this.dematAccountNo = dematAccountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankIFSCCode() {
		return bankIFSCCode;
	}

	public void setBankIFSCCode(String bankIFSCCode) {
		this.bankIFSCCode = bankIFSCCode;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getBusinessOperationCountry() {
		return businessOperationCountry;
	}

	public void setBusinessOperationCountry(String businessOperationCountry) {
		this.businessOperationCountry = businessOperationCountry;
	}

	public Integer getOperationYears() {
		return operationYears;
	}

	public void setOperationYears(Integer operationYears) {
		this.operationYears = operationYears;
	}

	public String getTaxResidenceStatus() {
		return taxResidenceStatus;
	}

	public void setTaxResidenceStatus(String taxResidenceStatus) {
		this.taxResidenceStatus = taxResidenceStatus;
	}

	public String getTaxIdNo() {
		return taxIdNo;
	}

	public void setTaxIdNo(String taxIdNo) {
		this.taxIdNo = taxIdNo;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public Date getGstRegDate() {
		return gstRegDate;
	}

	public void setGstRegDate(Date gstRegDate) {
		this.gstRegDate = gstRegDate;
	}

	public String getVatRegNo() {
		return vatRegNo;
	}

	public void setVatRegNo(String vatRegNo) {
		this.vatRegNo = vatRegNo;
	}

	public Date getVatRegDate() {
		return vatRegDate;
	}

	public void setVatRegDate(Date vatRegDate) {
		this.vatRegDate = vatRegDate;
	}

	public String getSalesTaxRegNo() {
		return salesTaxRegNo;
	}

	public void setSalesTaxRegNo(String salesTaxRegNo) {
		this.salesTaxRegNo = salesTaxRegNo;
	}

	public Date getSalesTaxRegDate() {
		return salesTaxRegDate;
	}

	public void setSalesTaxRegDate(Date salesTaxRegDate) {
		this.salesTaxRegDate = salesTaxRegDate;
	}

	public String getIeCode() {
		return ieCode;
	}

	public void setIeCode(String ieCode) {
		this.ieCode = ieCode;
	}

	public Date getIeCodeRegDate() {
		return ieCodeRegDate;
	}

	public void setIeCodeRegDate(Date ieCodeRegDate) {
		this.ieCodeRegDate = ieCodeRegDate;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getAnnualTurnover() {
		return annualTurnover;
	}

	public void setAnnualTurnover(String annualTurnover) {
		this.annualTurnover = annualTurnover;
	}

	public String getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(String noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public String getRiskRating() {
		return riskRating;
	}

	public void setRiskRating(String riskRating) {
		this.riskRating = riskRating;
	}

	public String getAffiliatedEntityYN() {
		return affiliatedEntityYN;
	}

	public void setAffiliatedEntityYN(String affiliatedEntityYN) {
		this.affiliatedEntityYN = affiliatedEntityYN;
	}

	public String getAffiliationType() {
		return affiliationType;
	}

	public void setAffiliationType(String affiliationType) {
		this.affiliationType = affiliationType;
	}

	public String getOperationCountry() {
		return operationCountry;
	}

	public void setOperationCountry(String operationCountry) {
		this.operationCountry = operationCountry;
	}

	public String getCustReason() {
		return custReason;
	}

	public void setCustReason(String custReason) {
		this.custReason = custReason;
	}

	public String getSpecialInstruct1() {
		return specialInstruct1;
	}

	public void setSpecialInstruct1(String specialInstruct1) {
		this.specialInstruct1 = specialInstruct1;
	}

	public String getSpecialInstruct2() {
		return specialInstruct2;
	}

	public void setSpecialInstruct2(String specialInstruct2) {
		this.specialInstruct2 = specialInstruct2;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocDetails() {
		return docDetails;
	}

	public void setDocDetails(String docDetails) {
		this.docDetails = docDetails;
	}

	public String getProprietorCustId() {
		return proprietorCustId;
	}

	public void setProprietorCustId(String proprietorCustId) {
		this.proprietorCustId = proprietorCustId;
	}

	public String getProprietorCustName() {
		return proprietorCustName;
	}

	public void setProprietorCustName(String proprietorCustName) {
		this.proprietorCustName = proprietorCustName;
	}

	public String getProprietorAddress() {
		return proprietorAddress;
	}

	public void setProprietorAddress(String proprietorAddress) {
		this.proprietorAddress = proprietorAddress;
	}

	public String getAssociationType() {
		return associationType;
	}

	public void setAssociationType(String associationType) {
		this.associationType = associationType;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getTel1CountryCode() {
		return tel1CountryCode;
	}

	public void setTel1CountryCode(String tel1CountryCode) {
		this.tel1CountryCode = tel1CountryCode;
	}

	public String getTel2CountryCode() {
		return tel2CountryCode;
	}

	public void setTel2CountryCode(String tel2CountryCode) {
		this.tel2CountryCode = tel2CountryCode;
	}

	public String getFaxCountryCode() {
		return faxCountryCode;
	}

	public void setFaxCountryCode(String faxCountryCode) {
		this.faxCountryCode = faxCountryCode;
	}

	public String getMobile1CountryCode() {
		return mobile1CountryCode;
	}

	public void setMobile1CountryCode(String mobile1CountryCode) {
		this.mobile1CountryCode = mobile1CountryCode;
	}

	public String getMobile2CountryCode() {
		return mobile2CountryCode;
	}

	public void setMobile2CountryCode(String mobile2CountryCode) {
		this.mobile2CountryCode = mobile2CountryCode;
	}

	public String getMlProprietorDetails() {
		return mlProprietorDetails;
	}

	public void setMlProprietorDetails(String mlProprietorDetails) {
		this.mlProprietorDetails = mlProprietorDetails;
	}

	public String getMlKycDetails() {
		return mlKycDetails;
	}

	public void setMlKycDetails(String mlKycDetails) {
		this.mlKycDetails = mlKycDetails;
	}

	public String getSubBusinessType() {
		return subBusinessType;
	}

	public void setSubBusinessType(String subBusinessType) {
		this.subBusinessType = subBusinessType;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getProofType() {
		return proofType;
	}

	public void setProofType(String proofType) {
		this.proofType = proofType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public String getNameAsInDocument() {
		return nameAsInDocument;
	}

	public void setNameAsInDocument(String nameAsInDocument) {
		this.nameAsInDocument = nameAsInDocument;
	}

	public Date getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}

	public byte[] getKycDoc() {
		return kycDoc;
	}

	public void setKycDoc(byte[] kycDoc) {
		this.kycDoc = kycDoc;
	}

	public MultipartFile getDoc() {
		return doc;
	}

	public void setDoc(MultipartFile doc) {
		this.doc = doc;
	}

	public String getMlBusinessCommunDetails() {
		return mlBusinessCommunDetails;
	}

	public void setMlBusinessCommunDetails(String mlBusinessCommunDetails) {
		this.mlBusinessCommunDetails = mlBusinessCommunDetails;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressDocId() {
		return addressDocId;
	}

	public void setAddressDocId(String addressDocId) {
		this.addressDocId = addressDocId;
	}

	public String getNameTitle() {
		return nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	public Integer getResidentYN() {
		return residentYN;
	}

	public void setResidentYN(Integer residentYN) {
		this.residentYN = residentYN;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getFrzReasonCd() {
		return frzReasonCd;
	}

	public void setFrzReasonCd(String frzReasonCd) {
		this.frzReasonCd = frzReasonCd;
	}

	public Integer getPepYN() {
		return pepYN;
	}

	public void setPepYN(Integer pepYN) {
		this.pepYN = pepYN;
	}

	public String getRelOff() {
		return relOff;
	}

	public void setRelOff(String relOff) {
		this.relOff = relOff;
	}

	public Integer getStaffYN() {
		return staffYN;
	}

	public void setStaffYN(Integer staffYN) {
		this.staffYN = staffYN;
	}

	public Date getTdsFrm15SubDt() {
		return tdsFrm15SubDt;
	}

	public void setTdsFrm15SubDt(Date tdsFrm15SubDt) {
		this.tdsFrm15SubDt = tdsFrm15SubDt;
	}

	public Double getIntProvision() {
		return intProvision;
	}

	public void setIntProvision(Double intProvision) {
		this.intProvision = intProvision;
	}

	public Integer getTdsYN() {
		return tdsYN;
	}

	public void setTdsYN(Integer tdsYN) {
		this.tdsYN = tdsYN;
	}

	public Double getTdsPercentage() {
		return tdsPercentage;
	}

	public void setTdsPercentage(Double tdsPercentage) {
		this.tdsPercentage = tdsPercentage;
	}

	public String getTdsReasonCd() {
		return tdsReasonCd;
	}

	public void setTdsReasonCd(String tdsReasonCd) {
		this.tdsReasonCd = tdsReasonCd;
	}

	/*
	 * public Date getLastModifiedDate() { return lastModifiedDate; }
	 * 
	 * public void setLastModifiedDate(Date lastModifiedDate) {
	 * this.lastModifiedDate = lastModifiedDate; }
	 */

	public Integer getConCurChkYN() {
		return conCurChkYN;
	}

	public void setConCurChkYN(Integer conCurChkYN) {
		this.conCurChkYN = conCurChkYN;
	}

	public Integer getUaeYN() {
		return uaeYN;
	}

	public void setUaeYN(Integer uaeYN) {
		this.uaeYN = uaeYN;
	}

	public String getPrimaryKeyData() {
		return primaryKeyData;
	}

	public void setPrimaryKeyData(String primaryKeyData) {
		this.primaryKeyData = primaryKeyData;
	}

	public Integer getIntroducerConfirmedYN() {
		return introducerConfirmedYN;
	}

	public void setIntroducerConfirmedYN(Integer introducerConfirmedYN) {
		this.introducerConfirmedYN = introducerConfirmedYN;
	}

	public Integer getKYCAvailableYN() {
		return KYCAvailableYN;
	}

	public void setKYCAvailableYN(Integer kYCAvailableYN) {
		KYCAvailableYN = kYCAvailableYN;
	}

	public Integer getForm60YN() {
		return form60YN;
	}

	public void setForm60YN(Integer form60yn) {
		form60YN = form60yn;
	}

	public Integer getOwnershipPercentage() {
		return ownershipPercentage;
	}

	public void setOwnershipPercentage(Integer ownershipPercentage) {
		this.ownershipPercentage = ownershipPercentage;
	}

	public List<Object> getCustObject() {
		return CustObject;
	}

	public void setCustObject(List<Object> custObject) {
		CustObject = custObject;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getFatherSpouseName() {
		return fatherSpouseName;
	}

	public void setFatherSpouseName(String fatherSpouseName) {
		this.fatherSpouseName = fatherSpouseName;
	}

	public Date getKycExpiryDate() {
		return kycExpiryDate;
	}

	public void setKycExpiryDate(Date kycExpiryDate) {
		this.kycExpiryDate = kycExpiryDate;
	}

}
