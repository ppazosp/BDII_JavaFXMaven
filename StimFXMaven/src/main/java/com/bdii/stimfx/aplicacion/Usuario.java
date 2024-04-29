/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;

import javafx.scene.image.Image;

import java.util.Objects;

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
    private Image fotoPerfil;
    private boolean isAdmin;
    private boolean isEditor;
    private boolean isCompetitivePlayer;
    double dinero;

    public Usuario(String id, String nombre, String contrasena, String email) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.fotoPerfil=null;
        this.dinero = 0;
    }

    public Usuario(String id, String nombre, String contrasena, String email, Image fotoPerfil) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.fotoPerfil=fotoPerfil;
        this.dinero = 0;
    }

    public Usuario(String id, String nombre, String contrasena, String TipoUsuario, String email) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.TipoUsuario = TipoUsuario;
        this.email = email;
        this.dinero = 0;
        this.fotoPerfil=null;
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

    public Image getFotoPerfil() {
        return fotoPerfil;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isEditor() {
        return isEditor;
    }

    public void setEditor(boolean editor) {
        isEditor = editor;
    }

    public boolean isCompetitivePlayer() {
        return isCompetitivePlayer;
    }

    public void setCompetitivePlayer(boolean competitivePlayer) {
        isCompetitivePlayer = competitivePlayer;
    }

    public boolean tieneDineroSuficiente(double dinero){
        return this.dinero> dinero;
    }
    public void setDinero(float dinero) {this.dinero = dinero;}

    public double getDinero(){
        return this.dinero;
    }

    public void modificarDinero(double dif){
        this.dinero+=dif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
