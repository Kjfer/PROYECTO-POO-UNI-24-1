package pe.edu.uni.Biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class PrestamoDTO {
    private String CodigoAlumno;
    private String EjemplarID;
    private int EmpleadoID;
    private String TipoPrestamo;
}
