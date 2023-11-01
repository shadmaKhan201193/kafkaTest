package com.itl.web.dto;

import java.io.Serializable;

public class CustomerForLoanVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

		private Integer memberCode;

		private String memberFName = "";

		private String memberMName = "";

		//private String memberLName = "";
		
		//private String MemberFullName ="";
		
		private String dateOfBirthStr;
		
		private String mobileNo1 = "";
		private String emailId = "";
		
		private String spouseName = "";
		
		private String fatherSpouseName = "";
		private String memberGender = ""; 
		
		private String kycExpiryDate="";
		
		private String introducerId="";

		public Integer getMemberCode() {
			return memberCode;
		}

		public void setMemberCode(Integer memberCode) {
			this.memberCode = memberCode;
		}

		public String getMemberFName() {
			return memberFName;
		}

		public void setMemberFName(String memberFName) {
			this.memberFName = memberFName;
		}

		public String getMemberMName() {
			return memberMName;
		}

		public void setMemberMName(String memberMName) {
			this.memberMName = memberMName;
		}

		/*
		 * public String getMemberLName() { return memberLName; }
		 * 
		 * public void setMemberLName(String memberLName) { this.memberLName =
		 * memberLName; }
		 */
		
		
		public String getDateOfBirthStr() {
			return dateOfBirthStr;
		}

		public void setDateOfBirthStr(String dateOfBirthStr) {
			this.dateOfBirthStr = dateOfBirthStr;
		}
		/*
		 * public String getMemberFullName() { return MemberFullName; }
		 * 
		 * public void setMemberFullName(String memberFullName) { MemberFullName =
		 * memberFullName; }
		 */
		

		public String getMobileNo1() {
			return mobileNo1;
		}

		public void setMobileNo1(String mobileNo1) {
			this.mobileNo1 = mobileNo1;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
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
		
		


		public String getMemberGender() {
			return memberGender;
		}

		public void setMemberGender(String memberGender) {
			this.memberGender = memberGender;
		}
		
		

		public String getKycExpiryDate() {
			return kycExpiryDate;
		}

		public void setKycExpiryDate(String kycExpiryDate) {
			this.kycExpiryDate = kycExpiryDate;
		}

		
		
		public String getIntroducerId() {
			return introducerId;
		}

		public void setIntroducerId(String introducerId) {
			this.introducerId = introducerId;
		}

		@Override
		public String toString() {
			return "CustomerForLoanVO [memberCode=" + memberCode + ", memberFName=" + memberFName + ", memberMName="
					+ memberMName + ", dateOfBirthStr=" + dateOfBirthStr + ", mobileNo1=" + mobileNo1 + ", emailId="
					+ emailId + ", spouseName=" + spouseName + ", fatherSpouseName=" + fatherSpouseName
					+ ", memberGender=" + memberGender + ", kycExpiryDate=" + kycExpiryDate + ", introducerId="
					+ introducerId + "]";
		}

	

		
		

		
		
		

		

		
		

		
}
