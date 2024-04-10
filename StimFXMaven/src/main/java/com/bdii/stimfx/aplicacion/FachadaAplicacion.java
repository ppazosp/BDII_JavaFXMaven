package com.bdii.stimfx.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;

public class FachadaAplicacion extends Application{
    com.bdii.stimfx.gui.FachadaGUI fgui;
    com.bdii.stimfx.baseDatos.FachadaBaseDatos fbd;
    GestionVideojuegos gv;
    GestionUsuarios gu;
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaAplicacion.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        FachadaAplicacion fa;

        fa= new FachadaAplicacion();
        launch();
    }
    
    public FachadaAplicacion(){
      //fgui=new gui.FachadaGUI(this);
      fbd= new com.bdii.stimfx.baseDatos.FachadaBaseDatos(this);
      gv= new GestionVideojuegos(fgui, fbd);
      //gu= new GestionUsuarios(fgui, fbd);
    }
 
    public void muestraExcepcion(String e){
       //fgui.muestraExcepcion(e);
   }
}