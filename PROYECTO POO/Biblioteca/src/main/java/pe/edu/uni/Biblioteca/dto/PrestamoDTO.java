package pe.edu.uni.Biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class PrestamoDTO {
    private String codigoAlumno;
    private String libroID;
    private String libroNombre;
    private String libroAutor;
    private String numeroLibro;
    private String ejemplarID;
    private int empleadoID;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String tipoPrestamo;
    private String estado;
}
