package com.alphacode.ppmtool.web;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphacode.ppmtool.domain.User;
import com.alphacode.ppmtool.security.JwtTokenProvider;
import com.alphacode.ppmtool.services.MapValidationErrorService;
import com.alphacode.ppmtool.services.UserService;
import com.alphacode.ppmtool.validator.UserValidator;
import com.alphacode.ppmtool.payload.JWTLoginResponse;
import com.alphacode.ppmtool.payload.LoginRequest;
import static com.alphacode.ppmtool.security.SecurityConstants.TOKEN_PREFIX;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private MapValidationErrorService mapValidation;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidater;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(
			@Valid @RequestBody LoginRequest loginRequest,
			BindingResult result
			)
	{
		ResponseEntity<?> errorMap = mapValidation.MapValidationService(result);
		if(errorMap!=null) {
			return errorMap;
		}
		
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
						loginRequest.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = TOKEN_PREFIX + tokenProvider.generateToken(auth);
		return ResponseEntity.ok(new JWTLoginResponse(true, jwt));
	}
	
	
	
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
