package pe.edu.uni.Biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.Biblioteca.service.EjemplarService;
import pe.edu.uni.Biblioteca.service.FaltasService;

@RestController
@RequestMapping("/faltas")
@CrossOrigin("")
public class FaltasController {

    @Autowired
    private  FaltasService faltasService;

    @GetMapping("/porAlumno")
    public int porAlumno(@RequestParam String CodigoAlumno){
        return faltasService.obtenerFaltasbyAlumno(CodigoAlumno);
    }

    @PutMapping("/agregar")
    public String agregar(@RequestParam String CodigoAlumno){
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
