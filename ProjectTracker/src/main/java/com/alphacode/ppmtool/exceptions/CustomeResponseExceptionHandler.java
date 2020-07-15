package com.alphacode.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomeResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectException(ProjectIdException ex, WebRequest request) {
		ProjectIdExceptionResponese exceptionResponese = new ProjectIdExceptionResponese(ex.getMessage());
		return new ResponseEntity(exceptionResponese, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest request) {
		ProjectNotFoundExceptionResponse exceptionResponese = new ProjectNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity(exceptionResponese, HttpStatus.BAD_REQUEST);

	}

}
