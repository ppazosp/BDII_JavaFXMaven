package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

import java.sql.Date;

public class GestionReseña {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionReseña(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void insertarReseña(String texto, float valoracion, String id_usr,int id_juego){
        Reseña r = new Reseña(texto, valoracion, id_usr, id_juego);
        fbd.insertarReseña(r);
    }


}
