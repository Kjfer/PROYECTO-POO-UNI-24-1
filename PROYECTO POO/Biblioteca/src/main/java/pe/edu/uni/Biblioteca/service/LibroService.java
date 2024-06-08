package pe.edu.uni.BIBLIOTECA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.edu.uni.BIBLIOTECA.dto.LibroDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class LibroService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //para administradores
    public LibroDTO AgregarLibro(LibroDTO dto){
        //verificar que el libro no exista mediante el ISBN
        String sql = "select count(1) filas from Libros where ISBN=?";
        int filas = jdbcTemplate.queryForObject(sql,Integer.class,dto.getISBN());
        if (filas==1){
            throw new RuntimeException("El libro que está ingresando ya está registrado.");
        }

        //crear id del libro

        //3 caracteres del autor
        ArrayList<Character> caracteres = new ArrayList<>();
        // Recorremos la cadena y añadimos los caracteres que no son espacios en blanco
        for (int i = 0; i < dto.getAutor().length(); i++) {
            char c = dto.getAutor().charAt(i);
            if (c != ' ') {
                caracteres.add(c);
            }
        }
        // Seleccionamos tres caracteres aleatorios y los convertimos a mayúsculas
        Random random = new Random();
        StringBuilder seleccionados = new StringBuilder(3); // Usamos StringBuilder para concatenar los caracteres
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(caracteres.size());
            seleccionados.append(Character.toUpperCase(caracteres.get(index)));
            caracteres.remove(index); // Evita seleccionar el mismo carácter más de una vez
        }
        String string1 = seleccionados.toString();

        //3 caracteres del titulo
        ArrayList<Character> caracteres1 = new ArrayList<>();
        // Recorremos la cadena y añadimos los caracteres que no son espacios en blanco
        for (int i = 0; i < dto.getTitulo().length(); i++) {
            char s = dto.getTitulo().charAt(i);
            if (s != ' ') {
                caracteres1.add(s);
            }
        }
        // Seleccionamos tres caracteres aleatorios
        Random random1 = new Random();
        StringBuilder seleccionados1 = new StringBuilder(3); // Usamos StringBuilder para concatenar los caracteres
        for (int i = 0; i < 3; i++) {
            int index1 = random1.nextInt(caracteres1.size());
            seleccionados1.append(caracteres1.get(index1));
            caracteres1.remove(index1); // Evita seleccionar el mismo carácter más de una vez
        }
        String string2 = seleccionados1.toString();
        //formar LibroID
        String LibroID = dto.getCDU()+string1+string2;

        //agregar libro
        sql = "insert into Libros values (?,?,?,?,?,?,?,?,?,1)";
        jdbcTemplate.update(sql,LibroID,dto.getTitulo(),dto.getAutor(), dto.getCategoría(), dto.getCDU(), dto.getAnioPublicacion(), dto.getEditorial(),dto.getISBN(),dto.getDescripcion());

        return dto;

    }

    //para administradores
    public void EliminarLibro(String LibroID){
        //verificar que el libro exista
        String sql = "select count(1) filas from Libros where LibroID=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, LibroID);
        if (filas != 1){
            throw new RuntimeException("El libro no existe.");
        }
        sql = "DELETE FROM Libros where LibroID=?";
        jdbcTemplate.update(sql, LibroID);

    }

    //para administradores
    public boolean ActualizarLibro(LibroDTO dto){
        //obtener id
        String sql = "select LibroID from Libros where ISBN=?";
        String LibroID = jdbcTemplate.queryForObject(sql, String.class, dto.getISBN());
        try {
            sql = "update Libros set Titulo=?, Autor=?, Categoria=?, CDU=?, AñoPublicacion=?, Editorial=?, ISBN=?, Descripcion=? where LibroID=?";
            jdbcTemplate.update(sql,dto.getTitulo(),dto.getAutor(),dto.getCategoría(),dto.getCDU(),dto.getAnioPublicacion(),dto.getEditorial(), dto.getISBN(), dto.getDescripcion(), LibroID);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //para administradores y bibliotecario
    public Map<String,Object> MostrarLibroPorID(String LibroID){
        //verificar que el libro exista
        String sql = "select count(1) filas from Libros where LibroID=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, LibroID);
        if (filas != 1){
            throw new RuntimeException("El libro no está registrado.");
        }
        sql = "select * from Libros where LibroID=?";
        return jdbcTemplate.queryForMap(sql,LibroID);
    }

    //para administradores y bibliotecario
    public List<Map<String,Object>> MostrarTodosLibros(){
        String sql = "select * from Libros";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String,Object>> buscarLibroPorTitulo(String Titulo){
        //verificar que el libro exista
        String sql = "select count(1) filas from Libros where Titulo=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, Titulo);
        if (filas == 0){
            throw new RuntimeException("No se encontró resultados al título ingresado.");
        }
        sql = "select Titulo,Autor,Categoria,AñoPublicacion,Editorial,ISBN,Descripcion from Libros where Titulo=?";
        return jdbcTemplate.queryForList(sql,Titulo);
    }

    public List<Map<String,Object>> buscarLibrorPorAutor(String Autor){
        //verificar que el libro exista
        String sql = "select count(1) filas from Libros where Autor=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, Autor);
        if (filas == 0){
            throw new RuntimeException("No se encontró resultados al autor ingresado.");
        }
        sql = "select Titulo,Autor,Categoria,AñoPublicacion,Editorial,ISBN,Descripcion from Libros where Autor=?";
        return jdbcTemplate.queryForList(sql,Autor);
    }

    public List<Map<String,Object>> buscarLibroPorCategoria(String Categoria){
        //verificar que el libro exista
        String sql = "select count(1) filas from Libros where Categoria=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, Categoria);
        if (filas == 0){
            throw new RuntimeException("No se encontró resultados a la categoría ingresada.");
        }
        sql = "select Titulo,Autor,Categoria,AñoPublicacion,Editorial,ISBN,Descripcion from Libros where Categoria=?";
        return jdbcTemplate.queryForList(sql,Categoria);
    }

    public List<Map<String,Object>> buscarLibroPorAnio(int AnioPublicacion){
        //verificar que el libro exista
        String sql = "select count(1) filas from Libros where AñoPublicacion=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, AnioPublicacion);
        if (filas == 0){
            throw new RuntimeException("No se encontró resultados al año de publicación ingresado.");
        }
        sql = "select Titulo,Autor,Categoria,AñoPublicacion,Editorial,ISBN,Descripcion from Libros where AñoPublicacion=?";
        return jdbcTemplate.queryForList(sql,AnioPublicacion);
    }

    public List<Map<String,Object>> buscarLibroPorEditorial(String Editorial){
        //verificar que el libro exista
        String sql = "select count(1) filas from Libros where Editorial=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, Editorial);
        if (filas == 0){
            throw new RuntimeException("No se encontró resultados a la editorial ingresada.");
        }
        sql = "select Titulo,Autor,Categoria,AñoPublicacion,Editorial,ISBN,Descripcion from Libros where Editorial=?";
        return jdbcTemplate.queryForList(sql,Editorial);
    }

    public Map<String,Object> buscarLibroPorISBN(String ISBN){
        //verificar que el libro exista
        String sql = "select count(1) filas from Libros where ISBN=?";
        int filas = jdbcTemplate.queryForObject(sql, Integer.class, ISBN);
        if (filas == 0){
            throw new RuntimeException("No se encontró resultados al ISBN ingresado.");
        }
        sql = "select Titulo,Autor,Categoria,AñoPublicacion,Editorial,ISBN,Descripcion from Libros where ISBN=?";
        return jdbcTemplate.queryForMap(sql,ISBN);
    }


}
