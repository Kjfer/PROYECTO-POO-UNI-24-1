package pe.edu.uni.BIBLIOTECA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.BIBLIOTECA.service.EjemplarService;
import pe.edu.uni.BIBLIOTECA.service.FaltasService;

@RestController
@RequestMapping("/faltas")
@CrossOrigin("")
public class FaltasController {

    @Autowired
    private  FaltasService faltasService;

    @GetMapping("/porAlumno")
    public int porAlumno(String CodigoAlumno){
        return faltasService.obtenerFaltasbyAlumno(CodigoAlumno);
    }

    @PostMapping("/agregar")
    public String agregar(String CodigoAlumno){
        String mensaje;
        try {
            faltasService.AgregarFalta(CodigoAlumno);
            mensaje = "Falta agregada a alumno "+CodigoAlumno;
        }catch (Exception e){
            mensaje = "Error: "+e.getMessage();
        }

        return mensaje;
    }
}
