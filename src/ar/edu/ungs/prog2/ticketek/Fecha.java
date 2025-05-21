package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;

public class Fecha {
	private LocalDate fecha;
	
	public Fecha(String fecha) {
		this.fecha = LocalDate.parse(fecha);
		}
}
