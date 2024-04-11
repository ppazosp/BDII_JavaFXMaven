package com.bdii.stimfx.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FachadaAplicacion extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/aplicacion/primary.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Mi Aplicación JavaFX");
            scene = new Scene(root, 600, 250);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            muestraExcepcion("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setRoot(String ventanaNombre) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(FachadaAplicacion.class.getResource("/com/bdii/stimfx/aplicacion/" + ventanaNombre + ".fxml"));
            Parent root = loader.load();
            scene.setRoot(root);
        } catch (IOException e) {
            muestraExcepcion("Error al cargar la ventana " + ventanaNombre + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void muestraExcepcion(String e){
        //fgui.muestraExcepcion(e);
    }
}
