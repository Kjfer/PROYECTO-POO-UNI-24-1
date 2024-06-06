package pe.edu.uni.BIBLIOTECA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.BIBLIOTECA.dto.EjemplarDTO;
import pe.edu.uni.BIBLIOTECA.service.EjemplarService;

@RestController
@RequestMapping("/ejemplares")
@CrossOrigin()
public class EjemplarController {

    @Autowired
    private EjemplarService ejemplarService;

    @PutMapping("/actualizarEstado")
    public String actualizarEstadoEjemplar(@RequestBody EjemplarDTO dto) {
        return ejemplarService.actualizarEstadoEjemplar(dto);
    }
}
