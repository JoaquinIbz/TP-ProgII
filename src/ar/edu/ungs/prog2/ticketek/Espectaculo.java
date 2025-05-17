package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public class Espectaculo {
    String nombre;
    String codigo;
    HashMap<String, LinkedList<Funcion>> funciones;

    public Espectaculo(String nombre, String codigo){
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public void agregarFuncion(Funcion funcion, String fecha){

    }

    public boolean verificarDisponibilidad(String fecha, String nombreSede){

    }

    public LinkedList<Funcion> obtenerFunciones(String fecha){

    }

}
