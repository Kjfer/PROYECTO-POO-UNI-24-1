package pe.edu.uni.Biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginDTO {

	private String codigo;
	private String password;
	private String tipo;
	private boolean activa;
	
}
