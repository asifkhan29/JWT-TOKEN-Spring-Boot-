package com.JwtCode.P.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {
	// 2 varaiable token_validity - final static ,  secret - private string
	// 8 methods -> getUserName,validate,generate,doGenerate,getExpiryDate,checkTokenExpire,claimDate,claimAllDate
	
	
	private final long TOKEN_VALIDITY = 5 * 60 * 60;
	private String secret =  "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf" ;
	
	// 1-getAllClaims , 2-getClaim , 3-getExpiryDate , 4-checkIsTokenExpire , 5-getUserName , 6-Validate through username comparing with user,7-doGenerate,generate
	
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	public <T> T getClaimFromToken(String token ,Function<Claims, T> claimResolver )
	{
		final Claims claim = getAllClaimsFromToken(token);
		return claimResolver.apply(claim);
	}
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}
	private Date getExpiryDate(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}
	public boolean isTokenExpired(String token)
	{
		final Date expiration = getExpiryDate(token);
		return expiration.before(new Date());
	}
	public boolean validate(String token , UserDetails userDetails)
	{
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	private String doGenerate( Map<String, Object> claims , String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512 , secret)
				.compact();
	}
	public String generate(UserDetails userDetails)
	{
		Map<String , Object> claims = new HashMap<>();
		return doGenerate(claims,userDetails.getUsername());
	}
	
}
