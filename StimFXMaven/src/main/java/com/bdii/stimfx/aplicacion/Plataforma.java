package com.bdii.stimfx.aplicacion;

import javafx.scene.image.Image;

public class Plataforma {
    private String nombre;
    private Image icono;
    public Plataforma(String nombre, Image icono) {
        this.nombre = nombre;
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }
    public Image getIcono() {
        return icono;
    }
}
