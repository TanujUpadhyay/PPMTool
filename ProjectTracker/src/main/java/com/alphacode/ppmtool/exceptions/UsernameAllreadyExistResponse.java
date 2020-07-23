package com.alphacode.ppmtool.exceptions;

public class UsernameAllreadyExistResponse {
	private String username;

	public UsernameAllreadyExistResponse(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
