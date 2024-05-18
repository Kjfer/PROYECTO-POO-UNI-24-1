package pe.edu.uni.Biblioteca.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HISTORIALPRESTAMOS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_historial;
    private int id_usuario;
    private int id_libro;
    private Date fecha_prestamo;
    private Date fecha_devolucion;
}
