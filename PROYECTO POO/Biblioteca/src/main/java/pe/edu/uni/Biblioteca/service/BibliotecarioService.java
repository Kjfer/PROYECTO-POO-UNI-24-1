package pe.edu.uni.BIBLIOTECA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.edu.uni.BIBLIOTECA.dto.DevolucionDTO;
import pe.edu.uni.BIBLIOTECA.dto.PrestamoDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class BibliotecarioService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void AgregarFalta(String CodigoAlumno){

        //verificar el ingreso del codigoalumno
        String sql = "select count(1) filas from Alumnos where CodigoAlumno=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, CodigoAlumno);
        if (filas != 1){
            throw new RuntimeException("El código del alumno no existe.");
        }

        //verificar los dias de demora del prestamo
        sql = "select convert(varchar(10),FechaDevolucion,103) FechaDevolucion from Devoluciones where CodigoAlumno=?";
        String FechaDevolucionD = jdbcTemplate.queryForObject(sql, String.class, CodigoAlumno);
        sql = "select convert(varchar(10),FechaDevolucion,103) FechaDevolucion from Prestamos where CodigoAlumno=?";
        String FechaDevolucionP = jdbcTemplate.queryForObject(sql, String.class, CodigoAlumno);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate FechaEstablecida = LocalDate.parse(FechaDevolucionP,formatter);
        LocalDate FechaDevuelta = LocalDate.parse(FechaDevolucionD,formatter);
        long diasDiferencia = ChronoUnit.DAYS.between(FechaDevuelta,FechaEstablecida);
        if (diasDiferencia==0) {
            throw new RuntimeException("No se encontró falta para dicho alumno. Revise el ingreso correcto de los datos.");
        }

        //fijar el peso de la falta segun los dias de demora
        sql = "select PesoFalta from Faltas where ? between MinDias and MaxDias";
        long PesoFalta = jdbcTemplate.queryForObject(sql, Long.class, diasDiferencia );

        //actualizar el numero de faltas
        sql = "update Alumnos set NumeroFaltas + "+ PesoFalta +" where CodigoAlumno=?";
        jdbcTemplate.update(sql,CodigoAlumno);

    }

    public DevolucionDTO registrarDevolucion(DevolucionDTO dto){

        //validar que el prestamo existe
        String sql = "select count(1) filas from Prestamos P where P.PrestamoID=? and P.Estado = 'NO DEVUELTO'";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, dto.getPrestamoID());
        if (filas!=1){
            throw new RuntimeException("Id de préstamo incorrecto. El préstamo no está registrado o ya ha sido cancelado.");
        }

        //validar codigo de alumno
        sql = "select count(1) filas from Prestamos P where P.CodigoAlumno=? and P.PrestamoID=?";
        filas = jdbcTemplate.queryForObject(sql, Integer.class,dto.getCodigoAlumno(),dto.getPrestamoID());
        if (filas!=1){
            throw new RuntimeException("Código de alumno incorrecto. ");
        }

        //validar empleado
        sql = "select COUNT(1) filas from Empleados E where E.EmpleadoID=?";
        filas = jdbcTemplate.queryForObject(sql, Integer.class,dto.getEmpleadoID());
        if (filas!=1){
            throw new RuntimeException("Id de empleado no existe. ");
        }

        //obtener los campos del prestamo a cancelar
        sql = "select P.EjemplarID from Prestamos P where P.PrestamoID=?";
        String EjemplarID = jdbcTemplate.queryForObject(sql, String.class, dto.getPrestamoID());
        sql = "select P.FechaPrestamo from Prestamos P where P.PrestamoID=?";
        String FechaPrestamo = jdbcTemplate.queryForObject(sql, String.class, dto.getPrestamoID());

        //registrar devolucion
        sql = "insert into Devoluciones values(?,?,?,?,?,convert(varchar(10),getdate(),103))";
        jdbcTemplate.update(sql, dto.getPrestamoID(), dto.getCodigoAlumno(), dto.getEmpleadoID(), EjemplarID, FechaPrestamo);

        //cambiar el estado del prestamo en la tabla
        sql = "update Prestamos set Estado = 'DEVUELTO' where PrestamoID=?";
        jdbcTemplate.update(sql, dto.getPrestamoID());

        //cambiar el estado del ejemplar
        sql = "update Ejemplares set Estado = 'DISPONIBLE' where EjemplarID=?";
        jdbcTemplate.update(sql, EjemplarID);

        return dto;

    }

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


}
