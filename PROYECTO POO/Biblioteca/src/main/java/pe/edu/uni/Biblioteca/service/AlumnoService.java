package pe.edu.uni.Biblioteca.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import pe.edu.uni.Biblioteca.dto.AlumnoDTO;
import pe.edu.uni.Biblioteca.dto.LibroDTO;
import pe.edu.uni.Biblioteca.dto.PrestamoDTO;
import pe.edu.uni.demo01.dto.ClienteDto;

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
	
	public void mostrarIndicadores(String empleadoID){
		
		
	}
}
