package pe.edu.uni.Biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.Biblioteca.dto.UsuarioDTO;
import pe.edu.uni.Biblioteca.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/acceso")
	public ResponseEntity<?> accederCuenta(@RequestBody UsuarioDTO bean){
		try {
			bean = loginService.accesoUsuario(bean);
			if (bean.isActiva()) {
				String body = "Perfil de usuario: "+ bean.getCodigo();
				return ResponseEntity.ok().body(body);
			} else {
				return ResponseEntity.ok().body("Contraseña incorrecta.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Usuario incorrecto.", HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
