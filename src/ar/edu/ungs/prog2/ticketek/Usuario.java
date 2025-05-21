package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private HashMap<String, LinkedList<Entrada>> entradasCompradas; // fecha, entrada

    public Usuario(String nombre, String apellido, String email, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.entradasCompradas = new HashMap<>();
    }

    public void comprarEntrada(Entrada entrada, String fecha){

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
        if(!this.email.equals(email) || !this.contrasenia.equals(contrasenia)){
            throw new RuntimeException("Email y/o contrasenia, invalidas.");
        }
        return true;
    }

    public String getNombre(){
        return this.nombre;
    }
    public String getApellido(){
        return this.apellido;
    }
    public String getEmail(){
        return this.email;
    }




}
