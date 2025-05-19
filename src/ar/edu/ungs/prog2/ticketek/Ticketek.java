package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Ticketek implements ITicketek {

    String nombre;
    private HashMap<String, Usuario> usuarios;
    private LinkedList<Sede> sedes;
    private LinkedList<Espectaculo> espectaculos;


    // metodos del diagrama
    public Ticketek(){}

    // metodos de la interfaz
    public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
    	for(Sede s : sedes) {
    		if(s.nombre.equals(nombre)) {
    			throw new RuntimeException("Ya existe una sede con este nombre");
    		}
    	}
    	if(capacidadMaxima <= 0) {
    		throw new RuntimeException("La capacidad debe ser mayor a 0");
    	}
    	Estadio estadio = new Estadio(nombre, direccion, capacidadMaxima);
    	sedes.add(estadio);
    	
    }

    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
    	//TEATRO
    	for(Sede s : sedes) {
    		if(s.nombre.equals(nombre)) {
    			throw new RuntimeException("Ya existe una sede con este nombre");
    		}
    	}
    	if(capacidadMaxima <= 0 || asientosPorFila <= 0 || sectores.length == 0 || capacidad.length == 0 || porcentajeAdicional.length == 0) {
    		throw new RuntimeException("Esos datos son invalidos");
    	}
    	Teatro teatro = new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad, porcentajeAdicional);
    }
    

    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
//   //MiniEstadio
    	for(Sede s : sedes) {
    		if(s.nombre.equals(nombre)) {
    			throw new RuntimeException("Ya existe una sede con este nombre");
    		}
    	}
    	if(capacidadMaxima <= 0 || asientosPorFila <= 0 || sectores.length == 0 || capacidad.length == 0|| porcentajeAdicional.length == 0) {
    		throw new RuntimeException("Por favor, ingrese datos validos");
    	}
    	MiniEstadio miniestadio = new MiniEstadio(nombre, direccion, capacidadMaxima, asientosPorFila, cantidadPuestos, precioConsumicion, sectores, capacidad, porcentajeAdicional);
    	sedes.add(miniestadio);
    }

    public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {

    }

    public void registrarEspectaculo(String nombre) {

    }

    public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {

    }

    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidadEntradas) {
        return List.of();
    }

    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
        return List.of();
    }

    public String listarFunciones(String nombreEspectaculo) {
        return "";
    }

    public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
        return List.of();
    }

    public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
        return List.of();
    }

    public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
        return List.of();
    }

    public boolean anularEntrada(IEntrada entrada, String contrasenia) {
        return false;
    }

    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
        return null;
    }

    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
        return null;
    }

    public double costoEntrada(String nombreEspectaculo, String fecha) {
        return 0;
    }

    public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
        return 0;
    }

    public double totalRecaudado(String nombreEspectaculo) {
        return 0;
    }

    public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
        return 0;
    }
}
