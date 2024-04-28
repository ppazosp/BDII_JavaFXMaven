package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameSearchItemController implements Controller {

    FachadaGUI fg;
    Videojuego game;
    @FXML
    ImageView iconImage;
    @FXML
    Label nameLabel;
    @FXML
    HBox iconsHbox;
    @FXML
    Label dateLabel;
    @FXML
    Label priceLabel;

    public void initializeWindow(Videojuego game)
    {
        this.game = game;
        iconImage.setImage(game.getImagen());
        nameLabel.setText(game.getNombre());
        fg.showPlatforms(game, iconsHbox);
        dateLabel.setText(game.getFechaSubida().toString());
        priceLabel.setText(game.getPrecio()+"€");
    }

    @FXML
    public void showGameScene ()
    {
        fg.showGameScene(game);
    }

    public void setMainApp(FachadaGUI fg)
    {
        this.fg = fg;
    }
}
