package pe.edu.uni.BIBLIOTECA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.BIBLIOTECA.dto.PrestamoDTO;
import pe.edu.uni.BIBLIOTECA.service.PrestamoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prestamo")
@CrossOrigin("")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping("/verPendientesPorAlumno")
    public List<Map<String,Object>> verPendientesPorAlumno(@RequestParam String CodigoAlumno){
        return prestamoService.verPrestamosPendientesByAlumno(CodigoAlumno);
    }

    @GetMapping("/verPorAlumnos")
    public List<Map<String,Object>> verPorAlumno(@RequestParam String CodigoAlumno){
        return prestamoService.mostrarPrestamosbyAlumno(CodigoAlumno);
    }

    @GetMapping("/verTodos")
    public List<Map<String,Object>> verTodos(){
        return prestamoService.mostrarPrestamos();
    }

    @PostMapping("/registrar")
    public String registrar(@RequestBody PrestamoDTO dto){
        String mensaje;
        try {
            dto = prestamoService.resgitrarPrestamo(dto);
            mensaje = "Prestamo registrado.";
        }catch (Exception e){
            mensaje = "Error: "+e.getMessage();
        }

        return mensaje;
    }

    @PutMapping("/renovar")
    public String renovar(@RequestParam int PrestamoID){
        String mensaje;
        try {
            prestamoService.renovarPrestamo(PrestamoID);
            mensaje = "Prestamo renovado.";
        }catch (Exception e){
            mensaje = "Error: "+e.getMessage();
        }

        return mensaje;
    }

    @GetMapping("/historial/mes")
    public List<Map<String, Object>> historialPrestamosPorMes(@RequestParam int mes, @RequestParam int anio) {
        return prestamoService.historialPrestamosPorMes(mes, anio);
    }


}
