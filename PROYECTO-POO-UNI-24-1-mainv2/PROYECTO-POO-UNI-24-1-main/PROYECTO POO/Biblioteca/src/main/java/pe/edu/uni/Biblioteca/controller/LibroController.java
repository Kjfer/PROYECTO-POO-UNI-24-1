package pe.edu.uni.BIBLIOTECA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.BIBLIOTECA.dto.LibroDTO;
import pe.edu.uni.BIBLIOTECA.service.LibroService;

import java.util.List; // Añadir esta importación

@RestController
@RequestMapping("/libros")
@CrossOrigin()
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/buscar")
    public List<LibroDTO> buscarLibros(@RequestParam String criterio, @RequestParam String valor) {
        return libroService.buscarLibros(criterio, valor);
    }

    @PostMapping("/agregar")
    public String agregarLibro(@RequestBody LibroDTO dto) {
        return libroService.agregarLibroYCrearEjemplares(dto);
    }

    @PutMapping("/actualizar")
    public String actualizarLibro(@RequestBody LibroDTO dto) {
        return libroService.actualizarLibro(dto);
    }

    @DeleteMapping("/eliminar")
    public String eliminarLibro(@RequestParam String codigoLibro) {
        return libroService.eliminarLibro(codigoLibro);
    }
}
