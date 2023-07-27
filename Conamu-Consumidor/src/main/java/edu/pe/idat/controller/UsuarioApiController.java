package edu.pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pe.idat.request.UsuarioCreateRequest;
import edu.pe.idat.response.TransactionResponse;
import edu.pe.idat.service.ConsumidorService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioApiController {

	@Autowired
	private ConsumidorService consumidorService;
	
	@PostMapping("/create")
	public TransactionResponse createUsuario(@RequestBody UsuarioCreateRequest request) {
		
		try {
			
			consumidorService.addPersonaConsumidor(request);
			
			return new TransactionResponse(true, "Usuario creado con éxito.");
			
		}catch(Exception e) {
			
			return new TransactionResponse(false, "Ocurrió un error al registrar el Usuario: ".concat(e.getMessage()));
			
		}
		
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("Authorization", null);
		cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
	}
	
}
