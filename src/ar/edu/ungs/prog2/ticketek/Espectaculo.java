package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

    public Funcion obtenerFuncion(String fecha) {
        Funcion funcion = this.funciones.get(fecha);
        if(funcion == null){
            throw new RuntimeException("No hay función registrada en esa fecha");
        }
        if(funcion.sede instanceof Estadio || funcion.sede instanceof Teatro || funcion.sede instanceof MiniEstadio) {
            return funcion;
        }
        throw new RuntimeException("La sede de la función no es válida");
    }
    public LinkedList<Funcion> obtenerTodasLasFunciones(){
        LinkedList<Funcion> listaFunciones = new LinkedList<>();
        for(Funcion funcion : this.funciones.values()){
            listaFunciones.add(funcion);
        }
        return listaFunciones;
    }


}
