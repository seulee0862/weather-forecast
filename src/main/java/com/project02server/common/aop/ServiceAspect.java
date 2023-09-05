package com.project02server.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ServiceAspect {

	private final String SERVICELAYERTAP = "\t\t";

	@Before("serviceLayer()")
	public void beforeRequest(JoinPoint joinPoint) {

		log.info("{}###Start Layer : {} , method :  {} ###",
			SERVICELAYERTAP,
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName());

		log.info("{}===== parameterValues open =====",SERVICELAYERTAP);
		String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
		Object[] parameterValues = joinPoint.getArgs();
		for (int i = 0; i < parameterValues.length; i++) {
			log.info("{}{}: {} \t",SERVICELAYERTAP, parameterNames[i], parameterValues[i]);
		}
		log.info("{}===== parameterValues close =====",SERVICELAYERTAP);
	}

	@AfterReturning(pointcut = "serviceLayer()", returning = "returnValue")
	public void afterReturningLogging(JoinPoint joinPoint, Object returnValue) {

		log.info("{}###End Layer : {} , method :  {} ###",
			SERVICELAYERTAP,
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName());
	}

	@AfterThrowing(pointcut = "serviceLayer()", throwing = "e")
		public void afterThrowingLogging(JoinPoint joinPoint, Exception e) {

		log.error("{}!!! Occured error at {}",SERVICELAYERTAP, joinPoint.getSignature().getName());
		log.error("{}{}",SERVICELAYERTAP, e.getMessage());
	}

	@Pointcut("within(com.project02server.domain.*.service.*)")
	public void serviceLayer() {

	}
}
