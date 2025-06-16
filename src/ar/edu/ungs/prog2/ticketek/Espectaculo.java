package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Espectaculo {
    private String nombre;
    private Map<String, Funcion> funciones;  // fecha, funciones
    private double totalRecaudado;

    public Espectaculo(String nombre){
    	if(nombre.equals("")) {
    		throw new RuntimeException("El nombre del espectaculo no puede estar vacio");
    	}
        this.nombre = nombre;
        this.funciones = new HashMap<>();
    }

    public void agregarFuncion(String fecha, Funcion funcion){
        if(this.funciones.containsKey(fecha)){
            throw new RuntimeException("La fecha no esta disponible.");
        }
        Funcion f = funcion;
        f.setNombreEspectaculo(this.nombre);

        this.funciones.put(fecha,funcion);
    }

    public boolean verificarDisponibilidad(String fecha){
        // verifica si la fecha esta disponible
        if(this.funciones.containsKey(fecha)) {
            throw new RuntimeException("La fecha no esta disponible.");
        }
        return true;
    }

    public Funcion obtenerFuncion(String fecha) {
        Funcion funcion = this.funciones.get(fecha);
        if(funcion == null){
            throw new RuntimeException("No hay función registrada en esa fecha");
        }
        return funcion;
    }

    public Sede obtenerSede(String nombreSede){
        for(Funcion funcion : this.funciones.values()){
            if(funcion.getSede().nombre.equals(nombreSede)){
                return funcion.getSede();
            }
        }
        throw new RuntimeException("La sede no esta registrada.");
    }


    public Funcion obtenerFuncionPorEspectaculo(String nombreEspectaculo) {
        for(Funcion funcion : this.funciones.values()){
            if(funcion.getNombreEspectaculo().equals(nombreEspectaculo)){
                return funcion;
            }
        }
        throw new RuntimeException("La funcion no esta registrada.");
    }

    public LinkedList<Funcion> obtenerTodasLasFunciones(){
        LinkedList<Funcion> listaFunciones = new LinkedList<>();
        for(Funcion funcion : this.funciones.values()){
            listaFunciones.add(funcion);
        }
        return listaFunciones;
    }

    public double calcularTotalRecaudado(){
        double total = 0;
        for(Funcion f : this.funciones.values()){
            total += f.recaudacion();
        }
        this.totalRecaudado = total;
        return this.totalRecaudado;
    }


	public Map<String, Funcion> getFunciones() {
		return funciones;
	}

    public String toString(){
        return this.nombre;
    }

	public String getNombre() {
		return nombre;
	}

	public double getTotalRecaudado() {
		return totalRecaudado;
	}
    
}
