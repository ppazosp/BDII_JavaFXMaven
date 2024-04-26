package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

import java.util.List;

public class GestionTorneo {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionTorneo(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void insertarTorneo(Torneo t){
        fbd.insertarTorneo(t);
    }
    public int torneosGanados(Usuario u){
        return fbd.torneosGanados(u.getId());
    }

    public List<Torneo> consultarTorneos(String nombre)
    {
        return fbd.consultarTorneos(nombre);
    }
}
