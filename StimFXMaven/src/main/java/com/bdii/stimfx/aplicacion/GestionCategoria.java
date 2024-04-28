package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

public class GestionCategoria {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionCategoria(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void insertarCategoria(Categoria c){
        fbd.insertarCategoria(c);
    }

    public void borrarCategoria(String nombre){
        fbd.borrarCategoria(nombre);
    }

    public java.util.List<Categoria> consultarCategorias(String nombre){
        return fbd.consultarCategorias(nombre);
    }

}
