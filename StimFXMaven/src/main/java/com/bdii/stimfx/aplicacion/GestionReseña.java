package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

public class GestionReseña {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionReseña(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void insertarReseña(Reseña r){
        fbd.insertarReseña(r);
    }


}
