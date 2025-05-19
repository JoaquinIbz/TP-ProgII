package ar.edu.ungs.prog2.ticketek;

public class Estadio extends Sede {

    Sector sector;

    public Estadio(String nombre, String direccion, int capacidadMax) {//SAQUE EL PRECIO UNICO 
        super(nombre, direccion, capacidadMax);
        this.sector = new Sector(0,"CAMPO",0,0);
    }
}
