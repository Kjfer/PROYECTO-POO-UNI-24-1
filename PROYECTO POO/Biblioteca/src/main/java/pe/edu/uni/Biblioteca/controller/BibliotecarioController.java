package pe.edu.uni.Biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.Biblioteca.dto.DevolucionDTO;
import pe.edu.uni.Biblioteca.service.BibliotecarioService;

@RestController
@RequestMapping("/bibliotecario")
@CrossOrigin()
public class BibliotecarioController {

    @Autowired
    private BibliotecarioService bibliotecarioService;

    @PostMapping("/devolver")
    public String devolver(@RequestBody DevolucionDTO dto){
        String mensaje;
        try{
            dto = bibliotecarioService.registrarDevolucion(dto);
            mensaje = "Se registró con éxito la devolución del préstamo "+dto.getPrestamoID();
        }catch (Exception e){
            mensaje = "Error: "+ e.getMessage();
        }
        return mensaje;
    }

    @PutMapping("/agregarFalta")
    public String agregarFalta(@RequestBody String CodigoAlumno){
        String mensaje;
        try{
            bibliotecarioService.AgregarFalta(CodigoAlumno);
            mensaje = "Se registró la falta del alumno "+ CodigoAlumno;
        }catch (Exception e){
            mensaje = "Error: "+ e.getMessage();
        }

        return mensaje;
    }

}
