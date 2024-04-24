package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryWController implements Controller{
    FachadaGUI fg;

    @FXML
    VBox gamesVbox;

    public void initializeWindow()  {
        List<Videojuego> userGames = fg.fa.consultarVideojuegosUsuario(fg.fa.usuario.getId());
        gamesVbox.getChildren().clear();

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
    public void showCommunityScene(MouseEvent event)
    {
        fg.showCommunityScene();
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
