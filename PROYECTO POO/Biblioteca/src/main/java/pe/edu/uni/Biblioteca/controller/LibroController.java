package pe.edu.uni.BIBLIOTECA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.BIBLIOTECA.dto.LibroDTO;
import pe.edu.uni.BIBLIOTECA.service.LibroService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
@CrossOrigin("")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping("/agregar")
    public String agregar(LibroDTO dto){
        String mensaje;
        try {
            dto = libroService.AgregarLibro(dto);
            mensaje = "Libro agregado.";
        }catch (Exception e){
            mensaje = "Error: "+e.getMessage();
        }

        return mensaje;
    }

    @DeleteMapping("/eliminar")
    public String eliminar(String LibroID){
        String mensaje;
        try {
            libroService.EliminarLibro(LibroID);
            mensaje = "Libro eliminado.";
        }catch (Exception e){
            mensaje = "Error: "+e.getMessage();
        }

        return mensaje;
    }

    @PutMapping("/actualizar")
    public String actualizar(LibroDTO dto){
        String mensaje;
        boolean actualizado = libroService.ActualizarLibro(dto);
        if(actualizado){
            mensaje = "Libro actualizado.";
        }
        else {
            mensaje = "Error al actualizar ";
        }

        return mensaje;
    }

    @GetMapping("/mostrarPorID")
    public Map<String,Object> mostrarPorID(String LibroID){
        return libroService.MostrarLibroPorID(LibroID);
    }

    @GetMapping("/mostrarTodos")
    public List<Map<String,Object>> mostrarTodos(){
        return libroService.MostrarTodosLibros();
    }

    @GetMapping("mostrarPorTitulo")
    public List<Map<String,Object>> mostrarPorTitulo(String Titulo){
        return libroService.buscarLibroPorTitulo(Titulo);
    }

    @GetMapping("mostrarPorAutor")
    public List<Map<String,Object>> mostrarPorAutor(String Autor){
        return libroService.buscarLibrorPorAutor(Autor);
    }

    @GetMapping("mostrarPorCategoria")
    public List<Map<String,Object>> mostrarPorCategoria(String Categoria){
        return libroService.buscarLibroPorCategoria(Categoria);
    }

    @GetMapping("mostrarPorEditorial")
    public List<Map<String,Object>> mostrarPorEditorial(String Editorial){
        return libroService.buscarLibroPorEditorial(Editorial);
    }

    @GetMapping("mostrarPorAnio")
    public List<Map<String,Object>> mostrarPorAnio(int Anio){
        return libroService.buscarLibroPorAnio(Anio);
    }

    @GetMapping("mostrarPorISBN")
    public Map<String,Object> mostrarPorISBN(String ISBN){
        return libroService.buscarLibroPorISBN(ISBN);
    }





}
