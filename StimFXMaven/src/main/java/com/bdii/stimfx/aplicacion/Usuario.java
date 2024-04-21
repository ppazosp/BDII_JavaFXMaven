/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Usuario {
    private String id;
    private String nombre;
    private String contrasena;
    private String TipoUsuario;
    private String email;

    public Usuario(String id, String nombre, String contrasena, String email) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
    }

    public Usuario(String id, String nombre, String contrasena, String TipoUsuario, String email) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.TipoUsuario = TipoUsuario;
        this.email = email;
    }

    public Usuario(String idUsr) {
        this.id = idUsr;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    
    
    
    
}
