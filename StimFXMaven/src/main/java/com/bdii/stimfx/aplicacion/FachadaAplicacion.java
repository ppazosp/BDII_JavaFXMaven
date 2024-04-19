package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;

public class FachadaAplicacion {
    private FachadaAplicacion fa;
    private FachadaGUI fg;
    private FachadaBaseDatos fbd;
    private GestionUsuarios gu;

    public Usuario usuario;

    private static Scene scene;

    public static byte[] imageToBytes(String path)
    {
        try {

            File imageFile = new File(path);
            FileInputStream fis = new FileInputStream(imageFile);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Image bytesToImage(byte[] imageData) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            Image image = new Image(bis);
            bis.close();

            return image;
        } catch (IOException e) {
            System.out.println("Error converting byte array to image: " + e.getMessage());
            return null;
        }
    }







    private void pruebas_DAO(){
        List<Categoria> cats = fbd.consultarCategorias("Aventura");
        System.out.println(cats.get(0).getDescripcion());
        List<Usuario> usrs = fbd.consultarUsuarios(null, "Sara");
        System.out.println(usrs.get(0).getNombre());
    }
    public FachadaAplicacion(FachadaGUI fg){
        this.fg = fg;
        fbd =new FachadaBaseDatos(this);
        pruebas_DAO();
        gu = new GestionUsuarios(this.fg, fbd);
    }

    public void setFachadaGUI(FachadaGUI fg) {
        this.fg = fg;
    }

    public static void main(String[] args) {
        Application.launch(FachadaGUI.class, args);

    }


    public static void muestraExcepcion(String e) {
        // fg.muestraExcepcion(e);
        System.out.println("Excepcion: "+ e);
    }

    // Funcion para crear una nueva comunidad
    public void insertarEquipoCompetitivo(Comunidad c){
        fbd.insertarEquipoCompetitivo(c);
    }

    // Funcion para borrar una comunidad
    public void borrarEquipoCompetitivo(Comunidad c){
        fbd.borrarEquipoCompetitivo(c);
    }

    // Funcion para mirar comunidades en el buscador, encontrar una comunidad especifica, a partir de algo o todas si la barra esta vacía
    public java.util.List<Comunidad> consultarEquipos(String nombre){
        return fbd.consultarEquipos(nombre);
    }

    // Funcion para insertar a un usuario en una comunidad.
    // QUE SEA COMPETITIVO EL USUARIO NO ESTA IMPLEMENTADO A NIVEL BAJO (por lo menos por ahora) !!!!!!!!!!!!!!!!
    public void insertarJugadorEquipo(int id_usuario, Comunidad c){
        fbd.insertarJugadorEquipo(id_usuario, c);
    }

    // Funcion para hacer salir de una comunidad a un usuario
    public void salirJugadorEquipo(int id_usuario, Comunidad c){
        fbd.salirJugadorEquipo(id_usuario, c);
    }

    // Funcion para consultar el equipo en el que esta un usuario
    public Comunidad consultarEquipoJugador(int id_usuario){
        return fbd.consultarEquipoJugador(id_usuario);
    }

    // Funcion para consultar los jugadores que pertenecen a un equipo en concreto
    public java.util.List<Integer> consultarJugadoresEquipo(Comunidad c){
        return fbd.consultarJugadoresEquipo(c);
    }

    // Funcion para contar los miembros de un equipo
    public Integer contarMiembrosEquipo(Comunidad c) {
        return fbd.contarMiembrosEquipo(c);
    }

    // Funcion para contar la cantidad de juegos que un usario tiene en propiedad
    public Integer contarJuegosUsuario(String id_usuario){
        return fbd.contarJuegosUsuario(id_usuario);
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
    public void insertarPlataforma(String nombre, byte[] icono){
        fbd.insertarPlataforma(nombre, icono);
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
    //Lo hice para q si null->false, si true te pasa el usuario, asi puedes ir a tu perfil y eso
    public boolean checkCredentials(String username, String password)
    {
        this.usuario = gu.comprobarAutentificacion(username, password);
        return usuario != null;
    }

    public List<Videojuego> consultaVideoJuegosInicio(){
        return fbd.consultaVideoJuegosInicio();
    }

    public Videojuego proximoVideojuego(){
        return fbd.proximoVideojuego();
    }
    public int torneosGanados(Usuario u){
        return fbd.torneosGanados(u.getId());
    }
    public List<Plataforma> consultarPlataformasVideoJuego(Videojuego v){
        return fbd.consultarPlataformasVideoJuego(v.getId());
    }
}
