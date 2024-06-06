package pe.edu.uni.BIBLIOTECA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.edu.uni.BIBLIOTECA.dto.LibroDTO;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LibroDTO> buscarLibros(String criterio, String valor) {
        String sql = "";
        if (criterio.equalsIgnoreCase("nombre")) {
            sql = "SELECT * FROM Libros WHERE Título LIKE ?";
        } else if (criterio.equalsIgnoreCase("autor")) {
            sql = "SELECT * FROM Libros WHERE Autor LIKE ?";
        } else if (criterio.equalsIgnoreCase("codigo")) {
            sql = "SELECT * FROM Libros WHERE LibroID LIKE ?";
        }
        return jdbcTemplate.query(sql, new Object[]{"%" + valor + "%"}, 
                (rs, rowNum) -> new LibroDTO(rs.getString("LibroID"), rs.getString("Título"),
                        rs.getString("Autor"), rs.getString("Categoria"), rs.getInt("AñoPublicacion"),
                        rs.getString("Editorial"), rs.getString("ISBN"), rs.getString("Descripcion"),
                        rs.getInt("Cantidad"), rs.getString("CDU")));
    }

    public String agregarLibroYCrearEjemplares(LibroDTO dto) {
        // Insertar libro en la tabla de libros
        String sql = "INSERT INTO Libros (LibroID, Título, Autor, Categoria, CDU, AñoPublicacion, Editorial, ISBN, Descripcion, Cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, dto.getCodigoLibro(), dto.getTitulo(), dto.getAutor(), dto.getCategoria(),
                dto.getCdu(), dto.getAñoPublicacion(), dto.getEditorial(), dto.getIsbn(), dto.getDescripcion(), dto.getCantidad());

        // Insertar ejemplares en la tabla de ejemplares
        for (int i = 0; i < dto.getCantidad(); i++) {
            String ejemplarID = generarCodigoEjemplar(dto.getCodigoLibro(), i + 1);
            sql = "INSERT INTO Ejemplares (EjemplarID, LibroID, Estado) VALUES (?, ?, 'DISPONIBLE')";
            jdbcTemplate.update(sql, ejemplarID, dto.getCodigoLibro());
        }

        return "Libro y ejemplares agregados con éxito";
    }

    public String actualizarLibro(LibroDTO dto) {
        String sql = "UPDATE Libros SET Título=?, Autor=?, Categoria=?, CDU=?, AñoPublicacion=?, Editorial=?, ISBN=?, Descripcion=?, Cantidad=? WHERE LibroID=?";
        jdbcTemplate.update(sql, dto.getTitulo(), dto.getAutor(), dto.getCategoria(), dto.getCdu(), dto.getAñoPublicacion(),
                dto.getEditorial(), dto.getIsbn(), dto.getDescripcion(), dto.getCantidad(), dto.getCodigoLibro());
        return "Libro actualizado con éxito";
    }

    public String eliminarLibro(String codigoLibro) {
        String sql = "DELETE FROM Ejemplares WHERE LibroID=?";
        jdbcTemplate.update(sql, codigoLibro);
        sql = "DELETE FROM Libros WHERE LibroID=?";
        jdbcTemplate.update(sql, codigoLibro);
        return "Libro y sus ejemplares eliminados con éxito";
    }

    private String generarCodigoEjemplar(String codigoLibro, int numeroEjemplar) {
        return codigoLibro + String.format("%03d", numeroEjemplar);
    }
}
