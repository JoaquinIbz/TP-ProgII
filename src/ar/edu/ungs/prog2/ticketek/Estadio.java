package ar.edu.ungs.prog2.ticketek;

public class Estadio extends Sede {

    public Estadio(String nombre, String direccion, int capacidadMax) {//SAQUE EL PRECIO UNICO 
        super(nombre, direccion, capacidadMax);
        this.sectores = new String[0];
        this.sectores[0] = "CAMPO";
    }
}
