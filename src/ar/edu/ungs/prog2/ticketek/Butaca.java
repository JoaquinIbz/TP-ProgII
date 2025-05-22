package ar.edu.ungs.prog2.ticketek;

public class Butaca {

    private int fila;
    private int asiento;


    public Butaca(int fila, int asiento){
        this.fila = fila;
        this.asiento = asiento;
    }

    public String toString(){
        return "fila: "+this.fila+", asiento: "+this.asiento;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }
}
