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

    public void insertarReseña(String texto, int valoracion, String id_usr,int id_juego){
        Resenha r = new Resenha(texto, valoracion, id_usr, id_juego);
        fbd.insertarReseña(r);
    }

    public void consultarResenhas(Videojuego v) {
        fbd.consultarReseña(v);
    }


}
