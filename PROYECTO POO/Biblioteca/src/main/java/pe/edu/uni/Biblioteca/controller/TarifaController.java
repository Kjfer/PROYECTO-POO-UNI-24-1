package pe.edu.uni.Biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.Biblioteca.service.TarifaService;

@RestController
@RequestMapping("/tarifas")
@CrossOrigin("")

public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    @PutMapping("/actualizar")
    public String actualizarTarifa(@RequestParam String tipo, @RequestParam double monto) {
        return tarifaService.actualizarTarifa(tipo, monto);
    }

    @GetMapping("/obtenerTarifa")
    public double obtenerTarifa(@RequestParam String tipo) {
        return tarifaService.obtenerTarifa(tipo);
    }


}
