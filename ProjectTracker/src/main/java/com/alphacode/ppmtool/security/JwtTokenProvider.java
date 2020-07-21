package com.alphacode.ppmtool.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.alphacode.ppmtool.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.alphacode.ppmtool.security.SecurityConstants.EXPIRATION_TTIME;
import static com.alphacode.ppmtool.security.SecurityConstants.SECRET;


@Component
public class JwtTokenProvider {
	
	//generate token
	public String generateToken(Authentication authentication)
	{
		User user = (User)authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis());
		
		Date expiryDtae = new Date(now.getTime()+EXPIRATION_TTIME);
		
		String userId = Long.toString(user.getId());
		
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("id", (Long.toString(user.getId())));
		
		claims.put("username", user.getUsername());
		
		claims.put("fullName", user.getFullName());
		
		
		
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiryDtae)
				.signWith(SignatureAlgorithm.HS512,SECRET)
				.compact();
	}
	
	//validate the token
	
	//get user d from token
}
