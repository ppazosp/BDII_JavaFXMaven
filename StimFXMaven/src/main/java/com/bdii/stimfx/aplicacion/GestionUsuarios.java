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
        //String hashedPassword= hashPassword(clave);
        u=fbd.validarUsuario(idUsuario, clave);
        return u;
    }

    public Usuario registrarUsuario(String id, String clave, String nombre, String email){
        Usuario usuario;
        if (fbd.existeUsuario(id)){
            System.out.println("El usuario ya existe");
            return null;
        }
        else{
            usuario = new Usuario(id, clave, nombre, email);
            fbd.insertarUsuario(usuario);
        }
        return usuario;
    }

    // Funcion para borrar un usuario a partir de un id.
    public void borrarUsuario(Usuario u){
        fbd.borrarUsuario(u.getId());
    }

    //pasar id desde FA
    public Usuario modificarUsuario(String id, String nombre, String clave, String email, Image imagen){
        Usuario usuario = new Usuario(id, nombre, clave, email, imagen);
        return fbd.modificarUsuario(usuario);
    }


    // Funcion para buscar usuarios en la base
    public java.util.List<Usuario> consultarUsuarios(Integer id, String nombre){
        return fbd.consultarUsuarios(id, nombre);
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
        java.util.List<Usuario> resultado = new ArrayList<Usuario>();
        Usuario usuarioActual;

        List<Integer> idUsuarios = fbd.consultarSeguidos(u1.getId());
        for (Integer i : idUsuarios) {
            //usuarioActual = fbd.consultarUsuarios(i, null);
        }
        return resultado;
    }
}
