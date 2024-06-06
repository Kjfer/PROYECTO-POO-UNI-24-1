package pe.edu.uni.BIBLIOTECA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.edu.uni.BIBLIOTECA.dto.PrestamoDTO;

@Service
public class PrestamoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String realizarPrestamo(PrestamoDTO dto) {
        // Validar que el alumno exista
        String sql = "SELECT COUNT(1) filas FROM Alumnos WHERE CodigoAlumno = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, dto.getCodigoAlumno());
        if (count != 1) {
            return "El alumno no existe";
        }

        // Validar que el empleado exista
        sql = "SELECT COUNT(1) FROM Empleados WHERE EmpleadoID = ?";
        count = jdbcTemplate.queryForObject(sql, Integer.class, dto.getEmpleadoID());
        if (count != 1) {
            return "El empleado no existe";
        }

        // Validar que el ejemplar esté disponible
        sql = "SELECT COUNT(1) FROM Ejemplares WHERE EjemplarID = ? AND Estado = 'DISPONIBLE'";
        count = jdbcTemplate.queryForObject(sql, Integer.class, dto.getEjemplarID());
        if (count != 1) {
            return "El ejemplar no está disponible";
        }

        // Realizar el préstamo
        sql = "INSERT INTO Prestamos (CodigoAlumno, EjemplarID, EmpleadoID, FechaPrestamo, FechaDevolucion, TipoPrestamo, Estado) VALUES (?, ?, ?, ?, ?, ?, 'NO DEVUELTO')";
        jdbcTemplate.update(sql, dto.getCodigoAlumno(), dto.getEjemplarID(), dto.getEmpleadoID(), dto.getFechaPrestamo(), dto.getFechaDevolucion(), dto.getTipoPrestamo());

        // Actualizar el estado del ejemplar
        sql = "UPDATE Ejemplares SET Estado = 'PRESTADO' WHERE EjemplarID = ?";
        jdbcTemplate.update(sql, dto.getEjemplarID());

        return "Préstamo realizado con éxito";
    }
}
