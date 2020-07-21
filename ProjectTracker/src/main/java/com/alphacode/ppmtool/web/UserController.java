package com.alphacode.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphacode.ppmtool.domain.User;
import com.alphacode.ppmtool.services.MapValidationErrorService;
import com.alphacode.ppmtool.services.UserService;
import com.alphacode.ppmtool.validator.UserValidator;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private MapValidationErrorService mapValidation;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidater;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user ,BindingResult result)
	{
		userValidater.validate(user,result);
		
		ResponseEntity<?> errorMap = mapValidation.MapValidationService(result);
		if(errorMap!=null) {
			return errorMap;
		}
		
		User newUser = userService.saveUser(user);
		
		return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
	}
}
