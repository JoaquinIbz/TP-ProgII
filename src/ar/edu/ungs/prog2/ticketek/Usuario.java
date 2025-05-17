package ar.edu.ungs.prog2.ticketek;

import java.util.LinkedList;

public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private LinkedList<Entrada> entradas;

    public Usuario(String nombre, String apellido, String email, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public void comprarEntrada(String nombreEspectaculo, String fechaFuncion){

    }

    public LinkedList<Entrada> listarEntradasFuturas(String fecha){

    }

    public LinkedList<Entrada> listarEntradasPasadas(String fecha){

    }

    public LinkedList<Entrada> listarTotalEntradas(){

    }

    public boolean autenticar(String email, String contrasenia){

    }


}
