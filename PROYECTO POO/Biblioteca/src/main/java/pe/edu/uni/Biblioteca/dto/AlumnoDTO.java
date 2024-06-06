package pe.edu.uni.BIBLIOTECA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AlumnoDTO {

    private String CodigoAlumno;
    private String Nombres;
    private String Apellidos;
    private String Correo;
    private String Usuario;
    private String Clave;


}
