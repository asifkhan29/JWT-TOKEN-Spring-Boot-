package com.JwtCode.P.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.JwtCode.P.Component.JwtAuthenticationFilter;
import com.JwtCode.P.Component.JwtEntryPointException;



@Configuration
public class SecurityJwtConfiguration {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private JwtEntryPointException jwtEntryPointException;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.csrf(c -> c.disable())
				   .cors(co -> co.disable())
				   .authorizeHttpRequests(auth -> auth.requestMatchers("/Authenticate")
						                 .permitAll().anyRequest().authenticated())
				   .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtEntryPointException))
				   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
				   .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				   .build();
	}

}
