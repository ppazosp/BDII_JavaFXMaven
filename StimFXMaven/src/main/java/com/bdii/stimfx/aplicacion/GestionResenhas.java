package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

public class GestionResenhas {
    FachadaGUI fgui;
    FachadaBaseDatos fbd;

    public GestionResenhas(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void insertarResenha(Resenha r){
        fbd.insertarResenha(r);
    }

    public void updateResenha(Resenha r)
    {
        fbd.updateResenha(r);
    }

    public void publicarResenha(Resenha r) {
        if (existsResenha(r)) updateResenha(r);
        else insertarResenha(r);
    }

    public boolean existsResenha(Resenha r) {
        return fbd.consultarResenha(r.getId_videojuego(), r.getId_usuario()) != null;
    }

    public Resenha consultarResenha(int id_v, String id_usr)
    {
        return fbd.consultarResenha(id_v, id_usr);
    }

    public void consultarResenhas(Videojuego v) {
        fbd.consultarResenhas(v);
    }

    public float consultarMediaResenhas(Videojuego v)
    {
        return fbd.consultarMediaResenhas(v);
    }

}
