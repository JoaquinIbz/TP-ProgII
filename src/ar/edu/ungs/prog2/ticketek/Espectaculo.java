package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public class Espectaculo {
    String nombre;
    //String codigo;
    HashMap<String, LinkedList<Funcion>> funciones;  // fecha, funciones

    public Espectaculo(String nombre){
        this.nombre = nombre;
        this.funciones = new HashMap<>();
    }

    public void agregarFuncion(Funcion funcion, String fecha){
        if(this.funciones.containsKey(fecha)){
            if(this.funciones.get(fecha) != null){
                throw new RuntimeException("La fecha ya esta ocupada");
            }
        }
        LinkedList<Funcion> funciones = new LinkedList<>();
        funciones.add(funcion);
        this.funciones.put(fecha,funciones);
    }

    public boolean verificarDisponibilidad(String fecha){
        // verifica si la fecha esta disponible
        if(this.funciones.containsKey(fecha)){
            if(this.funciones.get(fecha) != null){
                return false;
            }
        }
        return true;
    }

    public LinkedList<Funcion> obtenerFunciones(String fecha){
        return null;
    }

}
