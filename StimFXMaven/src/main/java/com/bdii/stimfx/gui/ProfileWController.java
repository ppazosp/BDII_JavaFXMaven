package com.bdii.stimfx.gui;
import com.bdii.stimfx.aplicacion.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileWController implements Controller, Initializable {
    FachadaGUI fg;

    @FXML
    ImageView profileImage;
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
        if(fg.fa.usuario.getFotoPerfil() != null) profileImage.setImage(fg.fa.usuario.getFotoPerfil());
        userField.setText(fg.fa.usuario.getId());
        nGamesLabel.setText(fg.fa.contarJuegosUsuario(fg.fa.usuario.getId()).toString());
        nWinsLabel.setText(String.valueOf(fg.fa.torneosGanados(fg.fa.usuario)));
        nameField.setText(fg.fa.usuario.getNombre());
        passField.setText(fg.fa.usuario.getContrasena());
        emailField.setText(fg.fa.usuario.getEmail());
    }
    @FXML
    public void modificarUsuario(MouseEvent event) {
        fg.fa.modificarUsuario(nameField.getText(), passField.getText(), emailField.getText(), profileImage.getImage());
    }

    @FXML
    private void onDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    @FXML
    private void onDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()) {
            List<File> files = dragboard.getFiles();
            if (!files.isEmpty()) {
                File file = files.get(0);
                if (file.getName().toLowerCase().endsWith(".png") || file.getName().toLowerCase().endsWith(".jpg")) {
                    javafx.scene.image.Image image = new Image(file.toURI().toString());
                    profileImage.setImage(image);
                }
            }
        }
        event.setDropCompleted(true);
        event.consume();
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
