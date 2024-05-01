package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.DLC;
import com.bdii.stimfx.aplicacion.Resenha;
import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DLCEditWController implements Controller {
    FachadaGUI fg;
    DLC dlc;
    Stage window;

    Videojuego game;
    String search;
    int from;

    @FXML
    TextField nameField;
    @FXML
    TextField priceField;

    int val = 5;

    public void initializeWindow(DLC dlc, Videojuego game, Stage window) {

        this.window = window;
        this.game = game;

        if(dlc != null)
        {
            this.dlc = dlc;

            nameField.setText(dlc.getNombre());
            priceField.setText(String.valueOf(dlc.getPrecio()));
        }

    }

    @FXML
    public void publishEdit(MouseEvent event) {

        this.window.close();

        DLC d = new DLC(
                game.getId(),
                (dlc != null) ? dlc.getIdDLC() : -1,
                nameField.getText(),
                Double.parseDouble(priceField.getText()));

        fg.fa.publicarDLC(d);

        fg.showEditGameWindow(game);
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
