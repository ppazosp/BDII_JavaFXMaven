package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Demo;
import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

public class GameWController implements Controller, Initializable {

    FachadaGUI fg;
    //PANE
    @FXML
    AnchorPane rightPane;

    @FXML
    TextField searchBar;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    public void loadGameWindow(MouseEvent event) {
        fg.loadGameWindow();
    }

    @FXML
    public void showProfileScene(MouseEvent event)
    {
        fg.showProfileScene();
    }

    @FXML
    public void showLibraryScene(MouseEvent event)
    {
        fg.showLibraryScene();
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

    @FXML
    public void showSearchScene(ActionEvent event)
    {
        fg.showSearchScene(searchBar.getText());
    }

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}