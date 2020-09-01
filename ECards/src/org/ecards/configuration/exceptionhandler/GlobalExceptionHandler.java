package org.ecards.configuration.exceptionhandler;

import java.util.Date;

import org.ecards.service.SecurityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public void logExceptions(Exception e) {
		logger.error(String.format("Exception Date : ", new Date()));
		logger.error(String.format("Exception      : /n", e.getStackTrace().toString()));
	}   
	
}
