package com.lipari.events.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.lipari.events.security.user_details.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${academy.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${academy.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		
		return Jwts.builder()
		        .setSubject(userPrincipal.getEmail())
		        .setIssuedAt(new Date())
		        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
		        .signWith(key(), SignatureAlgorithm.HS256)
		        .compact();
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public String getEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(key()).build()
				.parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(key()).build().parse(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: " + e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
	    	logger.error("JWT token is unsupported: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
	    	logger.error("JWT claims string is empty: {}", e.getMessage());
	    }
		
		return false;
	}
}
