/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class UsuarioAdministrador extends Usuario{

    public UsuarioAdministrador(String id, String nombre, String contrasena, String TipoUsuario, String email, String telefono) {
        super(id, nombre, contrasena, TipoUsuario, email, telefono);
    }

    public UsuarioAdministrador(String id, String nombre, String contrasena, String TipoUsuario, String email) {
        super(id, nombre, contrasena, TipoUsuario, email);
    }
    
}
