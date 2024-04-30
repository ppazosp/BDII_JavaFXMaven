package com.bdii.stimfx.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

public class ProfileWController implements Controller {
    FachadaGUI fg;


    //MENU BAR
    @FXML
    HBox comMenu;
    @FXML
    HBox editMenu;
    @FXML
    HBox adminMenu;


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
    @FXML
    Label changesLabel;

    @FXML
    Timeline changesWait;

    public void initializeWindow() {

        if(!(fg.fa.usuario.isCompetitivePlayer())) comMenu.setVisible(false);
        if(!(fg.fa.usuario.isEditor())) editMenu.setVisible(false);
        if(!(fg.fa.usuario.isAdmin())) adminMenu.setVisible(false);


        changesWait = new Timeline(new KeyFrame(Duration.seconds(2), e -> hideChangesLabel()));

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

        fg.loading();

        new Thread(() -> {

            fg.fa.modificarUsuario(nameField.getText(), passField.getText(), emailField.getText(), profileImage.getImage());

            Platform.runLater(() -> {
                changesLabel.setVisible(true);
                changesWait.play();

                fg.loaded();
            });
        }).start();

    }

    private void hideChangesLabel() {
        changesLabel.setVisible(false);
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
        fg.showAdminScene();
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
