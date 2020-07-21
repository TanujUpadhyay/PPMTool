package com.alphacode.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alphacode.ppmtool.domain.User;
import com.alphacode.ppmtool.repositories.UserRepositiry;

@Component
public class CustomeUserDetailsServices implements UserDetailsService {

	@Autowired
	private UserRepositiry userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username);
		
		if(user==null)
		{
			new UsernameNotFoundException("User not found");
		}
 		
		return user;
	}
	
	@Transactional
	public User loadUserById(Long id) {
		User user = userRepo.getById(id);
		if(user==null)
		{
			new UsernameNotFoundException("User not found");
		}
 		
		return user;
	}

	
}
