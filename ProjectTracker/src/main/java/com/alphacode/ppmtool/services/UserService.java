package com.alphacode.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alphacode.ppmtool.domain.User;
import com.alphacode.ppmtool.repositories.UserRepositiry;

@Service
public class UserService {
	
	@Autowired
	private UserRepositiry userRepositiry;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser) {
		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
		//Username has to be unique
			//make sure that paswword abd confirmpassword match
		return userRepositiry.save(newUser);
	}
	
	
	
}
