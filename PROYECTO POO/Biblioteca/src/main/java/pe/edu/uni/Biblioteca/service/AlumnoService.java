package pe.edu.uni.Biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.Biblioteca.dto.AlumnoDTO;

@Service
public class AlumnoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public AlumnoDTO datosAlumno(String codigoAlumno) {
		
		String sql = "select CodigoAlumno, Nombres, Apellidos, ";
		sql += "Correo, NumeroFaltas from Alumnos ";
		sql += "WHERE CodigoAlumno = ?";
		AlumnoDTO dto;
		try {
			dto = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<AlumnoDTO>(AlumnoDTO.class), codigoAlumno);
		} catch (Exception e) {
			dto = null;
		}
		
		return dto;
	}
	
	public informeUsuario
	
}
