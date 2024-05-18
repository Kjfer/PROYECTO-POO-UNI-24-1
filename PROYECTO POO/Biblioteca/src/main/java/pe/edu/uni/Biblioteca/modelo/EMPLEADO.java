package pe.edu.uni.Biblioteca.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EMPLEADO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idempleado;
    private String apellido;
    private String nombre;
    private String direccion;
    private String email;
    private String usuario;
    private String clave;
}
