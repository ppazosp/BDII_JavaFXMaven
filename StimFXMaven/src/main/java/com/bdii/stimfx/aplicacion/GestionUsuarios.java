/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import com.bdii.stimfx.gui.FachadaGUI;

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
}
