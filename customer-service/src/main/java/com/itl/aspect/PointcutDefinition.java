package com.itl.aspect;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.itl.utils.OmniConstants;
import com.itl.web.dto.CustomerVO;
import java.lang.reflect.*;
@Aspect
@Component
@Service
public class PointcutDefinition {

	private static final Logger logger = LoggerFactory.getLogger(PointcutDefinition.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;



	@Pointcut("execution(* com.itl.controller.*.create*(..))")
	public void loggingPointCut() {
		logger.info("before aspects aspects");
	}

//	@Before("loggingPointCut()")
//	public void before(JoinPoint joinPoint) {
//		logger.info("Before method invoked from spring boot");
//	}

//	@After("loggingPointCut()")
//	public void sendMessage(JoinPoint joinPoint) {
//	logger.info("after aspect from spring boot ");
//	this.kafkaTemplate.send(OmniConstants.TOPIC_NAME_TEST, message);
//	}
	
	
	@After("loggingPointCut()")
	public void around(JoinPoint joinPoint) throws Throwable {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		Enumeration<String> header = request.getHeaderNames();
//		while (header.hasMoreElements()) {
//			System.out.println(header.nextElement());
//			String param = (String) header.nextElement();
//		}
//	 StringBuffer header2 = request.getRequestURL();

		Object obj = joinPoint.getArgs()[0];
		//  Class<?> c = Class.forName("com.itl.web.dto.CustomerVO");
		  Class<?> c = obj.getClass();
		  Method methods =c.getDeclaredMethod("getPrimaryKey",null);
		  methods.setAccessible(true);
		  Object object=methods.invoke(obj, null);
		
		if (null!=object) {
			this.kafkaTemplate.send(OmniConstants.TOPIC_NAME_TEST, object);

		}

	}

//	@After("loggingPointCut()")
//	public void around(JoinPoint joinPoint) throws Throwable {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//				.getRequest();
//		Enumeration<String> header = request.getHeaderNames();
//		while (header.hasMoreElements()) {
//			System.out.println(header.nextElement());
//			String param = (String) header.nextElement();
//		}
//		// StringBuffer header2 = request.getRequestURL();
//
//		Object obj = joinPoint.getArgs()[0];
//		
//		if (obj instanceof CustomerVO) {
//			String id = ((CustomerVO) obj).getCustomerId();
//			ArrayList arraylist = new ArrayList();
//			arraylist.add(id);
//			arraylist.add(((CustomerVO) obj).getCustomerFullDisplayName());
//			arraylist.add(((CustomerVO) obj).getMobileNumber());
//
//			Object[] signatureArgs = joinPoint.getArgs();
//			for (Object signatureArg : signatureArgs) {
//				System.out.println("Arg: " + signatureArg);
//			}
//
//			this.kafkaTemplate.send(OmniConstants.TOPIC_NAME_TEST, arraylist);
//
//		}
//
//	}

//	@Around("loggingPointCut()")
//	  public Object around( ProceedingJoinPoint joinPoint ) throws Throwable {
//		  logger.info("Before method invoked::"+joinPoint.getArgs()[0]);
//	        Object object = joinPoint.proceed();
//            Object obj=joinPoint.getArgs()[0];
//	        if(object instanceof CustomerVO){
//	        	logger.info("After method invoked..."+joinPoint.getArgs()[0]);
//	        }
//	        if(obj instanceof CustomerVO){
//	        	logger.info("After method invoked..."+joinPoint.getArgs()[0]);
//	        }
//	        return object;
//	    }

//	@AfterReturning(pointcut="loggingPointCut()", returning = "customer")
//    public void afterReturning(String customer) {
//		logger.info("After method invoked::" + customer);
//  	}

//	@AfterReturning(value = "execution(* com.itl.controller.*.*(..))", returning = "customer")
//    public void after(JoinPoint joinPoint, CustomerVO customer) {
//		logger.info("After method invoked::" + customer);
//  	}
//	

//	@AfterThrowing(value = "execution(* com.example.aop.springaop.controller.*.*(..))",
//	            throwing = "e")
//	    public void after( JoinPoint joinPoint, Exception e ){
//		logger.info("After method invoked::"+e.getMessage());
//	    }
}
