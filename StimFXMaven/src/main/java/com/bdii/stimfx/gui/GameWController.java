package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Categoria;
import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class GameWController implements Controller {

    FachadaGUI fg;
    Videojuego game;

    //PANE
    @FXML
    AnchorPane rightPane;

    @FXML
    ImageView bannerImage;
    @FXML
    Label nameLabel;
    @FXML
    Label dateLabel;
    @FXML
    HBox buyHbox;
    @FXML
    Label priceLabel;
    @FXML
    TextArea descrpArea;
    @FXML
    Label creatorLabel;
    @FXML
    Label downloadsLabel;
    @FXML
    VBox catVbox;
    @FXML
    HBox reviewsHbox;
    @FXML
    HBox addHbox;


    public void setVideojuego(Videojuego v) {this.game = v;}

    public void initializeWindow()
    {
        bannerImage.setImage(game.getBanner());
        nameLabel.setText(game.getNombre());
        dateLabel.setText("Fecha de publicacion: "+game.getFechaSubida().toString());
        priceLabel.setText(game.getPrecio()+"€");
        descrpArea.setText(game.getDescripcion());
        creatorLabel.setText("Creador: "+game.getEditor().getId());
        downloadsLabel.setText("Descargas: " + game.getNumDescargas());
        catVbox.getChildren().clear();
        List<String> cats = fg.fa.consultarCategoriasVideojuego(game.getId());
        for(String s : cats){
            Label l = new Label(s);
            catVbox.getChildren().add(l);
        }
    }

    @FXML
    public void showMainScene(MouseEvent event)
    {
        fg.showMainWindow(false);
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

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}