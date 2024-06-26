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

    public boolean comprarDLC(DLC d, Usuario u){
        if (u.tieneDineroSuficiente(d.getPrecio())) {
            fbd.comprarDLC(d, u.getId(), Date.valueOf(LocalDate.now()));
            u.modificarDinero(-d.getPrecio());
            return true;
        }
        else{
            return false;
        }
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

    public void updateDLC(DLC d)
    {
        fbd.updateDLC(d);
    }

    public void borrarDLC(int d){
        fbd.borrarDLC(d);
    }

    public java.util.List<DLC> consultarDLCsVideojuego(Videojuego v){
        return fbd.consultarDLCsVideojuego(v);
    }

    public void publicarDLC(DLC d) {
        if (existsDLC(d)) updateDLC(d);
        else insertarDLC(d);
    }

    public boolean existsDLC(DLC d) {
        return fbd.consultarDLC(d.getIdVideojuego(), d.getIdDLC()) != null;
    }
}
