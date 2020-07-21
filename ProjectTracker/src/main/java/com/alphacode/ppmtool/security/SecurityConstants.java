package com.alphacode.ppmtool.security;

public class SecurityConstants {
	
	
	public static final String SING_UP_URLS = "/api/users/**";
	
	public static final String H2_URL = "h2-console/**";
	

	public static final String SECRET = "SecretKeyToGenJWT";
	

	public static final String TOKEN_PREFIX = "Bearer ";
	

	public static final String HEADER_STRING = "Authorization";
	

	public static final long EXPIRATION_TTIME = 300_000;//30 sconds
	
	
	
	
}
