/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FachadaGUI extends Application {

    FachadaAplicacion fa;

    public FachadaGUI() {
        // Constructor sin argumentos necesario para JavaFX
    }

    private Stage primaryStage;
    private Scene primaryScene;
    private Scene loadingScene;

    @Override
    public void start(Stage primaryStage) {
        fa = new FachadaAplicacion(this);

        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        //this.primaryStage.setMinWidth(1000);
        //this.primaryStage.setMaxWidth(1000);
        //this.primaryStage.setMinHeight(630);
        //this.primaryStage.setMaxHeight(630);

        showLoginWindow();
    }

    private <T extends Controller> T loadFXML(String fxmlFile, String windowTitle, Stage stage, Class<T> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            T controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
            controllerInstance.setMainApp(this);
            loader.setController(controllerInstance);
            Parent root = loader.load();

            primaryScene = new Scene(root);
            if (stage == null) stage = primaryStage;
            stage.setScene(primaryScene);
            if (windowTitle == null) windowTitle = "Stim";
            stage.setTitle(windowTitle);
            stage.show();

            return controllerInstance;
        } catch (IOException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createLoadingScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/loadingW.fxml"));
            LoadingWController loadingWController = new LoadingWController();
            loader.setController(loadingWController);
            Parent root = loader.load();
            loadingScene = new Scene(root);
            loadingWController.setMainApp(this);
            loadingWController.initializeWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loading() {
        primaryStage.setScene(loadingScene);
    }

    public void loaded() {
        primaryStage.setScene(primaryScene);
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
        LogInWController logInWController = loadFXML("/com/bdii/stimfx/gui/loginW.fxml", "Iniciar sesión", null, LogInWController.class);
        assert logInWController != null;
        logInWController.setMainApp(this);
    }

    public void showSigninScene() {
        SignInWController signInWController = loadFXML("/com/bdii/stimfx/gui/signinW.fxml", "Registrarse", null, SignInWController.class);
        assert signInWController != null;
        signInWController.setMainApp(this);
    }

    public void showMainWindow(boolean createWindow) {
        createLoadingScene();

        Stage old = primaryStage;

        if(createWindow)
        {
            primaryStage = new Stage();
            this.primaryStage.setResizable(false);
            //this.primaryStage.setMinWidth(1250);
            //this.primaryStage.setMaxWidth(1250);
            //this.primaryStage.setMinHeight(785);
            //this.primaryStage.setMaxHeight(785);
        }
        MainWController mainWController = loadFXML("/com/bdii/stimfx/gui/mainW.fxml", null, null, MainWController.class);
        assert mainWController != null;
        mainWController.setMainApp(this);
        mainWController.initializeWindow();

        if(createWindow) old.close();
    }

    public void showProfileScene() {
        ProfileWController profileWController = loadFXML("/com/bdii/stimfx/gui/profileW.fxml", null, null, ProfileWController.class);
        assert profileWController != null;
        profileWController.setMainApp(this);
        profileWController.initializeWindow();
    }

    public void showLibraryScene() {
        LibraryWController libraryWController = loadFXML("/com/bdii/stimfx/gui/libraryW.fxml", null, null, LibraryWController.class);
        assert libraryWController != null;
        libraryWController.setMainApp(this);
        libraryWController.initializeWindow();
    }

    public void showSocialScene() {
        SocialWController socialWController = loadFXML("/com/bdii/stimfx/gui/socialW.fxml", null, null, SocialWController.class);
        assert socialWController != null;
        socialWController.setMainApp(this);
        socialWController.load();
    }

    public void showSettingsScene() {
        SettingsWController settingsWController = loadFXML("/com/bdii/stimfx/gui/settingsW.fxml", null, null, SettingsWController.class);
        assert settingsWController != null;
        settingsWController.setMainApp(this);
        settingsWController.initializeWindow();
    }

    public void loadGameWindow(String url)
    {
        Stage old = primaryStage;
        primaryStage = new Stage();
        primaryStage.setResizable(true);
        this.primaryStage.setWidth(1920);
        this.primaryStage.setHeight(1080);

        LoadGameWController loadGameWController = loadFXML("/com/bdii/stimfx/gui/loadGameW.fxml", null, null, LoadGameWController.class);
        assert loadGameWController != null;
        loadGameWController.setMainApp(this);
        loadGameWController.initializeWindow(url);

        primaryStage = old;
    }

    public void showSearchScene(String search)
    {
        MainSearchWController mainSearchWController = loadFXML("/com/bdii/stimfx/gui/mainSearchW.fxml", null, null, MainSearchWController.class);
        assert mainSearchWController != null;
        mainSearchWController.setMainApp(this);
        mainSearchWController.setSearchBar(search);
        mainSearchWController.showSearchResults();
    }

    public void showGameScene(Videojuego v, String search, int from)
    {
        GameWController gameWController = loadFXML("/com/bdii/stimfx/gui/gameW.fxml", null, null, GameWController.class);
        assert gameWController != null;
        gameWController.setMainApp(this);
        gameWController.setApp(this);
        gameWController.initializeWindow(v, search, from);
    }

    public void showGameLibraryScene(Videojuego v)
    {
        LibraryGameWController libraryGameWController = loadFXML("/com/bdii/stimfx/gui/libraryGameW.fxml", null, null, LibraryGameWController.class);
        assert libraryGameWController != null;
        libraryGameWController.setMainApp(this);
        libraryGameWController.initializeWindow(v);
    }

    public void showCommunityScene()
    {
        CommunityWController communityWController = loadFXML("/com/bdii/stimfx/gui/communityW.fxml", null, null, CommunityWController.class);
        assert communityWController != null;
        communityWController.setMainApp(this);
        communityWController.load();
    }

    public void showEditScene() {
        EditWController editWController = loadFXML("/com/bdii/stimfx/gui/editW.fxml", null, null, EditWController.class);
        assert editWController != null;
        editWController.setMainApp(this);
        editWController.initializeWindow();
    }

    public void showEditGameWindow(Videojuego v) {
        Stage s = new Stage();
        s.setResizable(false);

        EditGameWController editGameWController = loadFXML("/com/bdii/stimfx/gui/editGameW.fxml", null, s, EditGameWController.class);
        assert editGameWController != null;
        editGameWController.setMainApp(this);
        editGameWController.initializeWindow(v, s);
    }

    public void showAdminScene()
    {
        AdminWController adminWController = loadFXML("/com/bdii/stimfx/gui/adminW.fxml", null, null, AdminWController.class);
        assert adminWController != null;
        adminWController.setMainApp(this);
        adminWController.initializeWindow();
    }

    public void showEditDemoW(Demo d) {
        Stage s = new Stage();
        s.setResizable(false);

        AdminEditDemoWController adminDemoWController = loadFXML("/com/bdii/stimfx/gui/adminEditDemoW.fxml", null, s, AdminEditDemoWController.class);
        assert adminDemoWController != null;
        adminDemoWController.setMainApp(this);
        adminDemoWController.initializeWindow(d, s);
    }

    public void showEditCompW(Torneo t) {
        Stage s = new Stage();
        s.setResizable(false);

        AdminEditCompWController adminEditCompWController = loadFXML("/com/bdii/stimfx/gui/adminEditCompW.fxml", null, s, AdminEditCompWController.class);
        assert adminEditCompWController != null;
        adminEditCompWController.setMainApp(this);
        adminEditCompWController.initializeWindow(t, s);
    }

    public void showEditReviewW(Resenha r, Videojuego v, String search, int from) {
        Stage s = new Stage();
        s.setResizable(false);

        ReviewEditWController reviewEditWController = loadFXML("/com/bdii/stimfx/gui/reviewEditW.fxml", null, s, ReviewEditWController.class);
        assert reviewEditWController != null;
        reviewEditWController.setMainApp(this);
        reviewEditWController.initializeWindow(r, s, v, search, from);
    }

    public void showEditDLCW(DLC d, Videojuego v) {
        Stage s = new Stage();
        s.setResizable(false);

        DLCEditWController dlcEditWController = loadFXML("/com/bdii/stimfx/gui/dlcEditW.fxml", null, s, DLCEditWController.class);
        assert dlcEditWController != null;
        dlcEditWController.setMainApp(this);
        dlcEditWController.initializeWindow(d, v, s);
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
