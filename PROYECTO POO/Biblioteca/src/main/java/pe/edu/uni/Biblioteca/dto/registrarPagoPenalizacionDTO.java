package pe.edu.uni.Biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class registrarPagoPenalizacionDTO {
    private int PenalizacionID;
    private String CodigoAlumno;
    private int EmpleadoID;
    private double Monto;
}
