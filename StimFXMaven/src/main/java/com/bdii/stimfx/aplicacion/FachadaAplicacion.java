package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;
import javafx.application.Application;
import javafx.scene.Scene;

public class FachadaAplicacion {

    private FachadaGUI fg;
    private FachadaBaseDatos fbd;

    private static Scene scene;

    public FachadaAplicacion() {
        //fbd = new FachadaBaseDatos(this);
    }

    public void setFachadaGUI(FachadaGUI fg) {
        this.fg = fg;
    }

    public static void main(String[] args) {
        FachadaGUI fg = new FachadaGUI();
        Application.launch(FachadaGUI.class, args);
    }

    public static void muestraExcepcion(String e) {
        // fg.muestraExcepcion(e);
    }
    
    // Funcion para consultar videojuegos a partir de un nombre. Utilizar en scroll del main
    public java.util.List<Videojuego> consultarVideojuegos(String n){ 
        return fbd.consultaVideojuegos(n);
    }

    // Funcion para insertar un nuevo torneo. Por ahora, las fechas se calculan solas (se puede mirar para cambiarlo). El ganador se insertara mas tarde.
    public void insertarTorneo(Torneo t){
        fbd.insertarTorneo(t);
    }
    
    // Escribir una nueva reseña
    public void insertarReseña(Reseña r){
        fbd.insertarReseña(r);
    }
    
    // Funcion para borrar un usuario a partir de un id.
    public void borrarUsuario(int id){
        fbd.borrarUsuario(id);
    }
    
    // Funcion para insertar la compra de un juego. Consultar tema de dinero. No veo necesario crear una clase compras. Pasar parametros con getters
    public void insertarCompra(int id_videojuego, int id_usuario, int precio){
        fbd.insertarCompra(id_videojuego, id_usuario, precio);
    }
    
    // Funciones relacionadas con la gestion de las categorias de los juegos
    public void insertarCategoria(Categoria c){
        fbd.insertarCategoria(c);
    }
    
    public void borrarCategoria(String nombre){
        fbd.borrarCategoria(nombre);
    }
    
    public java.util.List<Categoria> consultarCategorias(String nombre){
        return fbd.consultarCategorias(nombre);
    }
    
    // Funcion para buscar usuarios en la gestion de usuarios
    public java.util.List<Usuario> consultarUsuarios(Integer id, String nombre){
        return fbd.consultarUsuarios(id, nombre);
    }

    //METHODS
    public boolean checkCredentials(String username, String password)
    {
        return true;
    }
}
