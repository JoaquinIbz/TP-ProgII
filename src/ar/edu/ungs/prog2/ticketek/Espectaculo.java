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
        if(this.funciones.containsKey(fecha)) {
            throw new RuntimeException("La fecha no esta disponible.");
        }
        return true;
    }

    public Funcion obtenerFuncion(String fecha){
        if(this.funciones.get(fecha).sede instanceof Estadio){
            return this.funciones.get(fecha);
        }
        if(this.funciones.get(fecha).sede instanceof Teatro){
            return this.funciones.get(fecha);
        }
        if(this.funciones.get(fecha).sede instanceof MiniEstadio){
            return this.funciones.get(fecha);
        }

        throw new RuntimeException("La funcion no existe");
    }

}
