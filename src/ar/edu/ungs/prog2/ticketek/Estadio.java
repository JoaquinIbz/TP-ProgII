package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public class Estadio extends Sede {

    String sector;
    HashMap<String,Entrada> entradasVendidas; // email, entrada

    public Estadio(String nombre, String direccion, int capacidadMax) {
        super(nombre, direccion, capacidadMax);
        this.sector = "CAMPO";
        this.capacidadActual = capacidadMax;
        this.entradasVendidas = new HashMap<>();
    }

    @Override
    public Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha){
        Entrada entrada = new Entrada(nombreSede, nombreEspectaculo, fecha);
        if(!this.entradasVendidas.containsKey(email)){
            // TERMINAR, HACER CODIGO DE LA ENTRADA. ESO SE ENCARGA CADA SEDE.
            return entrada;
        }
        return entrada;
    }


}
