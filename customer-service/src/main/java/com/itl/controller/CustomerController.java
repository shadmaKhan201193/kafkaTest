package com.itl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.itl.domain.entities.custservice.CustomerDetails;
import com.itl.domain.entities.custservice.CustomerIntroducerMst;
import com.itl.domain.entities.custservice.CustomerMst;
//import com.itl.domain.entities.masters.CountryMst;
import com.itl.service.custservice.CustomerService;
import com.itl.utils.MasterDataUtil;
import com.itl.utils.MessagingConfig;
import com.itl.utils.OmniConstants;
import com.itl.web.dto.CustomerVO;
import com.itl.web.dto.D009011VO;


//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

@Component
@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private static final String CUSTOMERSERVICE = "customerService";

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MasterDataUtil masterDataUtil;

	private RestTemplate restTemplate = new RestTemplate();

	private RetryTemplate retryTemplate = new RetryTemplate();

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private TopicExchange exchange;
	

	// @Autowired
	// private LoadBalancerClient loadBalancerClient;

	private String mastersContextPath = ""; // "/Masters";

	private String getMasterBaseURL() {
		return "http://172.21.0.61:8989/masterService/";
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@GetMapping(value = "/idd/{id}", produces = "application/json")
	public CustomerVO getCustomerByIdd(@PathVariable String id) {
		logger.debug("Inside getCustomerByIdd method");
		List<CustomerMst> customerList = customerService.getByCustomerId(id);
		logger.info("After Service call. customerList.size-->"
				+ ((null != customerList && customerList.size() > 0) ? customerList.size() : 0));
		CustomerMst customerMst = null;
		CustomerVO customer = null;
		if (null != customerList) {
			customerMst = customerList.get(0);
			if (null != customerMst) {
				logger.info("Inside if  Condition");
				customer = new CustomerVO();
				customer.setCustomerId(customerMst.getCustomerId());
				customer.setCustomerFullName(customerMst.getCustomerFullName());
				customer.setCustomerFullDisplayName(customerMst.getCustomerFullDisplayName());
				customer.setFirstName(customerMst.getFirstName());
				customer.setLastName(customerMst.getLastName());
				customer.setMiddleName(customerMst.getMiddleName());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String convertedCurrentDate = sdf.format(customerMst.getDateOfBirth());
				customer.setDateOfBirth(convertedCurrentDate);
				customer.setMobileNumber(customerMst.getMobileNumber());
				customer.setEmailId(customerMst.getEmailId());
			}
		}
		logger.info(" Exit from getCustomerByIdd Method");
		return customer;
	}

	
	@GetMapping(value = "/id/{id}", produces = "application/json")
	public CustomerVO getCustomerById(@PathVariable String id) {
		logger.debug("Inside getCustomerById method");
		List<CustomerMst> customerList = customerService.getByCustomerId(id);
		logger.info("After Service call. customerList.size-->"
				+ ((null != customerList && customerList.size() > 0) ? customerList.size() : 0));

		CustomerMst customerMst = null;
		CustomerVO customer = null;
		if (null != customerList) {

			customerMst = customerList.get(0);
			if (null != customerMst) {
				logger.info("Inside if  Condition");
				customer = new CustomerVO();

				customer.setCustomerId(customerMst.getCustomerId());
				customer.setCustomerFullName(customerMst.getCustomerFullName());
				customer.setCustomerFullDisplayName(customerMst.getCustomerFullDisplayName());
				customer.setFirstName(customerMst.getFirstName());
				customer.setLastName(customerMst.getLastName());
				customer.setMiddleName(customerMst.getMiddleName());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String convertedCurrentDate = sdf.format(customerMst.getDateOfBirth());
				customer.setDateOfBirth(convertedCurrentDate);
				customer.setMobileNumber(customerMst.getMobileNumber());
				customer.setEmailId(customerMst.getEmailId());

				List<CustomerDetails> custDetailList = customerMst.getCustomerDetail();
				if (null != custDetailList && custDetailList.size()>0) {
				CustomerDetails custDetails = custDetailList.get(0);
					if (null != custDetails) {
				try {
					logger.info("Inside try-Catch block");

					customer.setCitizenship(masterDataUtil.getMasterCountryId(custDetails));
					customer.setGender(masterDataUtil.getMasterGenderId(custDetails));
					customer.setMaritalStatus(masterDataUtil.getMastermaritalStatusId(custDetails));
					customer.setOccupation(masterDataUtil.getMasterOccupationId(custDetails));
					customer.setCaste(masterDataUtil.getMasterCasteId(custDetails));
					customer.setPreferenceCategory(masterDataUtil.getMasterPrefCategoryId(custDetails));
					customer.setQualification(masterDataUtil.getMasterQualificationId(custDetails));
					customer.setReligion(masterDataUtil.getMasterReligionId(custDetails));
					customer.setResidentialStatus(masterDataUtil.getMasterResidentialStatusId(custDetails));

							customer.setCustomerCategory(custDetails.getCustomerCategory());
							customer.setCustomerType(custDetails.getCustomerType());
							customer.setFatherName(custDetails.getFatherName());
							customer.setHusbandName(custDetails.getHusbandName());
							customer.setMotherName(custDetails.getMotherName());
				
							convertedCurrentDate = sdf.format(custDetails.getKycExpiryDate());

							customer.setKycExpiryDate(convertedCurrentDate);
							if (custDetails.getSelfEmployed()) {
								customer.setSelfEmployed("TRUE");
							} else {
								customer.setSelfEmployed("FALSE");
							}
				} catch (Exception e) {
					e.printStackTrace();
				}
					}
				}
				List<CustomerIntroducerMst> custIntroDetailList = customerMst.getIntroducerDetail();
				if (null != custIntroDetailList && custIntroDetailList.size() > 0) {
					logger.info("Inside custIntroDetailList If block");
					for (CustomerIntroducerMst custIntroDetail : custIntroDetailList) {
						
						customer.setIntroducerId(custIntroDetail.getIntroducerId());
						logger.info("Inside  CustomerDetails If bolck..introducedDate>>"
								+ custIntroDetail.getIntroducedDate());
						if (null != custIntroDetail.getIntroducedDate()) {
							String introducedDate = sdf.format(custIntroDetail.getIntroducedDate());
							customer.setIntroducedDate(introducedDate);
						}
					}
				}
			}
		}
		logger.debug("Exit from getCustomerById method");
		return customer;
	}

	/*
	 * private void getMasterCountry(CustomerDetails custDetails) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

	@GetMapping(value = "/name/{name}", produces = "application/json")
	public CustomerVO getCustomerByName(@PathVariable String name) {
		logger.debug("Inside  getCustomerByName method");
		List<CustomerMst> customerList = customerService.getByCustomerName(name);
		logger.info("After Service call..customerList.size-->"
				+ ((null != customerList && customerList.size() > 0) ? customerList.size() : 0));

		CustomerMst customerMst = null; // customerList.get(0);
		CustomerVO customer = null;
		if (null == customerList) {
			return null;
		} else {
			customerMst = customerList.get(0);
			if (null != customerMst) {
				logger.info("customer : {}",customerMst );
				logger.info("Inside getCustomerByName if block");
				customer = new CustomerVO();

				customer.setCustomerId(customerMst.getCustomerId());
				customer.setCustomerFullName(customerMst.getCustomerFullName());
				customer.setCustomerFullDisplayName(customerMst.getCustomerFullDisplayName());
				customer.setFirstName(customerMst.getFirstName());
				customer.setLastName(customerMst.getLastName());
				customer.setMiddleName(customerMst.getMiddleName());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String convertedCurrentDate = sdf.format(customerMst.getDateOfBirth());
				customer.setDateOfBirth(convertedCurrentDate);
				customer.setMobileNumber(customerMst.getMobileNumber());
				customer.setEmailId(customerMst.getEmailId());

				List<CustomerDetails> custDetailList = customerMst.getCustomerDetail();

				if (null != custDetailList) {
					CustomerDetails custDetails = custDetailList.get(0);
					try {
						logger.info("Inside Exception{try-catch} block");
						customer.setCitizenship(masterDataUtil.getMasterCountryId(custDetails));	
						customer.setGender(masterDataUtil.getMasterGenderId(custDetails));						
						customer.setMaritalStatus(masterDataUtil.getMastermaritalStatusId(custDetails));						
						customer.setOccupation(masterDataUtil.getMasterOccupationId(custDetails));							
						customer.setCaste(masterDataUtil.getMasterCasteId(custDetails));								
						customer.setPreferenceCategory(masterDataUtil.getMasterPrefCategoryId(custDetails));						
						customer.setQualification(masterDataUtil.getMasterQualificationId(custDetails));						
						customer.setReligion(masterDataUtil.getMasterReligionId(custDetails));							
						customer.setResidentialStatus(masterDataUtil.getMasterResidentialStatusId(custDetails));
						
						customer.setCustomerCategory(custDetails.getCustomerCategory());
						customer.setCustomerType(custDetails.getCustomerType());
						customer.setFatherName(custDetails.getFatherName());
						customer.setHusbandName(custDetails.getHusbandName());
						customer.setMotherName(custDetails.getMotherName());
						
						convertedCurrentDate = sdf.format(custDetails.getKycExpiryDate());

						customer.setKycExpiryDate(convertedCurrentDate);
						if (custDetails.getSelfEmployed()) {
							customer.setSelfEmployed("TRUE");
						} else {
							customer.setSelfEmployed("FALSE");
						}
					} catch (Exception e) {
						logger.info("Inside catch  block");
						e.printStackTrace();
					}
				}
				
				List<CustomerIntroducerMst> custIntroDetailList = customerMst.getIntroducerDetail();
				if (null != custIntroDetailList && custIntroDetailList.size() > 0) {
					logger.info("Inside custIntroDetailList If block");
					for (CustomerIntroducerMst custIntroDetail : custIntroDetailList) {
						
						customer.setIntroducerId(custIntroDetail.getIntroducerId());
						logger.info("Inside  CustomerDetails If bolck..introducedDate>>"
								+ custIntroDetail.getIntroducedDate());
						if (null != custIntroDetail.getIntroducedDate()) {
							String introducedDate = sdf.format(custIntroDetail.getIntroducedDate());
							customer.setIntroducedDate(introducedDate);
						}
					}
				}
			}
		}
		logger.debug("Exit from  getCustomerByName method");
		return customer;
	}

	@GetMapping(value = "/list/authorized", produces = "application/json")
	public List<CustomerVO> getCustomerByAuthorizedStatus() {
		logger.debug("Inside  getCustomerByAuthorizedStatus method");

		List<CustomerMst> customerList = customerService.getByAuthStatus(OmniConstants.AUTH_AUTHORIZED);
		logger.info("After Service call");

		List<CustomerVO> custVOList = new ArrayList<CustomerVO>();
		if (null == customerList) {
			logger.info("Inside If bolck");
			return custVOList;
		} else {
			logger.info("Inside else block");
			for (CustomerMst customer : customerList) {
				CustomerVO custVO = new CustomerVO();
				Mapper mapper = new DozerBeanMapper();
				mapper.map(customer, custVO);

				List<CustomerDetails> custDetailList = customer.getCustomerDetail();
				if (null != custDetailList && custDetailList.size() > 0) {
					logger.info("Inside  CustomerDetails If bolck");
					for (CustomerDetails custDetail : custDetailList) {
						custVO.setCaste(custDetail.getCaste());
						custVO.setCitizenship(custDetail.getCitizenship());
						custVO.setCustomerCategory(custDetail.getCustomerCategory());
						custVO.setCustomerType(custDetail.getCustomerType());
						custVO.setFatherName(custDetail.getFatherName());
						custVO.setHusbandName(custDetail.getHusbandName());
						custVO.setMotherName(custDetail.getMotherName());
						custVO.setGender(custDetail.getGender());
						logger.info(
								"Inside  CustomerDetails If bolck..kycExpiryDate>>" + custDetail.getKycExpiryDate());
						if (null != custDetail.getKycExpiryDate()) {
							String kycExpiryDate = sdf.format(custDetail.getKycExpiryDate());
							custVO.setKycExpiryDate(kycExpiryDate);
						}
						custVO.setMaritalStatus(custDetail.getMaritalStatus());
						custVO.setOccupation(custDetail.getOccupation());
						custVO.setPreferenceCategory(custDetail.getPreferenceCategory());
						custVO.setQualification(custDetail.getQualification());
						custVO.setReligion(custDetail.getReligion());
						custVO.setResidentialStatus(custDetail.getResidentialStatus());
						custVO.setAuthStatus(custDetail.getAuthStatus());
						custVO.setIsActive(custDetail.getIsActive().toString());
						custVO.setIsDeleted(String.valueOf(custDetail.getIsDeleted()));
						logger.info("Inside  CustomerDetails If bolck..kycExpiryDate>>END");
					}
				}

				List<CustomerIntroducerMst> custIntroDetailList = customer.getIntroducerDetail();
				if (null != custIntroDetailList && custIntroDetailList.size() > 0) {
					logger.info("Inside custIntroDetailList If block");
					for (CustomerIntroducerMst custIntroDetail : custIntroDetailList) {
						custVO.setIntroducerId(custIntroDetail.getIntroducerId());
						logger.info("Inside  CustomerDetails If bolck..introducedDate>>"
								+ custIntroDetail.getIntroducedDate());
						if (null != custIntroDetail.getIntroducedDate()) {
							String introducedDate = sdf.format(custIntroDetail.getIntroducedDate());
							custVO.setKycExpiryDate(introducedDate);
						}
						// custVO.setAuthStatus(custIntroDetail.getAuthStatus());
						// custVO.setIsActive(Integer.toString(custIntroDetail.getIsActive()));
						// custVO.setIsDeleted(String.valueOf(custIntroDetail.getIsDeleted()));
					}
				}
				logger.info("Inside custIntroDetailList If block");
				custVOList.add(custVO);
			}
		}
		logger.debug("Exit from getCustomerByAuthorizedStatus method ");
		return custVOList;
	}

	@GetMapping(value = "/list/rejected", produces = "application/json")
	public List<CustomerVO> getCustomerByRejectedStatus() {
		logger.debug("Inside  getCustomerByAuthorizedStatus method");
		List<CustomerMst> customerList = customerService.getByAuthStatus(OmniConstants.AUTH_REJECTED);
		logger.info("After Service call");
		List<CustomerVO> custVOList = new ArrayList<CustomerVO>();
		if (null == customerList) {
			logger.info("Inside If block");
			return custVOList;
		} else {
			logger.info("Inside else block");
			for (CustomerMst customer : customerList) {
				CustomerVO custVO = new CustomerVO();
				Mapper mapper = new DozerBeanMapper();
				mapper.map(customer, custVO);

				List<CustomerDetails> custDetailList = customer.getCustomerDetail();
				logger.debug("Inside  custDetailList method");

				if (null != custDetailList && custDetailList.size() > 0) {
					logger.debug("Inside  if block ");

					for (CustomerDetails custDetail : custDetailList) {
						custVO.setCaste(custDetail.getCaste());
						custVO.setCitizenship(custDetail.getCitizenship());
						custVO.setCustomerCategory(custDetail.getCustomerCategory());
						custVO.setCustomerType(custDetail.getCustomerType());
						custVO.setFatherName(custDetail.getFatherName());
						custVO.setHusbandName(custDetail.getHusbandName());
						custVO.setMotherName(custDetail.getMotherName());
						custVO.setGender(custDetail.getGender());
						String kycExpiryDate = sdf.format(custDetail.getKycExpiryDate());
						custVO.setKycExpiryDate(kycExpiryDate);
						custVO.setMaritalStatus(custDetail.getMaritalStatus());
						custVO.setOccupation(custDetail.getOccupation());
						custVO.setPreferenceCategory(custDetail.getPreferenceCategory());
						custVO.setQualification(custDetail.getQualification());
						custVO.setReligion(custDetail.getReligion());
						custVO.setResidentialStatus(custDetail.getResidentialStatus());
//						custVO.setAuthStatus(custDetail.getAuthStatus());
//						custVO.setIsActive(Integer.toString(custDetail.getIsActive()));				
//						custVO.setIsDeleted(String.valueOf(custDetail.getIsDeleted()));
					}
				}
				List<CustomerIntroducerMst> custIntroDetailList = customer.getIntroducerDetail();
				logger.debug("Inside  custIntroDetailList method");
				if (null != custIntroDetailList && custIntroDetailList.size() > 0) {
					for (CustomerIntroducerMst custIntroDetail : custIntroDetailList) {
						custVO.setIntroducerId(custIntroDetail.getIntroducerId());
						String introducedDate = sdf.format(custIntroDetail.getIntroducedDate());
						custVO.setKycExpiryDate(introducedDate);
//						custVO.setAuthStatus(custIntroDetail.getAuthStatus());
//						custVO.setIsActive(Integer.toString(custIntroDetail.getIsActive()));				
//						custVO.setIsDeleted(String.valueOf(custIntroDetail.getIsDeleted()));
					}
				}
				logger.debug("Exit from custIntroDetailList method");
				custVOList.add(custVO);
			}
		}
		logger.debug("Exit from  getCustomerByAuthorizedStatus method");
		return custVOList;
	}

	@GetMapping(value = "/list/pending", produces = "application/json")
	public List<CustomerVO> getCustomerByPendingStatus() {
		logger.debug("Inside  getCustomerByPendingStatus method");
		List<CustomerMst> customerList = customerService.getByAuthStatus(OmniConstants.AUTH_PENDING);
		logger.info("After Service call");
		List<CustomerVO> custVOList = new ArrayList<CustomerVO>();
		if (null == customerList) {
			return custVOList;
		} else {
			for (CustomerMst customer : customerList) {
				CustomerVO custVO = new CustomerVO();
				Mapper mapper = new DozerBeanMapper();
				mapper.map(customer, custVO);
				// custVO.setAuthStatus(custDetail.getAuthStatus());
				// custVO.setIsActive(Integer.toString(custDetail.getIsActive()));
				// custVO.setIsDeleted(String.valueOf(custDetail.getIsDeleted()));
				List<CustomerDetails> custDetailList = customer.getCustomerDetail();
				if (null != custDetailList && custDetailList.size() > 0) {
					for (CustomerDetails custDetail : custDetailList) {
						custVO.setCaste(custDetail.getCaste());
						custVO.setCitizenship(custDetail.getCitizenship());
						custVO.setCustomerCategory(custDetail.getCustomerCategory());
						custVO.setCustomerType(custDetail.getCustomerType());
						custVO.setFatherName(custDetail.getFatherName());
						custVO.setHusbandName(custDetail.getHusbandName());
						custVO.setMotherName(custDetail.getMotherName());
						custVO.setGender(custDetail.getGender());
						String kycExpiryDate = sdf.format(custDetail.getKycExpiryDate());
						custVO.setKycExpiryDate(kycExpiryDate);
						custVO.setMaritalStatus(custDetail.getMaritalStatus());
						custVO.setOccupation(custDetail.getOccupation());
						custVO.setPreferenceCategory(custDetail.getPreferenceCategory());
						custVO.setQualification(custDetail.getQualification());
						custVO.setReligion(custDetail.getReligion());
						custVO.setResidentialStatus(custDetail.getResidentialStatus());
					}
				}
				List<CustomerIntroducerMst> custIntroDetailList = customer.getIntroducerDetail();
				logger.debug("Inside  custIntroDetailList ");
				if (null != custIntroDetailList && custIntroDetailList.size() > 0) {
					for (CustomerIntroducerMst custIntroDetail : custIntroDetailList) {
						custVO.setIntroducerId(custIntroDetail.getIntroducerId());
						String introducedDate = sdf.format(custIntroDetail.getIntroducedDate());
						custVO.setKycExpiryDate(introducedDate);
//						custVO.setAuthStatus(custIntroDetail.getAuthStatus());
//						custVO.setIsActive(Integer.toString(custIntroDetail.getIsActive()));				
//						custVO.setIsDeleted(String.valueOf(custIntroDetail.getIsDeleted()));
					}
				}
				logger.debug("Exit from  custIntroDetailList ");
				custVOList.add(custVO);
			}
		}
		logger.debug("Exit getCustomerByPendingStatus method ");
		return custVOList;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	@PostMapping(value = "/createCustomer", consumes = "application/json", produces = "application/json")
	public String createCustomer(@RequestBody CustomerVO customer) throws ParseException {
		logger.info("Inside consumer " + customer);
		
		System.out.println(customer);
		//customer.setPrimaryKeyData(customer.getTenantId()+"|"+customer.getCustomerId());
		
		List<CustomerMst> customerList = customerService.getByCustomerId(customer.getCustomerId());
		if (null != customerList && customerList.size() > 0) {
			return "Failure.. Record already exists";
		} 
		else {
			CustomerMst customerMst = new CustomerMst();
			Date dt = new Date();
			customerMst.setCreatedBy("login");
			customerMst.setCreatedDate(dt);
			customerMst.setCreatedTime(dt);
			customerMst.setLastModifiedBy("login");
			customerMst.setLastModifiedDate(dt);
			customerMst.setLastModifiedTime(dt);
			customerMst.setAuthStatus("A");
			customerMst.setIsActive(1);
			
			customerMst.setIsDeleted(Boolean.FALSE);
			customerMst.setCustomerId(customer.getCustomerId());
			customerMst.setCustomerFullName(customer.getCustomerFullName());
			customerMst.setCustomerFullDisplayName(customer.getCustomerFullDisplayName());
			customerMst.setFirstName(customer.getFirstName());
			customerMst.setLastName(customer.getLastName());
			customerMst.setMiddleName(customer.getMiddleName());
			// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date convertedCurrentDate = sdf.parse(customer.getDateOfBirth());

			customerMst.setDateOfBirth(convertedCurrentDate);
			customerMst.setMobileNumber(customer.getMobileNumber());
			customerMst.setEmailId(customer.getEmailId());

			List<CustomerDetails> customerDetailList = new ArrayList<CustomerDetails>();
			CustomerDetails custDetails = new CustomerDetails();
			custDetails.setCreatedBy("login");
			custDetails.setCreatedDate(dt);
			custDetails.setCreatedTime(dt);
			custDetails.setLastModifiedBy("login");
			custDetails.setLastModifiedDate(dt);
			custDetails.setLastModifiedTime(dt);
			custDetails.setAuthStatus("A");
			custDetails.setIsActive(1);
			custDetails.setIsDeleted(Boolean.FALSE);

			custDetails.setCaste(customer.getCaste());
			custDetails.setCitizenship(customer.getCitizenship());
			custDetails.setCustomerCategory(customer.getCustomerCategory());
			custDetails.setCustomerType(customer.getCustomerType());
			custDetails.setFatherName(customer.getFatherName());
			custDetails.setHusbandName(customer.getHusbandName());
			custDetails.setMotherName(customer.getMotherName());
			custDetails.setGender(customer.getGender());

			convertedCurrentDate = sdf.parse(customer.getKycExpiryDate());

			custDetails.setKycExpiryDate(convertedCurrentDate);
			custDetails.setMaritalStatus(customer.getMaritalStatus());
			custDetails.setOccupation(customer.getOccupation());
			custDetails.setPreferenceCategory(customer.getPreferenceCategory());
			custDetails.setQualification(customer.getQualification());
			custDetails.setReligion(customer.getReligion());
			custDetails.setResidentialStatus(customer.getResidentialStatus());
			if ("TRUE".equalsIgnoreCase(customer.getSelfEmployed())) {
				custDetails.setSelfEmployed(Boolean.TRUE);
			} else {
				custDetails.setSelfEmployed(Boolean.FALSE);
			}
			custDetails.setCustomer(customerMst);
			customerDetailList.add(custDetails);
			customerMst.setCustomerDetail(customerDetailList);

			List<CustomerIntroducerMst> introducerDetailList = new ArrayList<CustomerIntroducerMst>();
			CustomerIntroducerMst customerIntroducerMst = new CustomerIntroducerMst();
			customerIntroducerMst.setCreatedBy("login");
			customerIntroducerMst.setCreatedDate(dt);
			customerIntroducerMst.setCreatedTime(dt);
			customerIntroducerMst.setLastModifiedBy("login");
			customerIntroducerMst.setLastModifiedDate(dt);
			customerIntroducerMst.setLastModifiedTime(dt);
			customerIntroducerMst.setAuthStatus("A");
			customerIntroducerMst.setIsActive(1);
			customerIntroducerMst.setIsDeleted(Boolean.FALSE);

			customerIntroducerMst.setIntroducerId(customer.getIntroducerId());
			convertedCurrentDate = sdf.parse(customer.getIntroducedDate());

			customerIntroducerMst.setIntroducedDate(convertedCurrentDate);
			customerIntroducerMst.setCustomer(customerMst);

			introducerDetailList.add(customerIntroducerMst);
			customerMst.setIntroducerDetail(introducerDetailList);

			CustomerMst customerMstNew = customerService.saveOrUpdate("login", customerMst);
			if (null != customerMstNew) {
				return "Successfully saved record ";
			} else {
				return "Failure while saving record";
			}
		}
	}
	
	
	
	


	@PostMapping(value = "/updateCustomer", consumes = "application/json", produces = "application/json")
	public String updateCustomer(@RequestBody CustomerVO customer) throws ParseException {

		List<CustomerMst> customerList = customerService.getByCustomerId(customer.getCustomerId());
		logger.info("in update method  call");
		if (null != customerList && customerList.size() > 0) {
			CustomerMst customerMst = customerList.get(0);
			Date dt = new Date();
			customerMst.setLastModifiedBy("login2");
			customerMst.setLastModifiedDate(dt);
			customerMst.setLastModifiedTime(dt);
			customerMst.setCreatedDate(dt);
			customerMst.setCreatedTime(dt);
//			customerMst.setAuthStatus("A");
//			customerMst.setIsActive(1);
//			customerMst.setIsDeleted(Boolean.FALSE);

			customerMst.setAuthStatus(customer.getAuthStatus());
			customerMst.setIsActive(Integer.parseInt(customer.getIsActive()));
			customerMst.setIsDeleted(customer.getIsDeleted().equals("1") ? true : false);

			customerMst.setCustomerFullName(customer.getCustomerFullName());
			customerMst.setCustomerFullDisplayName(customer.getCustomerFullDisplayName());
			customerMst.setFirstName(customer.getFirstName());
			customerMst.setLastName(customer.getLastName());
			customerMst.setMiddleName(customer.getMiddleName());
			// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date convertedCurrentDate = sdf.parse(customer.getDateOfBirth());

			customerMst.setDateOfBirth(convertedCurrentDate);
			customerMst.setMobileNumber(customer.getMobileNumber());
			customerMst.setEmailId(customer.getEmailId());

			customerMst.setCustomerDetail(null);
			customerMst.setIntroducerDetail(null);

			List<CustomerDetails> customerDetailList = new ArrayList<CustomerDetails>();
			CustomerDetails custDetails = new CustomerDetails();
			custDetails.setLastModifiedBy("login2");
			custDetails.setLastModifiedDate(dt);
			custDetails.setLastModifiedTime(dt);
			custDetails.setCreatedDate(dt);
			custDetails.setCreatedTime(dt);
			custDetails.setAuthStatus("A");
			custDetails.setIsActive(1);
			custDetails.setIsDeleted(Boolean.FALSE);

			custDetails.setCaste(customer.getCaste());
			custDetails.setCitizenship(customer.getCitizenship());
			custDetails.setCustomerCategory(customer.getCustomerCategory());
			custDetails.setCustomerType(customer.getCustomerType());
			custDetails.setFatherName(customer.getFatherName());
			custDetails.setHusbandName(customer.getHusbandName());
			custDetails.setMotherName(customer.getMotherName());
			custDetails.setGender(customer.getGender());
			convertedCurrentDate = sdf.parse(customer.getKycExpiryDate());
			custDetails.setKycExpiryDate(convertedCurrentDate);
			custDetails.setMaritalStatus(customer.getMaritalStatus());
			custDetails.setOccupation(customer.getOccupation());
			custDetails.setPreferenceCategory(customer.getPreferenceCategory());
			custDetails.setQualification(customer.getQualification());
			custDetails.setReligion(customer.getReligion());
			custDetails.setResidentialStatus(customer.getResidentialStatus());
			if ("TRUE".equalsIgnoreCase(customer.getSelfEmployed())) {
				custDetails.setSelfEmployed(Boolean.TRUE);
			} else {
				custDetails.setSelfEmployed(Boolean.FALSE);
			}

			customerDetailList.add(custDetails);
			customerMst.setCustomerDetail(customerDetailList);

			List<CustomerIntroducerMst> introducerDetailList = new ArrayList<CustomerIntroducerMst>();
			CustomerIntroducerMst customerIntroducerMst = new CustomerIntroducerMst();
			customerIntroducerMst.setLastModifiedBy("login");
			customerIntroducerMst.setLastModifiedDate(dt);
			customerIntroducerMst.setLastModifiedTime(dt);
			customerIntroducerMst.setCreatedDate(dt);
			customerIntroducerMst.setCreatedTime(dt);
			customerIntroducerMst.setAuthStatus("A");
			customerIntroducerMst.setIsActive(1);
			customerIntroducerMst.setIsDeleted(Boolean.FALSE);

			customerIntroducerMst.setIntroducerId(customer.getIntroducerId());
			convertedCurrentDate = sdf.parse(customer.getIntroducedDate());

			customerIntroducerMst.setIntroducedDate(convertedCurrentDate);

			introducerDetailList.add(customerIntroducerMst);
			customerMst.setIntroducerDetail(introducerDetailList);

			CustomerMst customerMstNew = customerService.saveOrUpdate("login", customerMst);
			if (null != customerMstNew) {
				return "Successfully updated record";
			} else {
				return "Failure while updating record";
			}
		} else {
			return "Failure.. Record does not exists";
		}
	}

	@PostMapping(value = "/deleteCustomer/{id}", consumes = "application/json", produces = "application/json")
	public String deleteCustomer(@PathVariable String customerId) {

		List<CustomerMst> customerList = customerService.getByCustomerId(customerId);

		if (null != customerList && customerList.size() > 0) {
			CustomerMst customerMst = customerList.get(0);
			Date dt = new Date();
			customerMst.setDeletedBy("login3");
			customerMst.setDeletedDate(dt);
			customerMst.setDeletedTime(dt);
			customerMst.setAuthStatus("D");
			customerMst.setIsActive(0);
			customerMst.setIsDeleted(Boolean.TRUE);

			CustomerMst customerMstNew = customerService.saveOrUpdate("login", customerMst);
			if (null != customerMstNew) {
				return "Successfully deleted record";
			} else {
				return "Failure while deleting record";
			}
		} else {
			return "Failure.. Record does not exists";
		}
	}
	//////

//	  @RabbitListener(queues = "queue.B")
//	  private void createCustomerFromLoan(CustomerForLoanVO customer) throws ParseException {
//	  
//	  logger.info("Inside queue.B " +customer); System.out.println(customer);
//	  
//	  List<CustomerMst> customerList =
//	  customerService.getByCustomerId(String.valueOf(customer.getMemberCode()));
//	  
//	  if (null != customerList && customerList.size() > 0 ) {
//	  logger.info("Inside if block" );
//	 
//	  //return "Failure.. Record already exists"; } else {
//	  logger.info("Inside else block" ); CustomerMst customerMst = new
//	  CustomerMst(); Date dt = new Date(); customerMst.setCreatedBy("login");
//	  customerMst.setCreatedDate(dt); customerMst.setCreatedTime(dt);
//	  customerMst.setLastModifiedBy("login"); customerMst.setLastModifiedDate(dt);
//	  customerMst.setLastModifiedTime(dt); customerMst.setAuthStatus("A");
//	 customerMst.setIsActive(1); customerMst.setIsDeleted(Boolean.FALSE);
//	  
//	  customerMst.setCustomerId(String.valueOf(customer.getMemberCode()));
//	  //customerMst.setCustomerFullDisplayName(customer.getMemberFullName());
//	  customerMst.setFirstName(customer.getMemberFName());
//	  //customerMst.setLastName(customer.getMemberLName());
//	  customerMst.setMiddleName(customer.getMemberMName()); SimpleDateFormat sdf =
//	  new SimpleDateFormat("dd/MM/yyyy"); Date convertedCurrentDate =
//	 sdf.parse(customer.getDateOfBirthStr());
//	 customerMst.setDateOfBirth(convertedCurrentDate);
//	  customerMst.setMobileNumber(customer.getMobileNo1());
//	  customerMst.setEmailId(customer.getEmailId());
//	  
//	  List<CustomerDetails> customerDetailList = new ArrayList<CustomerDetails>();
//	  CustomerDetails custDetails = new CustomerDetails();
//	  custDetails.setCreatedBy("login"); custDetails.setCreatedDate(dt);
//	  custDetails.setCreatedTime(dt); custDetails.setLastModifiedBy("login");
//	  custDetails.setLastModifiedDate(dt); custDetails.setLastModifiedTime(dt);
//	  custDetails.setAuthStatus("A"); custDetails.setIsActive(1);
//	  custDetails.setIsDeleted(Boolean.FALSE);
//	  
//	  //custDetails.setCaste(customer.getCaste());
//	  //custDetails.setCitizenship(customer.getCitizenship());
//	  custDetails.setCustomerCategory("L");
//	  //custDetails.setCustomerType(customer.getCustomerType());
//	  custDetails.setFatherName(customer.getFatherSpouseName());
//	  custDetails.setHusbandName(customer.getSpouseName());
//	  //custDetails.setMotherName(customer.getMotherName());
//	  custDetails.setGender(customer.getMemberGender()); //convertedCurrentDate =
//	  sdf.parse(customer.getKycExpiryDate());
//	  //custDetails.setKycExpiryDate(convertedCurrentDate);
//	  
//	  custDetails.setCustomer(customerMst); customerDetailList.add(custDetails);
//	  customerMst.setCustomerDetail(customerDetailList);
//	  
//	  List<CustomerIntroducerMst> introducerDetailList = new
//	  ArrayList<CustomerIntroducerMst>(); CustomerIntroducerMst
//	  customerIntroducerMst = new CustomerIntroducerMst();
//	  customerIntroducerMst.setCreatedBy("login");
//	  customerIntroducerMst.setCreatedDate(dt);
//	  customerIntroducerMst.setCreatedTime(dt);
//	  customerIntroducerMst.setLastModifiedBy("login");
//	  customerIntroducerMst.setLastModifiedDate(dt);
//	  customerIntroducerMst.setLastModifiedTime(dt);
//	  customerIntroducerMst.setAuthStatus("A");
//	  customerIntroducerMst.setIsActive(1);
//	  customerIntroducerMst.setIsDeleted(Boolean.FALSE);
//	  
//	  customerIntroducerMst.setIntroducerId(customer.getIntroducerId());
//	  //convertedCurrentDate = sdf.parse(customer.getIntroducedDate());
//	  
//	  customerIntroducerMst.setIntroducedDate(dt);
//	  customerIntroducerMst.setCustomer(customerMst);
//	  
//	  introducerDetailList.add(customerIntroducerMst);
//	  customerMst.setIntroducerDetail(introducerDetailList);
//	  
//	 CustomerMst customerMstNew = customerService.saveOrUpdate("login",customerMst);
//	  
//	  if (null != customerMstNew) { 
//		 return "Successfully saved record"; 
//		 }
//	  else {
//	  }
//	  return "Failure while saving record"; 
//	  }
//	  } 
//	  }

	 @RabbitListener(queues = "custservice_create_Q")
	public void Consumer(D009011VO customer) throws ParseException {
		logger.info("Inside publisher " + customer);
		System.out.println("hii " + customer.getMemberMName());

		logger.info("Inside consumer " + customer);
		System.out.println(customer);
		List<CustomerMst> customerList = customerService.getByCustomerId(String.valueOf(customer.getMemberCode()));
		// List<CustomerMst> customerList =
		// customerService.getByCustomerId(customer.getMemberCode().toString());

		if (null != customerList && customerList.size() > 0) {
			// return "Failure.. Record already exists";
		} else {
			CustomerMst customerMst = new CustomerMst();
			Date dt = new Date();
			customerMst.setCreatedBy("login");
			customerMst.setCreatedDate(dt);
			customerMst.setCreatedTime(dt);
			customerMst.setLastModifiedBy("login");
			customerMst.setLastModifiedDate(dt);
			customerMst.setLastModifiedTime(dt);
			customerMst.setAuthStatus("A");
			customerMst.setIsActive(1);
			customerMst.setIsDeleted(Boolean.FALSE);

			customerMst.setCustomerId(customer.getMemberCode().toString());
			customerMst.setCustomerFullName(
					customer.getMemberFName() + " " + customer.getMemberLName() + " " + customer.getMemberMName());
			customerMst.setCustomerFullDisplayName(
					customer.getMemberFName() + " " + customer.getMemberLName() + " " + customer.getMemberMName());
			customerMst.setFirstName(customer.getMemberFName());
			customerMst.setLastName(customer.getMemberLName());
			customerMst.setMiddleName(customer.getMemberMName());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// Date convertedCurrentDate = sdf.parse(customer.getDateOfBirth());
			// customerMst.setDateOfBirth(convertedCurrentDate);
			customerMst.setMobileNumber(customer.getMobileNo1());
			customerMst.setEmailId(customer.getEmailId());

			List<CustomerDetails> customerDetailList = new ArrayList<CustomerDetails>();
			CustomerDetails custDetails = new CustomerDetails();
			custDetails.setCreatedBy("login");
			custDetails.setCreatedDate(dt);
			custDetails.setCreatedTime(dt);
			custDetails.setLastModifiedBy("login");
			custDetails.setLastModifiedDate(dt);
			custDetails.setLastModifiedTime(dt);
			custDetails.setAuthStatus("A");
			custDetails.setIsActive(1);
			custDetails.setIsDeleted(Boolean.FALSE);

			custDetails.setCustomerCategory(customer.getCustomerCategory());
			custDetails.setCustomerType(customer.getCustomerType());
			custDetails.setFatherName(customer.getFatherSpouseName());

			/*
			 * custDetails.setCaste(customer.getcCaste());
			 * custDetails.setCitizenship(customer.getCitizenship());
			 * 
			 * custDetails.setHusbandName(customer.getHusbandName());
			 * custDetails.setMotherName(customer.getMotherName());
			 * custDetails.setGender(customer.getGender());
			 * 
			 * convertedCurrentDate = sdf.parse(customer.getKycExpiryDate());
			 * custDetails.setKycExpiryDate(convertedCurrentDate);
			 */

			custDetails.setMaritalStatus(customer.getMaritalStatus());
			custDetails.setOccupation(customer.getOccupation());
			// custDetails.setPreferenceCategory(customer.getPreferenceCategory());
			custDetails.setQualification(customer.getQualification());
			custDetails.setReligion(customer.getReligion());
			custDetails.setResidentialStatus(customer.getResidentialStatus());
			/*
			 * if ("TRUE".equalsIgnoreCase(customer.getSelfEmployed())) {
			 * custDetails.setSelfEmployed(Boolean.TRUE); } else {
			 * custDetails.setSelfEmployed(Boolean.FALSE); }
			 */
			custDetails.setCustomer(customerMst);
			customerDetailList.add(custDetails);
			customerMst.setCustomerDetail(customerDetailList);

			List<CustomerIntroducerMst> introducerDetailList = new ArrayList<CustomerIntroducerMst>();
			CustomerIntroducerMst customerIntroducerMst = new CustomerIntroducerMst();
			customerIntroducerMst.setCreatedBy("login");
			customerIntroducerMst.setCreatedDate(dt);
			customerIntroducerMst.setCreatedTime(dt);
			customerIntroducerMst.setLastModifiedBy("login");
			customerIntroducerMst.setLastModifiedDate(dt);
			customerIntroducerMst.setLastModifiedTime(dt);
			customerIntroducerMst.setAuthStatus("A");
			customerIntroducerMst.setIsActive(1);
			customerIntroducerMst.setIsDeleted(Boolean.FALSE);

			// customerIntroducerMst.setIntroducerId(customer.getIntroducerId());
			// convertedCurrentDate = sdf.parse(customer.getIntroducedDate());
			// customerIntroducerMst.setIntroducedDate(convertedCurrentDate);
			customerIntroducerMst.setCustomer(customerMst);

			introducerDetailList.add(customerIntroducerMst);
			customerMst.setIntroducerDetail(introducerDetailList);

			CustomerMst customerMstNew = customerService.saveOrUpdate("login", customerMst);
			if (null != customerMstNew) {
				// return "Successfully saved record";
				template.convertAndSend(exchange.getName(), MessagingConfig.ROUTING_CS_CREATE_RESP,
						"Successfully created customer #" + customer.getMemberCode());
			} else {
				// return "Failure while saving record";
				template.convertAndSend(exchange.getName(), MessagingConfig.ROUTING_CS_CREATE_RESP,
						"Failure while creating customer #" + customer.getMemberCode());
			}
		}
	}

//	@GetMapping("/masterCountryDetl")
//	@CircuitBreaker(name = CUSTOMERSERVICE, fallbackMethod = "countryFallback")
//	public ResponseEntity<String> getCountryDetails() {
//		String response = restTemplate.getForObject("http://localhost:8183/country/list/authorized", String.class);
//
//		return new ResponseEntity<String>(response, HttpStatus.OK);
//
//	}

	int attempts = 1;

	@GetMapping("/master")
	@Retryable(value = ResourceAccessException.class, maxAttempts = 2, backoff = @Backoff(delay = 20000))
	public String getCountryDetails() {
		System.out.println("item service call  " + attempts++);
		String response = restTemplate.getForObject("http://localhost:8081/item", String.class);

		return response;
	}

	public ResponseEntity<String> countryFallback(Exception e) {
		return new ResponseEntity<String>("master service is down", HttpStatus.OK);

	}

}
