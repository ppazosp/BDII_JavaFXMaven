/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;
import com.bdii.stimfx.gui.FachadaGUI;
import com.bdii.stimfx.baseDatos.FachadaBaseDatos;

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
    
    public void consultarDLCsVideojuego(Videojuego v){
        fbd.consultarDLCsVideojuego(v);
    }
    
    
}
