package com.alphacode.ppmtool.security;

import  static com.alphacode.ppmtool.security.SecurityConstants.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alphacode.ppmtool.services.CustomeUserDetailsServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthenticationEntryPoint unathorizedHandler;
	
	@Autowired
	private CustomeUserDetailsServices customeUserDetailsServices;
	
	@Bean
	public JwtAtuhenticationFilters jwtAtuhenticationFilters() {return new JwtAtuhenticationFilters();}
	
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.userDetailsService(customeUserDetailsServices).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	


	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}





	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unathorizedHandler).and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.headers().frameOptions().sameOrigin()//to enable h2 database
			.and()
			.authorizeRequests()
			.antMatchers(
					"/",
                    "/favicon.ico",
                    "/**/*.png",
                    "/**/*.gif",
                    "/**/*.svg",
                    "/**/*.jpg",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js"
            ).permitAll()
			.antMatchers(SING_UP_URLS).permitAll()
			.antMatchers(H2_URL).permitAll()
            .anyRequest().authenticated();
		
		http.addFilterBefore(jwtAtuhenticationFilters(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
	
	
}
