package com.alphacode.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAllreadyExistsException extends RuntimeException{
	
	public UsernameAllreadyExistsException(String message) {
		super(message);
	}
	
}
