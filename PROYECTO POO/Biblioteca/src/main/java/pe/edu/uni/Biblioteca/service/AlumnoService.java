package pe.edu.uni.Biblioteca.service;

import java.util.List;
import java.util.Map;
import java.util.function.DoubleToIntFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import pe.edu.uni.Biblioteca.dto.AlumnoDTO;
import pe.edu.uni.Biblioteca.dto.IndicadoresDTO;
import pe.edu.uni.Biblioteca.dto.LibroDTO;
import pe.edu.uni.Biblioteca.dto.PrestamoDTO;

@Service
public class AlumnoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public AlumnoDTO datosAlumnos(String codigoAlumno) {
		String sql = "SELECT Nombres nombres, Apellidos apellidos, ";
		sql += " Correo correo, Faltas faltas FROM Alumnos ";
		sql += "WHERE CodigoAlumno = ?";
		AlumnoDTO dto;
		
		try {
			dto = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<AlumnoDTO>(AlumnoDTO.class), codigoAlumno);
		} catch (Exception e) {
			dto = null;
		}
		
		return dto;
	}
	
	public List<PrestamoDTO> informesLibrosAlumno(String codigoAlumno){
		
		String sql = "select L.Título libroNombre, L.Autor libroAutor, ";
		sql += "RIGHT(P.EjemplarID, LEN(P.EjemplarID)-9) numeroLibro, ";
		sql += "P.fechaPrestamo fechaPrestamo, P.FechaDevolucion fechaDevolucion, ";
		sql += "P.Estado estado, P.TipoPrestamo tipoPrestamo FROM Prestamos P join ";
		sql += "Libros L on LEFT(P.EjemplarID, 9) = L.LibroID where P.CodigoAlumno = ?";
		List<PrestamoDTO> lista;
		lista = jdbcTemplate.query(sql, new BeanPropertyRowMapper<PrestamoDTO>(PrestamoDTO.class), codigoAlumno);
		
		return lista;
	}
	
	public List<PrestamoDTO> informesLibrosBibliotecario(int empleadoID){
		
		String sql = "SELECT L.Título nombreLibro, L.Autor libroAutor, P.FechaPrestamo";
		sql += " fechaPrestamo, P.FechaDevolucion fechaDevolucion,  P.Estado estado ";
		sql += "FROM Prestamos P join Libros L on LEFT(P.EjemplarID, 9) = L.LibroID";
		sql += " where EmpleadoID = ?";
		
		List<PrestamoDTO> lista;
		lista = jdbcTemplate.query(sql, new BeanPropertyRowMapper<PrestamoDTO>(PrestamoDTO.class), empleadoID);

		return lista;
	}
	
	public IndicadoresDTO indicadoresBibliotecarioGeneral(int empleadoID) {
		
		IndicadoresDTO bean = null;
		int numeroPrestamos = 0, numeroDevueltos;
		String sql;
		
		// Validando la cantidad de prestamos por mes
		List<IndicadoresDTO> lista = indicadoresBibliotecarioPorMes(empleadoID);
		for (IndicadoresDTO dto : lista) {
			numeroPrestamos += dto.getNumeroPrestamosPorMes();
		}
		
		// Validando la cantidad de prestamos devueltos
		sql = "select count(*) prestamosDevueltos from Prestamos WHERE EmpleadoID = ? and estado = 'DEVUELTO'";
		numeroDevueltos = jdbcTemplate.queryForObject(sql, Integer.class, empleadoID);
		
		// Setteando los datos en el dto
		bean.setPrestamosTotales(numeroPrestamos);
		bean.setPrestamosPromedioPorMes(numeroPrestamos/lista.size());
		bean.setPrestamosDevueltos(numeroDevueltos);
		bean.setPorcentajePrestamosDevueltos(numeroDevueltos/numeroPrestamos);
		
		return bean;
		
	}
	
	public List<IndicadoresDTO> indicadoresBibliotecarioPorMes(int empleadoID) {
		
		String sql = "SELECT FORMAT(CONVERT(date, FechaPrestamo, 103), 'MM/yyyy') ";
		sql += "AS mes, COUNT(*) AS numeroPrestamosPorMes WHERE FROM Prestamos GROUP BY ";
		sql += "FORMAT(CONVERT(date, FechaPrestamo, 103), 'MM/yyyy') ORDER By mes";
		
		List<IndicadoresDTO> lista;
		lista = jdbcTemplate.query(sql, new BeanPropertyRowMapper<IndicadoresDTO>(IndicadoresDTO.class), empleadoID);
		
		return lista;
		
	}
	
	public IndicadoresDTO informesAdministrador(int empleadoID) {
		
		String sql = "SELECT TOP 1 L.Título as libroMasUtilizado, COUNT(*) AS libroCantidad ";
		sql += "FROM Prestamos P JOIN Libros L ON L.LibroID = LEFT(P.EjemplarID, 9) ";
		sql += "GROUP BY L.Título ORDER BY libroCantidad DESC";
		IndicadoresDTO dto;
		
		try {
			dto = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<IndicadoresDTO>(IndicadoresDTO.class), empleadoID);
		} catch (Exception e) {
			dto = null;
		}
		
		return dto;
	}
	
}
