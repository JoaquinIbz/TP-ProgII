package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public class Espectaculo {
    String nombre;
    private HashMap<String, Funcion> funciones;  // fecha, funciones

    public Espectaculo(String nombre){
        this.nombre = nombre;
        this.funciones = new HashMap<>();
    }

    public void agregarFuncion(String fecha, Funcion funcion){
        if(this.funciones.containsKey(fecha)){
            throw new RuntimeException("La fecha no esta disponible.");
        }
        this.funciones.put(fecha,funcion);
    }

    public boolean verificarDisponibilidad(String fecha){
        // verifica si la fecha esta disponible
        if(!this.funciones.containsKey(fecha)) {
            return true;
        }
        throw new RuntimeException("La fecha no esta disponible.");
    }

    public Funcion obtenerFuncion(String fecha){
        Funcion funcion = this.funciones.get(fecha);
        if (funcion == null) {
            throw new RuntimeException("No hay función registrada para la fecha: " + fecha);
        }
        return funcion;
    }

}
