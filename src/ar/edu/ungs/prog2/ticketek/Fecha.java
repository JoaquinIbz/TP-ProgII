package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha {
	private LocalDate fecha;									   //yy//mm/dd
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
	
	public Fecha(String fecha) {
		if(fecha.equals("")) {
			throw new RuntimeException("La fecha esta vacia");
		}
		this.fecha = LocalDate.parse(fecha,formato);
	}
	public boolean esFutura(){
		return this.fecha.isAfter(LocalDate.now());
	}
	public boolean esPasada() {
		return this.fecha.isBefore(LocalDate.now());
	}
	public String toString(){
		return this.fecha.format(formato);
	}
}
