package pe.edu.uni.BIBLIOTECA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.BIBLIOTECA.dto.PenalizacionesDTO;
import pe.edu.uni.BIBLIOTECA.dto.registrarPagoPenalizacionDTO;
import pe.edu.uni.BIBLIOTECA.service.PenalizacionesService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/penalizaciones")
@CrossOrigin("")
public class PenalizacionesController {

    @Autowired
    private PenalizacionesService penalizacionesService;

    @GetMapping("/mostrarTodos")
    public List<Map<String,Object>> mostrarTodos(){
        return penalizacionesService.mostrarTodasPenalizaciones();
    }

    @GetMapping("/mostrarPorAlumno")
    public List<Map<String,Object>> mostrarPorAlumno(String CodigoAlumno){
        return penalizacionesService.mostrarPenalizacionesbyAlumno(CodigoAlumno);
    }

    @GetMapping("/mostrarPendientesPorAlumno")
    public List<Map<String,Object>> mostrarPendientesPorAlumno(String CodigoAlumno){
        return penalizacionesService.mostrarPenalizacionesPendientesbyAlumno(CodigoAlumno);
    }

    @PostMapping("/penalizar")
    public String penalizar(PenalizacionesDTO dto){
        String mensaje;
        try {
            dto = penalizacionesService.penalizarAlumno(dto);
            mensaje = "El alumno "+dto.getCodigoAlumno()+" fue penalizado.";
        }catch (Exception e){
            mensaje = "Error: "+e.getMessage();
        }

        return mensaje;
    }

    @PutMapping("/registarPago")
    public String registrarPago(registrarPagoPenalizacionDTO dto){
        String mensaje;
        try {
            dto = penalizacionesService.registrarPago(dto);
            mensaje = "Pago del alumno "+dto.getCodigoAlumno()+" registrado.";
        }catch (Exception e){
            mensaje = "Error: "+e.getMessage();
        }

        return mensaje;
    }


}
