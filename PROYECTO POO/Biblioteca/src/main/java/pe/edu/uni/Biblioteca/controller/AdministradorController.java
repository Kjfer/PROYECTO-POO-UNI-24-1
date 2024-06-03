package pe.edu.uni.Biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.Biblioteca.dto.PenalizacionesDTO;
import pe.edu.uni.Biblioteca.dto.registrarPagoPenalizacionDTO;
import pe.edu.uni.Biblioteca.service.AdministradorService;

@RestController
@RequestMapping("/administrador")
@CrossOrigin()
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    @PostMapping("/penalizar")
    public String penalizar(@RequestBody PenalizacionesDTO dto){
        String mensaje;
        try{
            dto = administradorService.penalizarAlumno(dto);
            mensaje = "Proceso realizado con éxito. Se cambió el estado del alumno "+dto.getCodigoAlumno();
        }catch (Exception e){
            mensaje = "Error: "+ e.getMessage();
        }

        return mensaje;
    }

    @PutMapping("/registrarPago")
    public String registrarPago(@RequestBody registrarPagoPenalizacionDTO dto){
        String mensaje;
        try {
            dto = administradorService.registrarPago(dto);
            mensaje = "Pago registrado con éxito. Se actualizó el estado del alumno "+dto.getCodigoAlumno();
        }catch (Exception e){
            mensaje = "Error: "+ e.getMessage();
        }

        return mensaje;
    }

}
