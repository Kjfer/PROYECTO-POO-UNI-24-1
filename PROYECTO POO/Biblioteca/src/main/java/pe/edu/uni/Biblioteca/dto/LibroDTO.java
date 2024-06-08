package pe.edu.uni.Biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class LibroDTO {
    private String Titulo;
    private String Autor;
    private String Categoría;
    private int CDU;
    private int AnioPublicacion;
    private String Editorial;
    private String ISBN;
    private String Descripcion;
}
