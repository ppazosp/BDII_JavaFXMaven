package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;

import java.util.List;

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

    public Integer contarJuegosUsuario(String id_usuario){
        return fbd.contarJuegosUsuario(id_usuario);
    }

    public void consultarNumeroDescargas(Videojuego v){
        fbd.consultarNumeroDescargas(v);
    }

    public boolean tieneVideojuego(Usuario usuario, Videojuego videojuego){
        List<Videojuego> videojuegoUsuario = fbd.consultarVideojuegosUsuario(usuario.getId());
        return  videojuegoUsuario.contains(videojuego);
    }


}
