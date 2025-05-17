package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Sector {

    int numero;
    String tipoSector;
    int cantFilas;
    HashMap<Integer,Integer> asientosPorFila; // fila, asiento

    public Sector(int numero, String tipoSector, int filas, int asientos){}

}
