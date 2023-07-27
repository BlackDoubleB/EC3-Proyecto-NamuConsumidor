package edu.pe.idat.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
		
		String bearerToken = obtenerTokenDesdeCookie(request);
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.replace("Bearer ", "");
			UsernamePasswordAuthenticationToken usernameAuth = jwtUtils.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(usernameAuth);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private String obtenerTokenDesdeCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    String cookieValue = null;

	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("Authorization")) {
	                cookieValue = cookie.getValue();
	                break;
	            }
	        }
	    }
	    
	    if(cookieValue == null) {
	    	return null;
	    }
	    
	    return jwtUtils.decodificarTokenBase64(cookieValue);
	}
	

}
