package pe.edu.uni.Biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AlumnoDTO {

	private String codigoAlumno;
	private String nombre;
	private String apellidos;
	private String correo;
	private int faltas;
	
}
