package com.bdii.stimfx.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileWController implements Controller, Initializable {
    FachadaGUI fg;
    @FXML
    Label userField;
    @FXML
    Label nGamesLabel;
    @FXML
    Label nWinsLabel;
    @FXML
    TextField nameField;
    @FXML
    PasswordField passField;
    @FXML
    TextField emailField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userField.setText(fg.fa.usuario.getId());
        nGamesLabel.setText(fg.fa.contarJuegosUsuario(fg.fa.usuario.getId()).toString());
        //nWinsLabel.setText(fg.fa.ga(fg.fa.victorias.getId()).toString());
        nameField.setText(fg.fa.usuario.getNombre());
        passField.setText(fg.fa.usuario.getContrasena());
        emailField.setText(fg.fa.usuario.getEmail());
    }

    @FXML
    public void showMainScene(MouseEvent event)
    {
        fg.showMainWindow(false);
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

    @Override
    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}
