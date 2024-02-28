package com.JwtCode.P.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JwtCode.P.Model.JwtRequest;
import com.JwtCode.P.Model.JwtResponse;
import com.JwtCode.P.utility.JwtHelper;

@RestController
public class JwtController {
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;
    

    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
    {
    	this.doAuthenticate(request.getUsername(), request.getPassword());
    	UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
    	String token = this.helper.generate(userDetails);
    	JwtResponse response = JwtResponse.builder()
    			               .jwtToken(token)
    			               .username(userDetails.getUsername())
    			               .build();
    	return ResponseEntity.ok(response);
    			     
    }
    private void doAuthenticate(String username,String password)
    {
    	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
    	try {
			this.manager.authenticate(authentication);
		}catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }
    @GetMapping("/hello")
    public String str()
    {
    	return "Okkk";
    }
    
    
    
	
	
	

}
