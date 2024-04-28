package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

public class GestionCompra {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionCompra(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void insertarCompra(int id_videojuego, String id_usr) {
        fbd.insertarCompra(id_videojuego, id_usr);
    }
}
