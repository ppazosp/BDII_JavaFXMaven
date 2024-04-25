/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import com.bdii.stimfx.aplicacion.Plataforma;
import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FachadaGUI extends Application {

    private static Scene scene;
    FachadaAplicacion fa;

    public FachadaGUI() {
        // Constructor sin argumentos necesario para JavaFX
    }

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        fa = new FachadaAplicacion(this);

        this.primaryStage = primaryStage;
        this.primaryStage.setMinWidth(1000);
        this.primaryStage.setMaxWidth(1000);
        this.primaryStage.setMinHeight(630);
        this.primaryStage.setMaxHeight(630);
        showLoginWindow();
    }

    private <T extends Controller> T loadFXML(String fxmlFile, String windowTitle, Class<T> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            T controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
            controllerInstance.setMainApp(this);
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

    public void showPlatforms(Videojuego v, HBox hbox)
    {
        List<Plataforma> vp = fa.consultarPlataformasVideojuego(v);
        for(Plataforma p : vp)
        {
            ImageView iV = new ImageView(p.getIcono());
            hbox.getChildren().add(iV);
            iV.setFitWidth(24);
            iV.setFitHeight(24);
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
        libraryWController.initializeWindow();
    }

    public void showSocialScene() {
        SocialWController socialWController = loadFXML("/com/bdii/stimfx/gui/socialW.fxml", null, SocialWController.class);
        assert socialWController != null;
        socialWController.setMainApp(this);
        socialWController.initializeWindow();
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

    public void showSearchScene(String search)
    {
        MainSearchWController mainSearchWController = loadFXML("/com/bdii/stimfx/gui/mainSearchW.fxml", null, MainSearchWController.class);
        assert mainSearchWController != null;
        mainSearchWController.setMainApp(this);
        mainSearchWController.setSearchBar(search);
        mainSearchWController.showSearchResults();
    }

    public void showGameScene(Videojuego v)
    {
        GameWController gameWController = loadFXML("/com/bdii/stimfx/gui/gameW.fxml", null, GameWController.class);
        assert gameWController != null;
        gameWController.setMainApp(this);
        gameWController.setVideojuego(v);
        gameWController.setApp(this);
        gameWController.initializeWindow();
    }

    public void showGameLibraryScene(Videojuego v)
    {
        LibraryGameWController libraryGameWController = loadFXML("/com/bdii/stimfx/gui/libraryGameW.fxml", null, LibraryGameWController.class);
        assert libraryGameWController != null;
        libraryGameWController.setMainApp(this);
        libraryGameWController.initializeWindow(v);
    }

    public void showCommunityScene()
    {
        CommunityWController communityWController = loadFXML("/com/bdii/stimfx/gui/communityW.fxml", null, CommunityWController.class);
        assert communityWController != null;
        communityWController.setMainApp(this);
        communityWController.initializeWindow();
    }



    public static void main(String[] args) {
        launch(args);
    }


    // METHODS

    public boolean checkCredentials(String username, String password)
    {
        return this.fa.checkCredentials(username, password);
    }

    public boolean register(String id, String clave, String nombre, String email){
        return this.fa.registrar(id, clave, nombre, email);
    }
}
