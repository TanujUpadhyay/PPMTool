package com.alphacode.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alphacode.ppmtool.domain.User;
import com.alphacode.ppmtool.exceptions.UsernameAllreadyExistsException;
import com.alphacode.ppmtool.repositories.UserRepositiry;

@Service
public class UserService {
	
	@Autowired
	private UserRepositiry userRepositiry;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser) {
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			newUser.setUsername(newUser.getUsername());
			newUser.setConfirmPassword("");
			return userRepositiry.save(newUser);
		} catch (Exception e) {
			throw new UsernameAllreadyExistsException("Username '"+newUser.getUsername()+"' already exists!!");
		}
		
	}
	
	
	
}
