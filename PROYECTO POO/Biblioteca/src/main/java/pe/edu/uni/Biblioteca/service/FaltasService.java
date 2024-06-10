package pe.edu.uni.Biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class FaltasService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //para alumno
    public int obtenerFaltasbyAlumno(String CodigoAlumno){
        //verificar que el alumno exista
        String sql="select count(1) filas from Alumnos where CodigoAlumno=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class,CodigoAlumno);
        if (filas != 1){
            throw new RuntimeException("El código del alumno no existe.");
        }
        //obtener faltas
        sql="select NumeroFaltas from Alumnos where CodigoAlumno=?";
        return jdbcTemplate.queryForObject(sql, Integer.class,CodigoAlumno);
    }

    //para administrador
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
            throw new RuntimeException("No se encontró demora en la devolución del libro. No se puede agregar la falta.");
        }
        //fijar el peso de la falta segun los dias de demora
        sql = "select PesoFalta from Faltas where ? between MinDias and MaxDias";
        long PesoFalta = jdbcTemplate.queryForObject(sql, Long.class, diasDiferencia );
        //actualizar el numero de faltas
        sql = "update Alumnos set NumeroFaltas + "+ PesoFalta +" where CodigoAlumno=?";
        jdbcTemplate.update(sql,CodigoAlumno);

    }






}
