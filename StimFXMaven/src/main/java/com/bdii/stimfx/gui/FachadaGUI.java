/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class FachadaGUI extends Application {

    private static Scene scene;
    FachadaAplicacion fa;

    public FachadaGUI() {
        // Constructor sin argumentos necesario para JavaFX
    }

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        fa = new FachadaAplicacion();
        fa.setFachadaGUI(this);

        this.primaryStage = primaryStage;
        showLoginWindow();
    }

    private <T> T loadFXML(String fxmlFile, String windowTitle, Class<T> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            T controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
            loader.setController(controllerInstance);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(windowTitle);
            primaryStage.show();

            return controllerInstance;
        } catch (IOException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void showLoginWindow() {
        LogInWController logInWController = loadFXML("/com/bdii/stimfx/gui/loginW.fxml", "Iniciar sesión", LogInWController.class);
        assert logInWController != null;
        logInWController.setMainApp(this);
    }

    public void showSigninWindow() {
        SignInWController signInWController = loadFXML("/com/bdii/stimfx/gui/signinW.fxml", "Registrarse", SignInWController.class);
        assert signInWController != null;
        signInWController.setMainApp(this);
    }

    public void showMainWindow() {
        Stage old = primaryStage;
        primaryStage = new Stage();
        MainWController mainWController = loadFXML("/com/bdii/stimfx/gui/mainW.fxml", "Stim", MainWController.class);
        assert mainWController != null;
        mainWController.setMainApp(this);

        old.close();
    }



    public static void main(String[] args) {
        launch(args);
    }


    // METHODS

    public boolean checkCredentials(String username, String password)
    {
        return this.fa.checkCredentials(username, password);
    }
}
