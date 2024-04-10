/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class FachadaAplicacion {
    com.bdii.stimfx.gui.FachadaGUI fgui;
    com.bdii.stimfx.baseDatos.FachadaBaseDatos fbd;
    GestionVideojuegos gv;
    GestionUsuarios gu;
    
    
 public FachadaAplicacion(){
   //fgui=new gui.FachadaGUI(this);
   fbd= new com.bdii.stimfx.baseDatos.FachadaBaseDatos(this);
   gv= new GestionVideojuegos(fgui, fbd);
   //gu= new GestionUsuarios(fgui, fbd);
 }

 public static void main(String args[]) {
     FachadaAplicacion fa;
     
     fa= new FachadaAplicacion();
     //fa.iniciaInterfazUsuario();
 }
 
  public void muestraExcepcion(String e){
     //fgui.muestraExcepcion(e);
 }
}
