package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class GestionDLC {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionDLC(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public List<DLC> consultarDLCsVideojuegoUsuario(Videojuego v, Usuario u){
        return fbd.consultarDLCsVideojuegoUsuario(v.getId(), u.getId());
    }

    public void comprarDLC(DLC d, Usuario u){
        fbd.comprarDLC(d, u.getId(), Date.valueOf(LocalDate.now()));
    }

    public void devolverDLC(DLC d, Usuario u){
        fbd.devolverDLC(d, u.getId());
    }

    public boolean tieneDLC(Usuario usuario, DLC dlc){
        List<DLC> dlcs = fbd.consultarDLCsVideojuegoUsuario(dlc.getIdVideojuego(), usuario.getId());
        return  dlcs.contains(dlc);
    }
    public void insertarDLC(DLC d){
        fbd.insertarDLC(d);
    }

    public void borrarDLC(int d){
        fbd.borrarDLC(d);
    }
}
