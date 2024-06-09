package pe.edu.uni.Biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class IndicadoresDTO {

	private String mes;
	private int prestamosTotales;
	private int usuariosPorMes;
	private int numeroPrestamosPorMes;
	private double prestamosPromedioPorMes;
	private int prestamosDevueltos;
	private double porcentajePrestamosDevueltos;
	private int numeroUsuarios;
	private String libroMasUtilizado;
	private int libroCantidad;
	
}
