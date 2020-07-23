package com.alphacode.ppmtool.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alphacode.ppmtool.domain.User;
import com.alphacode.ppmtool.services.CustomeUserDetailsServices;


import static com.alphacode.ppmtool.security.SecurityConstants.HEADER_STRING;

import static com.alphacode.ppmtool.security.SecurityConstants.TOKEN_PREFIX;;



public class JwtAtuhenticationFilters extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private CustomeUserDetailsServices customeUserDetailsServices;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
			
			if(StringUtils.hasText(jwt)&&tokenProvider.validateToken(jwt))
			{
				Long userId = tokenProvider.getUserIdFromTOken(jwt);
				User userDeatils = customeUserDetailsServices.loadUserById(userId);
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDeatils,null,Collections.emptyList()
						);
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			
			}
				
				
		} catch (Exception e) {
			logger.error("Could not set user auth in securtity context ,",e);
		}
		
		filterChain.doFilter(request,response);

	}
	
	private String getJwtFromRequest(HttpServletRequest request)
	{
		String bearerToken = request.getHeader(HEADER_STRING);
		
		if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX))
		{
			return bearerToken.substring(7,bearerToken.length());
		}
		return null;
	}

}
