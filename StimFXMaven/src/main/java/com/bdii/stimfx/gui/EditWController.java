package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class EditWController implements Controller {
    FachadaGUI fg;

    @FXML
    VBox gamesVbox;
    @FXML
    VBox addVbox;


    public void initializeWindow() {
        List<Videojuego> myGames = fg.fa.consultarVideosjuegosEditor(fg.fa.usuario.getId());
        gamesVbox.getChildren().clear();

        try {
            int count = 0;
            HBox row = new HBox();
            row.setSpacing(5);
            for (Videojuego v : myGames) {
                if (count > 5) {
                    gamesVbox.getChildren().add(row);
                    count = 0;
                    row = new HBox();
                    row.setSpacing(5);
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/editItem.fxml"));
                row.getChildren().add(loader.load());

                EditItemController controller = loader.getController();
                controller.setMainApp(fg);
                controller.initializeWindow(v);

                count++;
            }
            if (count > 5) {
                gamesVbox.getChildren().add(row);
                row = new HBox();
                row.setSpacing(5);
            }
            row.getChildren().add(addVbox);
            gamesVbox.getChildren().add(row);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void showProfileScene(MouseEvent event) {
        fg.showProfileScene();
    }

    @FXML
    public void showLibraryScene(MouseEvent event) {
        fg.showLibraryScene();
    }

    @FXML
    public void showSocialScene(MouseEvent event) {
        fg.showSocialScene();
    }

    @FXML
    public void showCommunityScene(MouseEvent event) {
        fg.showCommunityScene();
    }

    @FXML
    public void showSettingsScene(MouseEvent event) {
        fg.showSettingsScene();
    }

    @FXML
    public void showAdminScene(MouseEvent event) {

    }

    @FXML
    public void showMainScene(MouseEvent event) {
        fg.showMainWindow(false);
    }

    public void setMainApp(FachadaGUI mainApp) {
        this.fg = mainApp;
    }
}
