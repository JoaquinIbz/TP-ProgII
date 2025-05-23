package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha {
	private LocalDate fecha;									   //yy//mm/dd
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
	
	public Fecha(String fecha) {
		this.fecha = LocalDate.parse(fecha,formato);
	}
	public boolean esFutura(){
		return this.fecha.isAfter(LocalDate.now());
	}
}
