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
        this.primaryStage.setMinWidth(1000);
        this.primaryStage.setMaxWidth(1000);
        this.primaryStage.setMinHeight(630);
        this.primaryStage.setMaxHeight(630);
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
            if (windowTitle == null) windowTitle = "Stim";
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

    public void showSigninScene() {
        SignInWController signInWController = loadFXML("/com/bdii/stimfx/gui/signinW.fxml", "Registrarse", SignInWController.class);
        assert signInWController != null;
        signInWController.setMainApp(this);
    }

    public void showMainWindow(boolean createWindow) {
        Stage old = primaryStage;

        if(createWindow)
        {
            primaryStage = new Stage();
            this.primaryStage.setMinWidth(1250);
            this.primaryStage.setMaxWidth(1250);
            this.primaryStage.setMinHeight(785);
            this.primaryStage.setMaxHeight(785);
        }
        MainWController mainWController = loadFXML("/com/bdii/stimfx/gui/mainW.fxml", null, MainWController.class);
        assert mainWController != null;
        mainWController.setMainApp(this);

        if(createWindow) old.close();
    }

    public void showProfileScene() {
        ProfileWController profileWController = loadFXML("/com/bdii/stimfx/gui/profileW.fxml", null, ProfileWController.class);
        assert profileWController != null;
        profileWController.setMainApp(this);
    }

    public void showLibraryScene() {
        LibraryWController libraryWController = loadFXML("/com/bdii/stimfx/gui/libraryW.fxml", null, LibraryWController.class);
        assert libraryWController != null;
        libraryWController.setMainApp(this);
    }

    public void showCommunityScene() {
        CommunityWController communityWController = loadFXML("/com/bdii/stimfx/gui/communityW.fxml", null, CommunityWController.class);
        assert communityWController != null;
        communityWController.setMainApp(this);
    }

    public void showSettingsScene() {
        SettingsWController settingsWController = loadFXML("/com/bdii/stimfx/gui/settingsW.fxml", null, SettingsWController.class);
        assert settingsWController != null;
        settingsWController.setMainApp(this);
    }


    public void loadGameWindow()
    {
        Stage old = primaryStage;
        primaryStage = new Stage();
        primaryStage.setResizable(true);
        this.primaryStage.setWidth(1920);
        this.primaryStage.setHeight(1105);

        LoadGameWController loadGameWController = loadFXML("/com/bdii/stimfx/gui/loadGameW.fxml", null, LoadGameWController.class);
        assert loadGameWController != null;
        loadGameWController.setMainApp(this);

        primaryStage = old;
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
