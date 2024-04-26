package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class FachadaAplicacion {
    private FachadaAplicacion fa;
    private FachadaGUI fg;
    private final FachadaBaseDatos fbd;
    private final GestionUsuarios gu;
    private final GestionVideojuegos gv;
    private final GestionDemo gdem;
    private final GestionDLC gd;

    private final GestionComunidad gc;
    private final GestionTorneos gt;
    private final GestionCompra gcom;
    private final GestionPlataforma gpl;
    private final GestionReseña gr;

    private final GestionCategoria gcat;

    public Usuario usuario;

    private static Scene scene;

    public static Image pathToImage(String path) {
        try {
            File imageFile = new File(path);
            FileInputStream inputStream = new FileInputStream(imageFile);
            return new Image(inputStream);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
            e.printStackTrace();
            return null;
        }
    }
    public static byte[] pathToBytes(String path) {
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
    public static byte[] imageToBytes(Image image) {
        if (image == null) return null;

        try {
            // Convert JavaFX Image to BufferedImage
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

            // Create ByteArrayOutputStream to hold the encoded image data
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Write the BufferedImage to the ByteArrayOutputStream with compression
            ImageIO.write(bufferedImage, "png", outputStream);

            // Get the byte array from the ByteArrayOutputStream
            byte[] byteArray = outputStream.toByteArray();

            // Close the ByteArrayOutputStream
            outputStream.close();

            return byteArray;
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            return null;
        }
    }
    public static Image bytesToImage(byte[] imageData) {
        if (imageData == null) return null;

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


    public FachadaAplicacion(FachadaGUI fg) {
        this.fg = fg;
        fbd = new FachadaBaseDatos(this);
        gu = new GestionUsuarios(this.fg, fbd);
        gv = new GestionVideojuegos(this.fg, fbd);
        gd = new GestionDLC(this.fg, fbd);
        gdem = new GestionDemo(this.fg, fbd);
        gc = new GestionComunidad(this.fg, fbd);
        gt = new GestionTorneos(this.fg, fbd);
        gcom = new GestionCompra(this.fg, fbd);
        gpl = new GestionPlataforma(this.fg, fbd);
        gr = new GestionReseña(this.fg, fbd);
        gcat = new GestionCategoria(this.fg, fbd);

        pruebas_DAO();
    }

    private void pruebas_DAO(){

        ///////////////////NO LA DESCOMENTEIS GRACIAS GRACIAS GRACIAS///////////////////////fbd.hashAllPasswords();


        //List<Categoria> cats = fbd.consultarCategorias("Aventura");
        //System.out.println(cats.get(0).getDescripcion());
        //List<Usuario> usrs = fbd.consultarUsuarios(null, "Sara");
        //System.out.println(usrs.get(0).getNombre());
        //<Torneo> torneos = consultarTorneos("");
        //System.out.println(torneos.size());

        //Comunidad c = new Comunidad("Racing de Ferrol", FachadaAplicacion.pathToImage("/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/racing.png"));
        //fbd.insertarComunidad(c);
        //c = new Comunidad("Barcelona", FachadaAplicacion.pathToImage("/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/barsa.png"));
        //fbd.insertarComunidad(c);
        //c = new Comunidad("Chelsea", FachadaAplicacion.pathToImage("/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/chelsea.png"));
        //fbd.insertarComunidad(c);

        //Demo d = new Demo("RobinRun", 4, 2024, FachadaAplicacion.pathToImage("/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/RobinRun.png"), "sara");
        //fbd.insertarDemo(d);
        //d = new Demo("RobinRun", 5, 2024, FachadaAplicacion.pathToImage("/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/RobinRun.png"), "sara");
       //fbd.insertarDemo(d);
        //insertarPlataforma("PC", "/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/PC.png");
        //insertarPlataforma("PlayStation", "/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/PlayStation.png");
        //insertarPlataforma("Xbox", "/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/Xbox.png");
        //insertarPlataforma("Android", "/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/Android.png");
        //insertarPlataforma("Apple", "/home/p3peat/Documents/POO/BDII_JavaFXMaven/StimFXMaven/src/imagenes/Apple.png");
        //fbd.inicializarbd();
    }

    public void setFachadaGUI(FachadaGUI fg) {
        this.fg = fg;
    }

    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDirectory);
        Application.launch(FachadaGUI.class, args);

    }

    public static void muestraExcepcion(String e) {
        // fg.muestraExcepcion(e);
        System.out.println("Excepcion: "+ e);
    }



    // FUNCIONES RELACIONADAS CON GESTIONVIDEOJUEGOS
    // -----------------------------------------------------------------------

    // Funcion para insertar videojuegos en la base
    public void insertarVideojuego(Videojuego v){
        gv.insertarVideojuego(v);
    }
    // Funcion para borrar un videojuego en la base
    public void borrarVideojuego(Videojuego v) {
        gv.borrarVideojuego(v);
    }
    // Funcion para consultar videojuegos a partir de un nombre
    public java.util.List<Videojuego> consultarVideojuegos(String n){
        return gv.consultarVideojuegos(n);
    }
    // Funcion para buscar un unico videojuego a partir de un nombre
    public Videojuego consultarVideojuego(String n){
        return gv.consultarVideojuego(n);
    }
    // Funcion para obtener las plataformas asociadas a un videojuego.
    public List<Plataforma> consultarPlataformasVideojuego(Videojuego v){
        return gv.consultarPlataformasVideoJuego(v);
    }
    // Funcion para obtener el proximo videojuego que saldra a la venta
    public Videojuego proximoVideojuego(){
        return gv.proximoVideojuego();
    }
    // Funcion para obtener los 3 videojuegos mas vendidos del momento
    public List<Videojuego> consultaVideoJuegosInicio(){
        return gv.consultaVideoJuegosInicio();
    }
    // Funcion para actualizar el numero de descargas de un videojuego actualmente.
    // Hace un set en videojuego, USAR ANTES DE ENSEÑAR
    public void consultarNumeroDescargas(Videojuego v){
        gv.consultarNumeroDescargas(v);
    }
    // Funcion para obtener las categorias asociadas a un juego, se podrian mostrar por pantalla
    public java.util.List<String> consultarCategoriasVideojuego(Videojuego v){
        return gv.consultarCategoriasVideojuego(v);
    }

    // -----------------------------------------------------------------------



    // FUNCIONES RELACIONADAS CON GESTIONUSUARIOS
    // -----------------------------------------------------------------------

    // Funcion para registrar un usuario en la base
    public boolean registrar(String id, String clave, String nombre, String email){
        this.usuario  = gu.registrarUsuario(id, clave, nombre, email);
        return usuario!=null;
    }
    // Funcion para borrar un usuario
    public void borrarUsuario(Usuario u){
        gu.borrarUsuario(u);
    }
    public void modificarUsuario(String nombre, String clave, String email, Image imagen){
        Usuario u = gu.modificarUsuario(this.usuario.getId(), nombre, this.usuario.getContrasena(), clave, email, imagen);
        if (u != null) {
            this.usuario = u;
        }
    }
    // Funcion para buscar usuarios en la base
    public java.util.List<Usuario> consultarUsuarios(Integer id, String nombre){
        return gu.consultarUsuarios(id, nombre);
    }

    public java.util.List<Usuario> consultarUsuariosNoSeguidos(Usuario usuario, String busq){
        return gu.consultarUsuariosNoSeguidos(usuario, busq);
    }

    // Funcion para empezar a seguir a un usuario
    public void seguir(Usuario u1, Usuario u2){
        gu.seguir(u1, u2);
    }
    // Funcion para dejar de seguir a un usuario
    public void dejarSeguir(Usuario u1, Usuario u2){
        gu.dejarSeguir(u1, u2);
    }

    // Funcion para consultar a las personas que sigue un usuario
    public java.util.List<Usuario> consultarSeguidos(Usuario u1){
        return gu.consultarSeguidos(u1);
    }

    public Demo consultarDemo(int mes, int ano)
    {
        return gdem.consultarDemo(mes, ano);
    }

    // Funcion para mostrar los videojuegos de un usuario en el scroll de biblioteca.
    // Si pones un buscador se añade facil.
    // DEVUELVE LOS VIDEOJUEGOS DIERECTAMENTE
    public java.util.List<Videojuego> consultarJuegosUsuario(int id_usuario){
        return gu.consultarJuegosUsuario(id_usuario);
    }

    public java.util.List<String> consultarSeguidores(String idU2){
        return gu.consultarSeguidores(idU2);
    }

    // Funcion para crear una nueva comunidad
    public void insertarComunidad(Comunidad c){
        gc.insertarComunidad(c);
    }

    // Funcion para borrar una comunidad
    public void borrarComunidad(Comunidad c){
        gc.borrarComunidad(c);
    }

    // Funcion para mirar comunidades en el buscador, encontrar una comunidad especifica, a partir de algo o todas si la barra esta vacía
    public java.util.List<Comunidad> consultarComunidades(String nombre){
        return gc.consultarComunidades(nombre);
    }

    // Funcion para insertar a un usuario en una comunidad.
    // QUE SEA COMPETITIVO EL USUARIO NO ESTA IMPLEMENTADO A NIVEL BAJO (por lo menos por ahora) !!!!!!!!!!!!!!!!
    public void insertarJugadorEquipo(String id_usuario, Comunidad c){
        gc.insertarJugadorEquipo(id_usuario, c);
    }

    // Funcion para hacer salir de una comunidad a un usuario
    public void salirJugadorEquipo(String id_usuario){
        gc.salirJugadorEquipo(id_usuario);
    }

    // Funcion para consultar el equipo en el que esta un usuario
    public Comunidad consultarEquipoJugador(String id_usuario){
        return gc.consultarEquipoJugador(id_usuario);
    }

    // Funcion para consultar los jugadores que pertenecen a un equipo en concreto
    public java.util.List<Integer> consultarJugadoresEquipo(Comunidad c){
        return gc.consultarJugadoresEquipo(c);
    }

    // Funcion para contar los miembros de un equipo
    public Integer contarMiembrosEquipo(Comunidad c) {
        return gc.contarMiembrosEquipo(c);
    }

    // Funcion para contar la cantidad de juegos que un usario tiene en propiedad
    public Integer contarJuegosUsuario(String id_usuario){
        return gu.contarJuegosUsuario(id_usuario);
    }
    
    // Escribir una nueva reseña
    public void insertarReseña(Reseña r){
        gr.insertarReseña(r);
    }
    
    // Funcion para insertar la compra de un juego. Consultar tema de dinero. No veo necesario crear una clase compras. Pasar parametros con getters
    public void insertarCompra(int id_videojuego) {
        gcom.insertarCompra(id_videojuego, usuario.getId());
    }
    
    // Funciones relacionadas con la gestion de las categorias de los juegos
    public void insertarCategoria(Categoria c){
        gcat.insertarCategoria(c);
    }
    
    public void borrarCategoria(String nombre){
        gcat.borrarCategoria(nombre);
    }
    
    public java.util.List<Categoria> consultarCategorias(String nombre){
        return gcat.consultarCategorias(nombre);
    }
    
    // Funciones para crear y borrar DLC de videojuegos
    public void insertarDLC(DLC d){
        gd.insertarDLC(d);
    }
    
    public void borrarDLC(int d){
        gd.borrarDLC(d);
    }
    
    // Funciones relacionadas con la gestion de las plataformas. No veo necesario crear una clase
    public void insertarPlataforma(String nombre, String path){
        gpl.insertarPlataforma(nombre, path);
    }
    public void borrarPlataforma(String nombre){
        gpl.borrarPlataforma(nombre);
    }
    public java.util.List<String> consultarPlataformas(String nombre){
        return gpl.consultarPlataformas(nombre);
    }
    
    // Funciones para gestionar las plataformas de un videojuego, se podrian mostrar por pantalla
    public void insertarPlataformaVideojuego(String nombre, int id_videojuego){
        gpl.insertarPlataformaVideojuego(nombre, id_videojuego);
    }
    public void borrarPlataformaVideojuego(String nombre, int videojuego){
        gpl.borrarPlataformaVideojuego(nombre, videojuego);
    }
    
    public void bloquearSeguidor(String idU1, String idU2){
        gu.bloquearSeguidor(idU1, idU2);
    }
    //Lo hice para q si null->false, si true te pasa el usuario, asi puedes ir a tu perfil y eso
    public boolean checkCredentials(String username, String password)
    {
        this.usuario = gu.comprobarAutentificacion(username, password);
        return usuario != null;
    }

    public java.util.List<DLC> consultarDLCsVideojuego(Videojuego v){
        return gv.consultarDLCsVideojuego(v);
    }

//METHODS

    // TORNEOS
    public void insertarTorneo(Torneo t) {
        gt.insertarTorneo(t);
    }
    public int torneosGanados(Usuario u){
        return gt.torneosGanados(u);
    }
    public List<Torneo> consultarTorneos(String nombre)
    {
        return gt.consultarTorneos(nombre);
    }

    public void participarTorneo(Usuario u, Torneo t) {
        gt.participarTorneo(u, t);
    }

    public List<Usuario> consultarParticipantes(Torneo t) {
        return gt.consultarParticipantes(t);
    }

    public boolean isParticipante(Usuario u, Torneo t) {
        return gt.isParticipante(u, t);
    }

    public boolean puedeRetirarse(Torneo t) {
        return gt.puedeRetirarse(t);
    }

    public void retirarseTorneo(Usuario u, Torneo t) {
        gt.retirarseTorneo(u, t);
    }




    public java.util.List<Videojuego> consultarVideojuegosUsuario(String id){
        return gu.consultarVideojuegosUsuario(id);
    }
    public List<DLC> consultarDLCsVideojuegoUsuario(Videojuego v, Usuario u){
        return gd.consultarDLCsVideojuegoUsuario(v, u);
    }
    public boolean tieneDLC(Usuario usuario, DLC dlc){
        return gd.tieneDLC(usuario, dlc);
    }

    public boolean tieneVideojeugo(Usuario usuario, Videojuego videojuego){
        return gv.tieneVideojuego(usuario, videojuego);
    }

    public void comprarDLC(DLC d, Usuario u){
        gd.comprarDLC(d, u);
    }

    public void devolverDLC(DLC d, Usuario u){
        gd.devolverDLC(d, u);
    }

    public boolean tieneComunidad(Usuario u){
        return gc.tieneComunidad(u);
    }
}
