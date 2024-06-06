package pe.edu.uni.BIBLIOTECA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LibroDTO {
    private String codigoLibro;
    private String titulo;
    private String autor;
    private String categoria;
    private int añoPublicacion;
    private String editorial;
    private String isbn;
    private String descripcion;
    private int cantidad;
    private String cdu; 
}
