package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Demo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class AdminEditDemoWController implements Controller {
    FachadaGUI fg;
    Demo demo;
    Stage window;

    @FXML
    ImageView iconImage;
    @FXML
    TextField nameField;
    @FXML
    TextField monthField;
    @FXML
    TextField yearField;


    public void initializeWindow(Demo d, Stage window) {
        this.window = window;

        if (d != null) {
            this.demo = d;

            iconImage.setImage(demo.getImagen());
            nameField.setText(demo.getNombre());
            monthField.setText(String.valueOf(demo .getMes()));
            yearField.setText(String.valueOf(demo .getAno()));
        }
    }

    @FXML
    public void publishEdit(MouseEvent event) {
        window.close();
        fg.loading();

            new Thread(() -> {
                Demo d = new Demo(nameField.getText(),
                        Integer.parseInt(monthField.getText()),
                        Integer.parseInt(yearField.getText()),
                        iconImage.getImage(),
                        fg.fa.usuario.getId());

                fg.fa.publicarDemo(d);

                Platform.runLater(() -> fg.showAdminScene());
            }).start();
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
                String fileName = file.getName().toLowerCase();
                if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
                    Image image = new Image(file.toURI().toString());
                    iconImage.setImage(image);
                }
            }
        }
        event.setDropCompleted(true);
        event.consume();
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
