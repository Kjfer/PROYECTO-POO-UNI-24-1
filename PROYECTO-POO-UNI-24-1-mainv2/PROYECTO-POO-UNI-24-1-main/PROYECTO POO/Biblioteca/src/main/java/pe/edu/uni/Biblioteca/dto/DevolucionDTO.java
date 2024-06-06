package pe.edu.uni.BIBLIOTECA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class DevolucionDTO {
    private int PrestamoID;
    private String CodigoAlumno;
    private int EmpleadoID;
}
