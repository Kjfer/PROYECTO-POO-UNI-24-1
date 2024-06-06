package pe.edu.uni.BIBLIOTECA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.edu.uni.BIBLIOTECA.dto.EjemplarDTO;

@Service
public class EjemplarService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String actualizarEstadoEjemplar(EjemplarDTO dto) {
        // Validar que el ejemplar exista
        String sql = "SELECT COUNT(1) FROM Ejemplares WHERE EjemplarID = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, dto.getEjemplarID());
        if (count != 1) {
            return "El ejemplar no existe";
        }

        // Actualizar el estado del ejemplar
        sql = "UPDATE Ejemplares SET Estado = ? WHERE EjemplarID = ?";
        jdbcTemplate.update(sql, dto.getEstado(), dto.getEjemplarID());

        return "Estado del ejemplar actualizado con éxito";
    }
}
