package com.JwtCode.P;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PApplication {

	public static void main(String[] args) {
		SpringApplication.run(PApplication.class, args);
	}
    //exceptional handling - entryPoint implements authentication Entry point - method commence - setStatus - print authentication.getMessage
	// jwt helper - 8 methods
	//  2 - claims - 1-getAllClaims-return claims ,  2-getClaim - return Function <T> T
	// 2 expiry date - 1-getExpiryDate - return Date , 2- checkExpiryDate - return boolean
	// getUserName byusing getClaim - pass token + Claims::getSubject return String
	// validate return boolean using userService.loadByUsername(username) - return username.isequal(userDetail.getUsername) && isToeknExpired 
	// doGenerate - setClaims,setSubject,issueddate,expiryDate,signWith.build -  parameter - String secret , map<String,Object> 
	// generate - return String token parameter - userDetail for getting username , make a map<String,Object>
	
	
	// filter
	// implements oncePerRequest
	// getHeader-"Authrization" and extract token from , get UserNameFromToken - in tryCatch , write if condition - username not == null and filterration didnt done yet - securityContextHolder.getContext
	// userDetail - userDetailServices.loadByusername(username)
	// validate by helper.validate(userDetails)
	// if(validate) - Usernamepassword c  = new usernamePass(username,null,userDetails.getAuthorties) c.setDetails(new WebAuth(request))
	// securityContextHolder.getContext().authentication
	// filterChain.doFilter(request,response)
	
	// securityConfig
	// autowired - filter , entryPointException
	// parameter httpSecurity - http.csrv,cors,requestDispacter.permitAll.anyRequest.authrized
	// exceptionalHandling(e -> e.authenticatonEntryPoint(entryPoint)
	// sessionManagement(s -> createSessionPolicy(Session.StateLess)
	// bedoreFiler(filter, userNamePassworAuthFilter.class)
	
	// controller
	// doAuth
	// para - usrname , password 
	// usernamePasswordAuthTOken t  = new (username,password)
	// try - manager(AuthManager) -> authenticate(t)
	// login(requestBody)
	// doAuth(rb.getusername, rb.getPass)
	// UserDetail ud = userDetails.loadByByusername(usernaem)
	// String token = helper.generateToken(userDetail)
	// return jwtResponse
	
}
