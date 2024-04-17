package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.baseDatos.DAOCategorias;
import com.bdii.stimfx.gui.FachadaGUI;
import javafx.application.Application;
import javafx.scene.Scene;

import java.util.List;

public class FachadaAplicacion {
    private FachadaAplicacion fa;
    private FachadaGUI fg;
    private FachadaBaseDatos fbd;

    private static Scene scene;
    private void pruebas_DAO(){
        List<Categoria> cats = fbd.consultarCategorias("Aventura");
        System.out.println(cats.get(0).getDescripcion());
        List<Usuario> usrs = fbd.consultarUsuarios(null, "Sara");
        System.out.println(usrs.get(0).getNombre());
    }
    public FachadaAplicacion(){
        fbd =new FachadaBaseDatos(this);
        pruebas_DAO();

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
        System.out.println("Excepcion: "+ e);
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
    
    // Funciones para crear y borrar DLC de videojuegos
    public void insertarDLC(DLC d){
        fbd.insertarDLC(d);
    }
    
    public void borrarDLC(int d){
        fbd.borrarDLC(d);
    }
    
    // Funciones relacionadas con la gestion de las plataformas. No veo necesario crear una clase
    public void insertarPlataforma(String nombre){
        fbd.insertarPlataforma(nombre);
    }
    public void borrarPlataforma(String nombre){
        fbd.borrarPlataforma(nombre);
    }
    public java.util.List<String> consultarPlataformas(String nombre){
        return fbd.consultarPlataformas(nombre);
    }
    
    // Funcion para obtener las plataformas asociadas a un videojuego.
    public java.util.List<String> consultarPlataformasVideojuego(int id_videojuego){
        return fbd.consultarPlataformasVideojuego(id_videojuego);
    }
    
    // Funciones para gestionar las plataformas de un videojuego, se podrian mostrar por pantalla
    public void insertarPlataformaVideojuego(String nombre, int id_videojuego){
        fbd.insertarPlataformaVideojuego(nombre, id_videojuego);
    }
    public void borrarPlataformaVideojuego(String nombre, int videojuego){
        fbd.borrarPlataformaVideojuego(nombre, videojuego);
    }
    
    // Funcion para obtener las categorias asociadas a un juego, se podrian mostrar por pantalla
    public java.util.List<String> consultarCategoriasVideojuego(int id_videojuego){
        return fbd.consultarCategoriasVideojuego(id_videojuego);
    }
    
    // Funciones relacionadas con los seguidores
    public void seguir(int idU1, int idU2){
        fbd.seguir(idU1, idU2);
    }
    
    public void dejarSeguir(int idU1, int idU2){
        fbd.dejarSeguir(idU1, idU2);
    }
    
    public java.util.List<Integer> consultarSeguidos(int idU1){
        return fbd.consultarSeguidos(idU1);
    }
    
    public java.util.List<Integer> consultarSeguidores(int idU2){
        return fbd.consultarSeguidores(idU2);
    }
    
    public void bloquearSeguidor(int idU1, int idU2){
        fbd.bloquearSeguidor(idU1, idU2);
    }

    //METHODS
    public boolean checkCredentials(String username, String password)
    {
        return true;
    }
}
