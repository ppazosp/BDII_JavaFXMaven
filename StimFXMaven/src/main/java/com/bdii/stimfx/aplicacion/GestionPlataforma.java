package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

public class GestionPlataforma {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionPlataforma(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void insertarPlataforma(String nombre, String path){
        fbd.insertarPlataforma(nombre, path);
    }
    public void borrarPlataforma(String nombre){
        fbd.borrarPlataforma(nombre);
    }
    public java.util.List<String> consultarPlataformas(String nombre){
        return fbd.consultarPlataformas(nombre);
    }

    // Funciones para gestionar las plataformas de un videojuego, se podrian mostrar por pantalla
    public void insertarPlataformaVideojuego(String nombre, int id_videojuego){
        fbd.insertarPlataformaVideojuego(nombre, id_videojuego);
    }
    public void borrarPlataformaVideojuego(String nombre, int videojuego){
        fbd.borrarPlataformaVideojuego(nombre, videojuego);
    }
}
