package pe.edu.uni.BIBLIOTECA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.BIBLIOTECA.dto.PrestamoDTO;
import pe.edu.uni.BIBLIOTECA.service.PrestamoService;

@RestController
@RequestMapping("/prestamos")
@CrossOrigin()
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping("/realizar")
    public String realizarPrestamo(@RequestBody PrestamoDTO dto) {
        return prestamoService.realizarPrestamo(dto);
    }
}
