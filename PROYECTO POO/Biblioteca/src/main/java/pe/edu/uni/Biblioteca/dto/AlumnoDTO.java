package pe.edu.uni.Biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AlumnoDTO {

    private String codigo;
    private String nombres;
    private String apellidos;
    private String correo;
    private int faltas;

}
