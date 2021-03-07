package com.exchange.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<Object> BadRequestException(final RuntimeException ex){
		log.warn("error", ex);
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> GlobalException(final  Exception ex){
		log.info(ex.getClass().getName());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
