package edu.pe.idat.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtils {
	
	private String ACCESS_TOKEN_SECRET = "4276d50f05d2cac4c98c17337ac3654vv5fdfgfg5412d1sfs";
	private Long ACCESS_TOKEN_VALIDITY_SECONDS = 86_400L;
	
	public String createToken(String username, String nombres, String rol) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		
		Map<String, Object> extra = new HashMap<>();
		extra.put("username", username);
		extra.put("nombres", nombres);
		extra.put("rol", rol);
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(getSignatureKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public Key getSignatureKey() {
		byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public <T> T getClaim(String token, Function<Claims, T> claimsFunction) {
		Claims claims = extractAllClaims(token);
		return claimsFunction.apply(claims);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
		
		try {
			
			Claims claims = extractAllClaims(token);
			
			String username = claims.getSubject();
			String rol = (String)claims.get("rol");
			
			return new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority("ROLE_".concat(rol))));
			
		}catch(JwtException e) {
			
			return null;
			
		}
		
	}
	
	public String decodificarTokenBase64(String base64) {
		byte[] decodedBytes = Base64.getDecoder().decode(base64);
		String decodedToken = new String(decodedBytes, StandardCharsets.UTF_8);
		
		return decodedToken;
	}
}
