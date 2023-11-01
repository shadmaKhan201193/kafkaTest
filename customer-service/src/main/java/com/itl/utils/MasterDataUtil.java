package com.itl.utils;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.itl.domain.entities.custservice.CustomerDetails;
import com.itl.web.dto.CasteVO;
import com.itl.web.dto.CountryVO;
import com.itl.web.dto.CustomerVO;
import com.itl.web.dto.GenderVO;
import com.itl.web.dto.MaritalStatusVO;
import com.itl.web.dto.OccupationVO;
import com.itl.web.dto.PrefCategoryVO;
import com.itl.web.dto.QualificationVO;
import com.itl.web.dto.ReligionVO;
import com.itl.web.dto.ResidentialStatusVO;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "requestmapping")
public class MasterDataUtil {
	private RestTemplate restTemplate = new RestTemplate();

	private static final Logger logger = LoggerFactory.getLogger(MasterDataUtil.class);
	private String mastersContextPath = ""; // "/Masters";

	private String masterbaseurl;
	
	private String countryid;
	private String countryname;
	private String genderid;
	private String gendername;
	
	private String maritalid;
	private String maritalname;
	private String occupationid;
	private String occupationname;
	
	private String casteid;
	private String castename;
	private String prefCategoryid;
	private String prefCategoryname;
	
	private String qualificationid;
	private String qualificationname;
	private String religionid;
	private String religionname;
	private String residentialid;
	private String residentialname;
	
	
	
	/*
	 * private String getMasterBaseURL() { return
	 * "http://172.21.0.61:8989/masterService/"; }
	 */

	public String getMastersContextPath() {
		return mastersContextPath;
	}

	public void setMastersContextPath(String mastersContextPath) {
		this.mastersContextPath = mastersContextPath;
	}

	public String getMasterbaseurl() {
		return masterbaseurl;
	}

	public void setMasterbaseurl(String masterbaseurl) {
		this.masterbaseurl = masterbaseurl;
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getGenderid() {
		return genderid;
	}

	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}

	public String getGendername() {
		return gendername;
	}

	public void setGendername(String gendername) {
		this.gendername = gendername;
	}

	public String getMaritalid() {
		return maritalid;
	}

	public void setMaritalid(String maritalid) {
		this.maritalid = maritalid;
	}

	public String getMaritalname() {
		return maritalname;
	}

	public void setMaritalname(String maritalname) {
		this.maritalname = maritalname;
	}

	public String getOccupationid() {
		return occupationid;
	}

	public void setOccupationid(String occupationid) {
		this.occupationid = occupationid;
	}

	public String getOccupationname() {
		return occupationname;
	}

	public void setOccupationname(String occupationname) {
		this.occupationname = occupationname;
	}

	public String getCasteid() {
		return casteid;
	}

	public void setCasteid(String casteid) {
		this.casteid = casteid;
	}

	public String getCastename() {
		return castename;
	}

	public void setCastename(String castename) {
		this.castename = castename;
	}

	public String getPrefCategoryid() {
		return prefCategoryid;
	}

	public void setPrefCategoryid(String prefCategoryid) {
		this.prefCategoryid = prefCategoryid;
	}

	public String getPrefCategoryname() {
		return prefCategoryname;
	}

	public void setPrefCategoryname(String prefCategoryname) {
		this.prefCategoryname = prefCategoryname;
	}

	public String getQualificationid() {
		return qualificationid;
	}

	public void setQualificationid(String qualificationid) {
		this.qualificationid = qualificationid;
	}

	public String getQualificationname() {
		return qualificationname;
	}

	public void setQualificationname(String qualificationname) {
		this.qualificationname = qualificationname;
	}

	public String getReligionid() {
		return religionid;
	}

	public void setReligionid(String religionid) {
		this.religionid = religionid;
	}

	public String getReligionname() {
		return religionname;
	}

	public void setReligionname(String religionname) {
		this.religionname = religionname;
	}

	public String getResidentialid() {
		return residentialid;
	}

	public void setResidentialid(String residentialid) {
		this.residentialid = residentialid;
	}

	public String getResidentialname() {
		return residentialname;
	}

	public void setResidentialname(String residentialname) {
		this.residentialname = residentialname;
	}

	int attempts = 1;

	// @Retry(name = "retryService", fallbackMethod = "getDefaultMastercountry")
	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterCountryId(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
			logger.info("In retry testing");
			logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getCountryid());
			ResponseEntity<CountryVO> resp = restTemplate.exchange(
					getMasterbaseurl() + mastersContextPath + getCountryid(), HttpMethod.GET, null, CountryVO.class,
					custDetails.getCitizenship());

			CountryVO country = resp.getBody();

			if (null != country) {
				logger.info("Inside country if block");
				customer.setCitizenship("" + custDetails.getCitizenship() + ":" + country.getCountryDisplayName());
			} else {
				logger.info("Inside country else block");
				customer.setCitizenship("" + custDetails.getCitizenship() + ":");
			}
		return customer.getCitizenship();
	}

	// ------------------------------------------gende-------------------

	// @Retry(name = "retryService", fallbackMethod = "getDefaultMastergender")
	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterGenderId(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getGenderid());
			ResponseEntity<GenderVO> res = restTemplate.exchange(
					getMasterbaseurl() + mastersContextPath + getGenderid(), HttpMethod.GET, null, GenderVO.class,
					custDetails.getGender());

			GenderVO gender = res.getBody();
			if (null != gender) {
				logger.info("Inside gender if block");
				customer.setGender("" + custDetails.getGender() + ":" + gender.getGenderDisplayName());
			} else {
				logger.info("Inside gender else block");
				customer.setGender("" + custDetails.getGender() + ":");
			}
		
		return customer.getGender();

	}

	// -----------------------------MaritalStatus-----------

	// @Retry(name = "retryService", fallbackMethod =
	// "getDefaultMastermaritalStatus")
	@Retryable(value = { ResourceAccessException.class, ConnectException.class}, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMastermaritalStatusId(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getMaritalid());
			ResponseEntity<MaritalStatusVO> respmaritalStatus = restTemplate.exchange(
					getMasterbaseurl() + mastersContextPath + getMaritalid(), HttpMethod.GET, null,
					MaritalStatusVO.class, custDetails.getMaritalStatus());

			MaritalStatusVO maritalStatus = respmaritalStatus.getBody();
			if (null != maritalStatus) {
				logger.info("Inside maritalStatus if block");
				customer.setMaritalStatus(
						"" + custDetails.getMaritalStatus() + ":" + maritalStatus.getMaritalStatusDisplayName());
			} else {
				logger.info("Inside maritalStatus else block");
				customer.setMaritalStatus("" + custDetails.getMaritalStatus() + ":");
			}		
		return customer.getMaritalStatus();

	}
	// -----------------------------occupation-----
	// @Retry(name = "retryService", fallbackMethod = "getDefaultMasteroccupation")
	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterOccupationId(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getOccupationid());
			ResponseEntity<OccupationVO> respOccupation = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getOccupationid(), HttpMethod.GET, null,
					OccupationVO.class, custDetails.getOccupation());

			OccupationVO occupation = respOccupation.getBody();
			if (null != occupation) {
				logger.info("Inside occupation if block");
				customer.setOccupation("" + custDetails.getOccupation() + ":" + occupation.getOccupationDisplayName());
			} else {
				logger.info("Inside occupation else block");
				customer.setOccupation("" + custDetails.getOccupation() + ":");
			}
		return customer.getOccupation();

	}

	// ---------------------------caste------------

	// @Retry(name = "retryService", fallbackMethod = "getDefaultMastercaste")
	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterCasteId(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
			logger.info("Inside country if block " + attempts);
			logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getCasteid());
			ResponseEntity<CasteVO> respcaste = restTemplate.exchange(
					getMasterbaseurl() + mastersContextPath + getCasteid(), HttpMethod.GET, null, CasteVO.class,
					custDetails.getCaste());

			CasteVO caste = respcaste.getBody();
			if (null != caste) {
				logger.info("Inside caste if block");
				customer.setCaste("" + custDetails.getCaste() + ":" + caste.getCasteDisplayName());
			} else {
				logger.info("Inside caste else block");
				customer.setCaste("" + custDetails.getCaste() + ":");
			}
		return customer.getCaste();

	}

	// -------------------------prefCategory--------

	// @Retry(name = "retryService", fallbackMethod =
	// "getDefaultMasterprefCategory")
	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterPrefCategoryId(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getPrefCategoryid());
			ResponseEntity<PrefCategoryVO> respprefCategory = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getPrefCategoryid(), HttpMethod.GET, null,
					PrefCategoryVO.class, custDetails.getPreferenceCategory());

			PrefCategoryVO prefCategory = respprefCategory.getBody();
			if (null != prefCategory) {
				logger.info("Inside prefCategory if block");
				customer.setPreferenceCategory(
						"" + custDetails.getPreferenceCategory() + ":" + prefCategory.getPrefCategoryDisplayName());
			} else {
				logger.info("Inside prefCategory else block");
				customer.setPreferenceCategory("" + custDetails.getPreferenceCategory() + ":");
			}
		return customer.getPreferenceCategory();

	}

	// ------------------------qualification--------

	// @Retry(name = "retryService", fallbackMethod =
	// "getDefaultMasterqualification")
	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterQualificationId(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getQualificationid());
			ResponseEntity<QualificationVO> resppQualification = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getQualificationid(), HttpMethod.GET, null,
					QualificationVO.class, custDetails.getQualification());

			QualificationVO qualification = resppQualification.getBody();
			if (null != qualification) {
				logger.info("Inside qualification if block");
				customer.setQualification(
						"" + custDetails.getQualification() + ":" + qualification.getQualificationDisplayName());
			} else {
				logger.info("Inside qualification else block");
				customer.setQualification("" + custDetails.getQualification() + ":");
			}
		return customer.getQualification();

	}

	// --------------------religion---------------

	// @Retry(name = "retryService")
	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterReligionId(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getReligionid());
			ResponseEntity<ReligionVO> resppReligion = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getReligionid(), HttpMethod.GET, null,
					ReligionVO.class, custDetails.getReligion());

			ReligionVO religion = resppReligion.getBody();
			if (null != religion) {
				logger.info("Inside religion if block");
				customer.setReligion("" + custDetails.getReligion() + ":" + religion.getReligionDisplayName());
			} else {
				logger.info("Inside religion else block");
				customer.setReligion("" + custDetails.getReligion() + ":");
			}
		return customer.getReligion();

	}

	// ------------------------residentialStatus--------------


	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterResidentialStatusId(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getResidentialid());
			ResponseEntity<ResidentialStatusVO> respResidentialStatus = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getResidentialid(), HttpMethod.GET, null,
					ResidentialStatusVO.class, custDetails.getResidentialStatus());

			ResidentialStatusVO residentialStatus = respResidentialStatus.getBody();
			if (null != residentialStatus) {
				logger.info("Inside residentialStatus if block");
				customer.setResidentialStatus("" + custDetails.getResidentialStatus() + ":"
						+ residentialStatus.getResidentialStatusDisplayName());
			} else {
				logger.info("Inside residentialStatus else block");
				customer.setResidentialStatus("" + custDetails.getResidentialStatus() + ":");
			}
		return customer.getResidentialStatus();

	}

	// --------------------countryName-----

	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterCountryName(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getCountryname());
		ResponseEntity<CountryVO> resp = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getCountryname(), HttpMethod.GET, null, CountryVO.class,
				custDetails.getCitizenship());
		CountryVO country = resp.getBody();
		if (null != country) {
			logger.info("Inside country if block");
			customer.setCitizenship("" + custDetails.getCitizenship() + ":" + country.getCountryDisplayName());
		} else {
			logger.info("Inside country else block");
			customer.setCitizenship("" + custDetails.getCitizenship() + ":");
		}
		return customer.getCitizenship();

	}

	// --------------------------genderName-----

	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterGenderName(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getGendername());
		ResponseEntity<GenderVO> respgender = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getGendername(), HttpMethod.GET, null, GenderVO.class,
				custDetails.getGender());

		GenderVO gender = respgender.getBody();
		if (null != gender) {
			logger.info("Inside gender if block");
			customer.setGender("" + custDetails.getGender() + ":" + gender.getGenderDisplayName());
		} else {
			logger.info("Inside gender else block");
			customer.setGender("" + custDetails.getGender() + ":");
		}
		return customer.getGender();

	}

	// ---------------------maritalStatusName------------

	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterMaritalStatusName(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getMaritalname());
		ResponseEntity<MaritalStatusVO> respmaritalStatus = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getMaritalname(), HttpMethod.GET, null,
				MaritalStatusVO.class, custDetails.getMaritalStatus());

		MaritalStatusVO maritalStatus = respmaritalStatus.getBody();
		if (null != maritalStatus) {
			logger.info("Inside maritalStatus if block");
			customer.setMaritalStatus(
					"" + custDetails.getMaritalStatus() + ":" + maritalStatus.getMaritalStatusDisplayName());
		} else {
			logger.info("Inside maritalStatus else block");
			customer.setMaritalStatus("" + custDetails.getMaritalStatus() + ":");
		}
		return customer.getMaritalStatus();

	}

	// ---------------------occupation/name--------------

	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterOccupationName(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getOccupationname());
		ResponseEntity<OccupationVO> respOccupation = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getOccupationname(), HttpMethod.GET, null,
				OccupationVO.class, custDetails.getOccupation());

		OccupationVO occupation = respOccupation.getBody();
		if (null != occupation) {
			logger.info("Inside occupation if block");
			customer.setOccupation("" + custDetails.getOccupation() + ":" + occupation.getOccupationDisplayName());
		} else {
			logger.info("Inside occupation else block");
			customer.setOccupation("" + custDetails.getOccupation() + ":");
		}
		return customer.getOccupation();

	}

	// -----------------------caste/name

	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterCasteName(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getCastename());
		ResponseEntity<CasteVO> respcaste = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getCastename(), HttpMethod.GET, null, CasteVO.class,
				custDetails.getCaste());

		CasteVO caste = respcaste.getBody();
		if (null != caste) {
			logger.info("Inside caste if block");
			customer.setCaste("" + custDetails.getCaste() + ":" + caste.getCasteDisplayName());
		} else {
			logger.info("Inside caste else block");
			customer.setCaste("" + custDetails.getCaste() + ":");
		}
		return customer.getCaste();

	}

	// ---------------------prefCategory/name

	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterPrefCategoryName(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getPrefCategoryname());
		ResponseEntity<PrefCategoryVO> respprefCategory = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getPrefCategoryname(), HttpMethod.GET, null,
				PrefCategoryVO.class, custDetails.getPreferenceCategory());

		PrefCategoryVO prefCategory = respprefCategory.getBody();
		if (null != prefCategory) {
			logger.info("Inside PrefCategory if block");
			customer.setPreferenceCategory(
					"" + custDetails.getPreferenceCategory() + ":" + prefCategory.getPrefCategoryDisplayName());
		} else {
			logger.info("Inside PrefCategory else block");
			customer.setPreferenceCategory("" + custDetails.getPreferenceCategory() + ":");
		}
		return customer.getPreferenceCategory();

	}

	// -------------------------qualification/name---------

	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterQualificationName(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getQualificationname());
		ResponseEntity<QualificationVO> resppQualification = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getQualificationname(), HttpMethod.GET, null,
				QualificationVO.class, custDetails.getQualification());

		QualificationVO qualification = resppQualification.getBody();
		if (null != qualification) {
			logger.info("Inside Qualification if block");
			customer.setQualification(
					"" + custDetails.getQualification() + ":" + qualification.getQualificationDisplayName());
		} else {
			logger.info("Inside Qualification else block");
			customer.setQualification("" + custDetails.getQualification() + ":");
		}
		return customer.getQualification();

	}

	// -----------------religion/name---------------

	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterReligionName(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getReligionname());
		ResponseEntity<ReligionVO> resppReligion = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getReligionname(), HttpMethod.GET, null,
				ReligionVO.class, custDetails.getReligion());

		ReligionVO religion = resppReligion.getBody();
		if (null != religion) {
			logger.info("Inside Religion if block");
			customer.setReligion("" + custDetails.getReligion() + ":" + religion.getReligionDisplayName());
		} else {
			logger.info("Inside Religion else block");
			customer.setReligion("" + custDetails.getReligion() + ":");
		}
		return customer.getReligion();

	}

	/// --------residentialStatus/name-------------

	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 10000))
	public String getMasterResidentialStatusName(CustomerDetails custDetails) {

		CustomerVO customer = null;
		customer = new CustomerVO();
		logger.info("Calling URL is>>" + getMasterbaseurl() + mastersContextPath + getResidentialname());
		ResponseEntity<ResidentialStatusVO> respResidentialStatus = restTemplate.exchange(
				getMasterbaseurl() + mastersContextPath + getResidentialname(), HttpMethod.GET, null,
				ResidentialStatusVO.class, custDetails.getResidentialStatus());

		ResidentialStatusVO residentialStatus = respResidentialStatus.getBody();
		if (null != residentialStatus) {
			logger.info("Inside residentialStatus if block");
			customer.setResidentialStatus("" + custDetails.getResidentialStatus() + ":"
					+ residentialStatus.getResidentialStatusDisplayName());
		} else {
			logger.info("Inside residentialStatus else block");
			customer.setResidentialStatus("" + custDetails.getResidentialStatus() + ":");
		}
		return customer.getResidentialStatus();

	}
	

}
