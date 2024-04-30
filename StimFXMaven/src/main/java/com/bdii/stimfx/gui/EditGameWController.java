package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Editor;
import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Date;
import java.util.List;

public class EditGameWController implements Controller {
    FachadaGUI fg;
    Videojuego game;
    Stage window;

    @FXML
    ImageView bannerImage;
    @FXML
    ImageView iconImage;
    @FXML
    TextField nameField;
    @FXML
    TextArea descrpArea;
    @FXML
    TextField prizeField;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField trailerField;


    public void initializeWindow(Videojuego v, Stage window) {
        this.window = window;

        if (v != null) {
            this.game = v;

            bannerImage.setImage(game.getBanner());
            iconImage.setImage(game.getImagen());
            nameField.setText(game.getNombre());
            descrpArea.setText(game.getDescripcion());
            prizeField.setText(String.valueOf(game.getPrecio()));
            datePicker.setValue(game.getFechaSubida().toLocalDate());
            trailerField.setText(game.getTrailer());
        }
    }

    @FXML
    public void publishEdit(MouseEvent event) {
        window.close();
        fg.loading();

            new Thread(() -> {
                Videojuego v = new Videojuego(
                (game != null) ? game.getId() : -1,
                nameField.getText(),
                Date.valueOf(datePicker.getValue()),
                (game != null) ? game.getEditor() : new Editor(fg.fa.usuario.getId()),
                descrpArea.getText(), Double.parseDouble(prizeField.getText()),
                iconImage.getImage(),
                bannerImage.getImage(),
                trailerField.getText());
                fg.fa.publicarVideojuego(v);

                Platform.runLater(() -> fg.showEditScene());
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
    private void onDragDroppedIcon(DragEvent event) {
        handleDragDropped(event, iconImage);
    }

    @FXML
    private void onDragDroppedBanner(DragEvent event) {
        handleDragDropped(event, bannerImage);
    }

    private void handleDragDropped(DragEvent event, ImageView imageView) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()) {
            List<File> files = dragboard.getFiles();
            if (!files.isEmpty()) {
                File file = files.get(0);
                String fileName = file.getName().toLowerCase();
                if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
                    Image image = new Image(file.toURI().toString());
                    imageView.setImage(image);
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
        fg.showAdminScene();
    }

    @FXML
    public void showMainScene(MouseEvent event) {
        fg.showMainWindow(false);
    }

    public void setMainApp(FachadaGUI mainApp) {
        this.fg = mainApp;
    }
}
