package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class LibraryWController implements Controller{
    FachadaGUI fg;

    @FXML
    VBox gamesVbox;

    public void initializeWindow()  {

        fg.loading();

        Task<Void> loadGamesTask = new Task<Void>() {
            @Override
            protected Void call() {
                List<Videojuego> userGames = fg.fa.consultarVideojuegosUsuario(fg.fa.usuario.getId());
                gamesVbox.getChildren().clear();

                System.out.println("Thread is running: " + isRunning());

                try {
                    for (Videojuego v : userGames) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/librarySearchItem.fxml"));
                        gamesVbox.getChildren().add(loader.load());

                        LibrarySearchItemController controller = loader.getController();
                        controller.setMainApp(fg);
                        controller.initializeWindow(v);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                fg.loaded();

                return null;
            }
        };
        Thread t = new Thread(loadGamesTask);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showProfileScene(MouseEvent event)
    {
        fg.showProfileScene();
    }

    @FXML
    public void showMainScene(MouseEvent event)
    {
        fg.showMainWindow(false);
    }

    @FXML
    public void showSocialScene(MouseEvent event)
    {
        fg.showSocialScene();
    }

    @FXML
    public void showCommunityScene(MouseEvent event)
    {
        fg.showCommunityScene();
    }

    @FXML
    public void showEditScene(MouseEvent event)
    {
        fg.showEditScene();
    }

    @FXML
    public void showAdminScene(MouseEvent event)
    {

    }

    @FXML
    public void showSettingsScene(MouseEvent event)
    {
        fg.showSettingsScene();
    }

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}
