package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Ticketek implements ITicketek {

    String nombre;
    private HashMap<String, Usuario> usuarios;//email, usuario
    private LinkedList<Sede> sedes;
    private LinkedList<Espectaculo> espectaculos;


    // metodos del diagrama
    public Ticketek(){
    	this.usuarios = new HashMap<>();
    	this.sedes = new LinkedList<>();
    	this.espectaculos = new LinkedList<>();
    }

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
    	sedes.add(teatro);
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
        if(usuarios.containsKey(email)) {
            throw new RuntimeException("este email ya esta registrado");
        }
        if(email == null || nombre == null || apellido == null || contrasenia == null) {
            throw new RuntimeException("los datos son invalidos");
        }
        Usuario usuario = new Usuario(email, nombre, apellido, contrasenia);
        usuarios.put(email, usuario);
    }

    public void registrarEspectaculo(String nombre) {
        for(Espectaculo e : this.espectaculos){
            if(e.nombre.equals(nombre)){
                throw new RuntimeException("El nombre del espectaculo ya esta registrado.");
            }
        }
        this.espectaculos.add(new Espectaculo(nombre));
    }

    public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {

        Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        Sede s = verificarRegistroSede(sede);
        if(espectaculo.verificarDisponibilidad(fecha)){
            Funcion funcion = new Funcion(s,fecha,precioBase);
            espectaculo.agregarFuncion(fecha,funcion);
        }
    }

    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidadEntradas) {
        // vender entrada estadio
        Usuario usuario = autenticarUsuario(email,contrasenia);
        Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        Funcion funcion = espectaculo.obtenerFuncion(fecha);
        LinkedList<IEntrada> entradasVendidas = new LinkedList<>();

        if(funcion.sede instanceof Estadio){
            Estadio estadio = (Estadio) funcion.sede;
            for(int i=0 ; i<cantidadEntradas ; i++){
                Entrada entrada = estadio.venderEntrada(email,estadio.nombre,nombreEspectaculo,fecha);
                entrada.setEspectaculo(espectaculo);
                usuario.comprarEntrada(entrada,fecha);
                entradasVendidas.add(entrada);
            }
        }else{
            throw new RuntimeException("La sede de la funcion no es un estadio.");
        }
        return entradasVendidas;
    }

    private Sede verificarRegistroSede(String nombre){
        for(Sede s : this.sedes){
            if(s.nombre.equals(nombre)){
                return s;
            }
        }
        throw new RuntimeException("La sede no se encuentra registrada.");
    }

    private Usuario autenticarUsuario(String email,String contrasenia){
        Usuario usuario = this.usuarios.get(email);
        if(usuario == null){
            throw new RuntimeException("El usuario no se encuentra registrado.");
        }
        if(!usuario.getNombre().equals(email) || !usuario.getContrasenia().equals(contrasenia)){
            throw new RuntimeException("Email y/o contrasenia, incorrectas.");
        }
        return usuario;
    }

    private Espectaculo verificarRegistroEspectaculo(String nombreEspectaculo){
        for(Espectaculo e : this.espectaculos) {
            if (e.nombre.equals(nombreEspectaculo)) {
                return e;
            }
        }
        throw new RuntimeException("El espectaculo no se encuentra registrado.");
    }


    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
        /*
        Usuario usuario = autenticarUsuario(email,contrasenia);
        Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        Funcion funcion = espectaculo.obtenerFuncion(fecha);
        LinkedList<IEntrada> entradasVendidas = new LinkedList<>();

        if(funcion.sede instanceof Teatro){
            Teatro teatro = (Teatro) funcion.sede;

        }else{
            throw new RuntimeException("La sede de la funcion no es un teatro.");
        }
        return entradasVendidas;
        */
        return null;
    }

    public String listarFunciones(String nombreEspectaculo) {
        return "";
    }

    public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
        List<IEntrada> entradas = new ArrayList<>();
        for(Usuario usuario : usuarios.values()) {
        	for(Entrada entrada : usuario.listarTotalEntradas()) {
        		if(entrada.getEspectaculo().nombre.equals(nombreEspectaculo)) {
        			entradas.add(entrada);
        		}
        	}
        }
        return entradas;
    }

    public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
        List<IEntrada> entradas = new LinkedList<>();
        return entradas;
    }

    public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
    	
    	return null;
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

    public String toString(){
        return "asdasd";
    }

}
