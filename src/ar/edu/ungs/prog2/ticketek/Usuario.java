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
		return entradas;
        return null;
    }

    public LinkedList<Entrada> listarEntradasPasadas(String fecha){
		return null;
        return null;
    }

    public LinkedList<Entrada> listarTotalEntradas(){
		return null;
        return null;
    }

    public boolean autenticar(String email, String contrasenia){
		return false;
        return true;
    }


}
