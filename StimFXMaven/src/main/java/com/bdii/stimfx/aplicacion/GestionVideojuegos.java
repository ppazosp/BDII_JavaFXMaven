/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;
import com.bdii.stimfx.gui.FachadaGUI;
import com.bdii.stimfx.baseDatos.FachadaBaseDatos;

import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class GestionVideojuegos {
    
    FachadaGUI fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionVideojuegos(FachadaGUI fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }
    
    public void insertarVideojuego(Videojuego v){
        fbd.insertarVideojuego(v);
    }

    public java.util.List<Videojuego> consultarVideojuegos(String n){
        return fbd.consultaVideojuegos(n);
    }

    public Videojuego consultarVideojuego(String n){
        return fbd.consultarVideojuego(n);
    }

    public void consultarNumeroDescargas(Videojuego v){
        fbd.consultarNumeroDescargas(v);
    }

    public List<Videojuego> consultaVideoJuegosInicio(){
        return fbd.consultaVideoJuegosInicio();
    }

    public Videojuego proximoVideojuego(){
        return fbd.proximoVideojuego();
    }

    public List<Plataforma> consultarPlataformasVideoJuego(Videojuego v){
        return fbd.consultarPlataformasVideoJuego(v.getId());
    }

    public java.util.List<DLC> consultarDLCsVideojuego(Videojuego v){
        return fbd.consultarDLCsVideojuego(v);
    }

    public java.util.List<String> consultarCategoriasVideojuego(Videojuego v){
        return fbd.consultarCategoriasVideojuego(v.getId());
    }

    public void borrarVideojuego(Videojuego v) {
        fbd.borrarVideojuego(v.getId());
    }
    
    /*
    public java.util.List<String> consultarPlataformasVideojuego(Videojuego v){
        return fbd.consultarPlataformasVideojuego(v.getId());
    }
*/


}
