package pe.edu.uni.BIBLIOTECA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.edu.uni.BIBLIOTECA.dto.AlumnoDTO;

import java.util.List;
import java.util.Map;

@Service
public class AlumnoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //para bibliotecario y administrador
    public List<Map<String,Object>> obtenerTodosAlumnos(){
        String sql = "select * from Alumnos";
        return jdbcTemplate.queryForList(sql);
    }

    //para el Alumno
    public Map<String,Object> obtenerDatosPorCodigo(String CodigoAlumno){
        String sql = "select CodigoAlumno, Nombres, Apellidos, Correo, Usuario, Contraseña Clave  from Alumnos where CodigoAlumno=?";
        return jdbcTemplate.queryForMap(sql, CodigoAlumno);
    }

    //para bibliotecario y administrador
    public boolean AgregarAlumno(AlumnoDTO dto){
        String sql = "insert into Alumnos(CodigoAlumno,Nombres,Apellidos,Correo,Usuario,Contraseña,FechaRegistro,NumeroFaltas,Estado) values(?,?,?,?,?,?,convert(varchar(10),getdate(),103),0,'ACTIVO')";
        try {
            jdbcTemplate.update(sql,dto.getCodigoAlumno(),dto.getNombres(),dto.getApellidos(),dto.getCorreo(),dto.getUsuario(),dto.getClave());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //para alumno
    public boolean ActualizarAlumno(AlumnoDTO dto){
        try {
            String sql = "update Alumnos set Nombres=?, Apellidos=?, Correo=?, Usuario=?, Contraseña=? where CodigoAlumno=?";
            jdbcTemplate.update(sql,dto.getNombres(),dto.getApellidos(),dto.getCorreo(),dto.getUsuario(),dto.getClave(),dto.getCodigoAlumno());
            return true;
        }catch (Exception e){
            return false;
        }

    }

    //para administradores
    public boolean eliminarAlumno(String CodigoAlumno) {
        try {
            String sql = "DELETE FROM Alumnos where CodigoAlumno=?";
            jdbcTemplate.update(sql, CodigoAlumno);
            return true;
        } catch(Exception e) {
            return false;
        }
    }



}
