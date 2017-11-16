package com.nisum.carpool.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.util.CommonsUtil;

@ControllerAdvice
public class ExceptionControllerAdvice {
	private static Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		logger.error("ExceptionControllerAdvice,globalException"+ex.getMessage());
		logger.error(CommonsUtil.getErrorStacktrace(ex));
		Errors error = new Errors();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value()+"");
		error.setErrorMessage("Please contact Helpdesk!!");
		return new ResponseEntity<Errors>(error, HttpStatus.OK);
	}
}
