package com.bdii.stimfx.aplicacion;

import com.bdii.stimfx.baseDatos.FachadaBaseDatos;
import com.bdii.stimfx.gui.FachadaGUI;
import javafx.application.Application;
import javafx.scene.Scene;

public class FachadaAplicacion {

    private FachadaGUI fg;
    private FachadaBaseDatos fbd;

    private static Scene scene;

    public FachadaAplicacion() {
        //fbd = new FachadaBaseDatos(this);
    }

    public void setFachadaGUI(FachadaGUI fg) {
        this.fg = fg;
    }

    public static void main(String[] args) {
        FachadaGUI fg = new FachadaGUI();
        Application.launch(FachadaGUI.class, args);
    }

    public static void muestraExcepcion(String e) {
        // fg.muestraExcepcion(e);
    }
    
    // Funcion para consultar videojuegos a partir de un nombre. Utilizar en scroll del main
    public java.util.List<Videojuego> consultarVideojuegos(String n){ 
        return fbd.consultaVideojuegos(n);
    }

    // Funcion para insertar un nuevo torneo. Por ahora, las fechas se calculan solas (se puede mirar para cambiarlo). El ganador se insertara mas tarde.
    public void insertarTorneo(Torneo t){
        fbd.insertarTorneo(t);
    }

    //METHODS
    public boolean checkCredentials(String username, String password)
    {
        return true;
    }
}
