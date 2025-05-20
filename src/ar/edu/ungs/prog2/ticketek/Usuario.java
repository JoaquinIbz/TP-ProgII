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
        this.entradas = new LinkedList<>();
    }

    public void comprarEntrada(String nombreEspectaculo, String fechaFuncion){

    }

    public LinkedList<Entrada> listarEntradasFuturas(String fecha){
        return null;
    }

    public LinkedList<Entrada> listarEntradasPasadas(String fecha){
        return null;
    }

    public LinkedList<Entrada> listarTotalEntradas(){
        return null;
    }

    public boolean autenticar(String email, String contrasenia){
        if(!this.email.equals(email)) {
            throw new RuntimeException("El email es incorrecto.");
        }
        if(!this.contrasenia.equals(contrasenia)){
            throw new RuntimeException("La contrasenia es incorrecta.");
        }
        return this.email.equals(email) && this.contrasenia.equals(contrasenia);
    }




}
