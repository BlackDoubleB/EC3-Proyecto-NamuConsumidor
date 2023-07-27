package edu.pe.idat.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	JwtTokenUtils jwtUtils;

	public JwtAuthenticationFilter(JwtTokenUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response) throws AuthenticationException {
		
		AuthCredentials authCredentials = new AuthCredentials();
		
		try {
			
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		}catch(Exception e) {}
		
		UsernamePasswordAuthenticationToken usernameAuth = new UsernamePasswordAuthenticationToken(
					authCredentials.getUsername(), 
					authCredentials.getPassword());
		
		return getAuthenticationManager().authenticate(usernameAuth);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
											HttpServletResponse response, 
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		
		UserDetailsImpl userDetails = (UserDetailsImpl)authResult.getPrincipal();
		String token = jwtUtils.createToken(userDetails.getUsername(), userDetails.getNombres(), userDetails.getRol());
		
		String bearer = "Bearer " + token;
		
		response.addHeader("Authorization", bearer);
		
		String encodedToken = Base64.getEncoder().encodeToString(bearer.getBytes(StandardCharsets.UTF_8));
		
		var cookie = new Cookie("Authorization", encodedToken);
		cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(84600);
        cookie.setPath("/");
		response.addCookie(cookie);
		
		response.getWriter().flush();
		
		
		super.successfulAuthentication(request, response, chain, authResult);
		
	}
	
}
