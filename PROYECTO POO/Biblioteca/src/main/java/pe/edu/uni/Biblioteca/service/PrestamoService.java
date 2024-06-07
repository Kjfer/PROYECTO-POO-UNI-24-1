package pe.edu.uni.BIBLIOTECA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.edu.uni.BIBLIOTECA.dto.PrestamoDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
@Service
public class PrestamoService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //para alumno y bibliotecario y administrador
    public List<Map<String, Object>> verPrestamosPendientesByAlumno(String CodigoAlumno){
        String sql ="select EjemplarID,FechaPrestamo,FechaDevolucion,TipoPrestamo from Prestamos where CodigoAlumno=? and Estado='NO DEVUELTO'";
        return jdbcTemplate.queryForList(sql,CodigoAlumno);
    }

    //para bibliotecario y administrador
    public List<Map<String,Object>> mostrarPrestamosbyAlumno(String CodigoAlumno){
        String sql = "select PrestamoID,EjemplarID,EmpleadoID,FechaPrestamo,FechaDevolucion,TipoPrestamo,Estado from Prestamos where CodigoAlumno=?";
        return jdbcTemplate.queryForList(sql,CodigoAlumno);

    }

    //para bibliotecario
    public List<Map<String,Object>> mostrarPrestamos(){
        String sql = "select * from Prestamos";
        return jdbcTemplate.queryForList(sql);
    }

    //para bibliotecarios
    public PrestamoDTO resgitrarPrestamo(PrestamoDTO dto){
        // Validar que el alumno exista
        String sql = "SELECT COUNT(1) filas FROM Alumnos WHERE CodigoAlumno = ?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, dto.getCodigoAlumno());
        if (filas != 1) {
            throw new RuntimeException( "El código del alumno no existe.");
        }
        // Validar que el empleado exista
        sql = "SELECT COUNT(1) filas FROM Empleados WHERE EmpleadoID = ?";
        filas = jdbcTemplate.queryForObject(sql, Integer.class, dto.getEmpleadoID());
        if (filas != 1) {
            throw new RuntimeException( "El id del empleado no existe.");
        }
        // Validar que el ejemplar esté disponible
        sql = "SELECT COUNT(1) filas FROM Ejemplares WHERE EjemplarID = ? AND Estado = 'DISPONIBLE'";
        filas = jdbcTemplate.queryForObject(sql, Integer.class, dto.getEjemplarID());
        if (filas != 1) {
            throw new RuntimeException("El ejemplar no está disponible para ser prestado.");
        }
        // Realizar el préstamo
        sql = "INSERT INTO Prestamos (CodigoAlumno, EjemplarID, EmpleadoID, FechaPrestamo, FechaDevolucion, TipoPrestamo, Estado) VALUES (?, ?, ?, convert(varchar(10),getdate(),103), convert(varchar(10),DATEADD(day, (select Valor from Guia where Parametro='Tiempo de devolucion en dias') , GETDATE()),103), ?, 'NO DEVUELTO')";
        jdbcTemplate.update(sql, dto.getCodigoAlumno(), dto.getEjemplarID(), dto.getEmpleadoID(), dto.getTipoPrestamo());
        //actualizar estado del ejemplar
        sql = "UPDATE Ejemplares SET Estado = 'PRESTADO' WHERE EjemplarID = ?";
        jdbcTemplate.update(sql, dto.getEjemplarID());

        return dto;
    }

    //para alumnos
    public void renovarPrestamo(int PrestamoID){
        //verificar que el prestamo no se ha devuelto
        String sql = "select count(1) filas from Prestamos where PrestamoID=? and Estado='NO DEVUELTO'";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class,PrestamoID);
        if (filas != 1){
            throw new RuntimeException("No se puede renovar un préstamo ya devuelto.");
        }
        //verificar que el tipo de prestamo sea para llevar
        sql = "select count(1) filas from Prestamos where PrestamoID=? and TipoPrestamo='LLEVAR'";
        filas = jdbcTemplate.queryForObject(sql, Integer.class,PrestamoID);
        if (filas != 1){
            throw new RuntimeException("Los préstamos para leer en sala no se pueden renovar.");
        }
        //obtener la cantidad maxima de veces de renovacion
        sql = "select Valor from Guia where Parametro = 'Veces maximas de renovacion'";
        int Valor = jdbcTemplate.queryForObject(sql, Integer.class);
        //obtener el tiempo de devolucion
        sql = "select Valor DiasMax from Guia where Parametro = 'Tiempo de devolucion en dias'";
        long DiasMax = jdbcTemplate.queryForObject(sql, long.class);
        //verificar que no se haya renovado ya dos veces
        sql = "select convert(varchar(10),FechaPrestamo,103) FechaPrestamo from Prestamos where PrestamoID=?";
        String FechaPrestamo = jdbcTemplate.queryForObject(sql, String.class, PrestamoID);
        sql = "select convert(varchar(10),FechaDevolucion,103) FechaDevolucion from Prestamos where PrestamoID=?";
        String FechaDevolucion = jdbcTemplate.queryForObject(sql, String.class, PrestamoID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate FechaP = LocalDate.parse(FechaPrestamo,formatter);
        LocalDate FechaD = LocalDate.parse(FechaDevolucion,formatter);
        long diasDiferencia = ChronoUnit.DAYS.between(FechaP,FechaD);
        if(diasDiferencia==Valor*DiasMax){
            throw new RuntimeException("Ya no puede renovar este préstamo.");
        }
        //renovar el prestamo
        LocalDate NuevaFechaD = FechaD.plusDays(DiasMax);
        sql = "update Prestamos set FechaDevolucion=convert(varchar(10),DATEADD(day, (select Valor from Guia where Parametro='Tiempo de devolucion en dias') ,convert (DATE,(select FechaDevolucion from Prestamos where PrestamoID=?),103)),103) where PrestamoID=?";
        jdbcTemplate.update(sql,PrestamoID,PrestamoID);

    }

}
