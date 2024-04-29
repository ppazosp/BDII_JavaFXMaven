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

    public boolean compraVideojuego(Videojuego v, Usuario u){
        if (u.tieneDineroSuficiente(v.getPrecio())){
            insertarCompra(v, u.getId());
            u.modificarDinero(-v.getPrecio());
            return true;
        }
        return false;
    }
    public void insertarCompra(Videojuego v, String id_usr) {
        fbd.insertarCompra(v.getId(), v.getPrecio(), id_usr);
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
