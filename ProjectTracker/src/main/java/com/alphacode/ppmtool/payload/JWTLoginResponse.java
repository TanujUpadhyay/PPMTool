package com.alphacode.ppmtool.payload;

public class JWTLoginResponse {
		private boolean success;
		private String token;
		
		
		public JWTLoginResponse(boolean success, String token) {
			super();
			this.success = success;
			this.token = token;
		}
		
		
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}


		@Override
		public String toString() {
			return "JWTLonginResponse [success=" + success + ", token=" + token + "]";
		}
		
		
		
}
