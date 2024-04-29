/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.bdii.stimfx.gui.FachadaGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//PONER A TODOS LOS USUARIOS RESPONABILIDADES DE PASAR DE IMAGE A BYTES
/**
 *
 * @author alumnogreibd
 */
public class GestionUsuarios {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;
    
        public GestionUsuarios(FachadaGUI fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    /*
    public java.util.List<Usuario> obtenerUsuarios(String id, String nombre){
        return fbd.consultarUsuarios(id, nombre);
    }
       */

        /*
    public void crearCambiarUsuario(String id, String nombre, String clave, String tipo, String email, String direccion, String claveAntigua){
        try{
        String clave_hash;
        if (claveAntigua == null || !claveAntigua.equals(clave)) {
            clave_hash = hashPassword(clave);
        } else {
            clave_hash = clave;
        }
        Usuario usuario= new Usuario(id, clave_hash, nombre, direccion,  email, tipoU);
        if (fbd.comprobarExisteUsuario(id)){
            fbd.modificarUsuario(usuario);
        }
        else{
            fbd.crearUsuario(usuario);
        }
        }
        
        catch (IllegalArgumentException i){
            fgui.getFa().muestraExcepcion("Tipo usuario puede ser o \"Administrador\" o \"Normal\"");
           
        }
        
    }

*/
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            md.update(password.getBytes());
            
            // bytes hasheados
            byte[] hashedBytes = md.digest();
            
            // Converison a base 64
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);
            //le quitamos el '=' que esta al final del base64 ya que no aporta nada
            String finalPassword = hashedPassword.substring(0, hashedPassword.length() - 1);
            return finalPassword;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    //Si no encuentra al usuario con esa contraseña, devuelve un null
    public Usuario comprobarAutentificacion(String idUsuario, String clave){
        Usuario u;
        //hashear en el futuro
        String hashedPassword= hashPassword(clave);
        u=fbd.validarUsuario(idUsuario, hashedPassword);
        return u;
    }

    public Usuario registrarUsuario(String id, String clave, String nombre, String email){
        Usuario usuario;
        if (fbd.existeUsuario(id)){
            System.out.println("El usuario ya existe");
            return null;
        }
        else{
            String hashedPassword= hashPassword(clave);
            usuario = new Usuario(id, nombre, hashedPassword, email);
            fbd.insertarUsuario(usuario);
        }
        return usuario;
    }

    public void hacerAdmin(Usuario u)
    {
        fbd.hacerAdmin(u.getId());
    }

    public void quitarAdmin(Usuario u)
    {
        fbd.quitarAdmin(u.getId());
    }

    public void hacerJugadorCompetitivo(Usuario u)
    {
        fbd.hacerJugadorCompetitivo(u.getId());
    }

    public void hacerEditor(Usuario u)
    {
        fbd.hacerEditor(u.getId());
    }

    // Funcion para borrar un usuario a partir de un id.
    public void borrarUsuario(Usuario u){
        fbd.borrarUsuario(u.getId());
    }

    //pasar id desde FA
    public Usuario modificarUsuario(String id, String nombre, String claveAntigua, String clave, String email, Image imagen){
        Usuario usuario;
        if (claveAntigua.equals(hashPassword(clave))){
            usuario = new Usuario(id, nombre, clave, email, imagen);}
        //Si cambio contraseña rehshear
        else{
            usuario = new Usuario(id, nombre, hashPassword(clave), email, imagen);
        }
        return fbd.modificarUsuario(usuario);
    }

    public java.util.List<Videojuego> consultarJuegosUsuario(int id_usuario){
        java.util.List<Videojuego> resultado = new ArrayList<Videojuego>();
        Videojuego videojuegoActual;
        java.util.List<Integer> id_juegos = fbd.consultarJuegosUsuario(id_usuario);
        for (Integer i : id_juegos){
            videojuegoActual = fbd.consultarVideojuego(i);
            resultado.add(videojuegoActual);
        }
        return resultado;
    }
    public java.util.List<String> consultarSeguidores(String idU2){
        return fbd.consultarSeguidores(idU2);
    }

    // Funcion para buscar usuarios en la base
    public java.util.List<Usuario> consultarUsuarios(Integer id, String nombre){
        return fbd.consultarUsuarios(id, nombre);
    }

    public java.util.List<Usuario> consultarUsuarios(){
        return fbd.consultarUsuarios();
    }

    public java.util.List<Videojuego> consultarVideojuegosUsuario(String id){
        return fbd.consultarVideojuegosUsuario(id);
    }
    public void bloquearSeguidor(String idU1, String idU2){
        fbd.bloquearSeguidor(idU1, idU2);
    }

    public java.util.List<Usuario> consultarUsuariosNoSeguidos(Usuario usuario, String busq){
        return fbd.consultarUsuariosNoSeguidos(usuario.getId(), busq);
    }

    // Funciones para empezar a seguir a un usuario
    public void seguir(Usuario u1, Usuario u2){
        fbd.seguir(u1.getId(), u2.getId());
    }

    // Funcion para dejar de seguir a un usuario
    public void dejarSeguir(Usuario u1, Usuario u2){
        fbd.dejarSeguir(u1.getId(), u2.getId());
    }

    // Funcion para consultar a las personas que sigue un usuario // CAMBIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARRRRRR
    public java.util.List<Usuario> consultarSeguidos(Usuario u1){
        return fbd.consultarSeguidos(u1.getId());
    }
}
