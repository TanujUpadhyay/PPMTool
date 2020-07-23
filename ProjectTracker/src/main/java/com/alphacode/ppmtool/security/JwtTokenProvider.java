package com.alphacode.ppmtool.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.alphacode.ppmtool.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

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
	public boolean validateToken(String token)
	{
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid jwt signature");
		}
		catch (MalformedJwtException e) {
			System.out.println("Invalid jwt signature");
		}
		catch (ExpiredJwtException e) {
			System.out.println("Expired jwt signature");
		}
		catch (UnsupportedJwtException e) {
			System.out.println("unsupported jwt signature");
		}
		catch (IllegalArgumentException e) {
			System.out.println("Jwt claims string is empty");
		}
		
		return false;
	}
	
	
	//get user d from token
	
	public Long getUserIdFromTOken(String token)
	{
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		String id = (String)claims.get("id");
		return Long.parseLong(id);
	}
	
}










