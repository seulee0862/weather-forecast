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
public class ControllerAspect {

	@Before("controllerLayer()")
	public void beforeRequest(JoinPoint joinPoint) {
		log.info("###Start Layer : {} , method :  {} ###",
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName());

		log.info("===== parameter info open =====");
		String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
		Object[] parameterValues = joinPoint.getArgs();
		for (int i = 0; i < parameterValues.length; i++) {
			log.info("{}: {} \n", parameterNames[i], parameterValues[i]);
		}
		log.info("===== parameter Values info close =====");
	}

	@AfterReturning(pointcut = "controllerLayer()", returning = "returnValue")
	public void afterReturningLogging(JoinPoint joinPoint, Object returnValue) {
		log.info("###End Layer : {} , method :  {} ###",
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName());
	}

	@AfterThrowing(pointcut = "controllerLayer()", throwing = "e")
		public void afterThrowingLogging(JoinPoint joinPoint, Exception e) {
		log.error("!!! Occured error at {}", joinPoint.getSignature().getName());
		log.error("{}", e.getMessage());
	}

	@Pointcut("within(com.project02server.core.controller.*)")
	public void controllerLayer() {

	}
}
