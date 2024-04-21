package com.bdii.stimfx.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameSearchItemController{

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

    public ImageView getIconImage() {
        return iconImage;
    }
    public Label getNameLabel() {
        return nameLabel;
    }
    public HBox getIconsHbox() {
        return iconsHbox;
    }
    public Label getDateLabel() {
        return dateLabel;
    }
    public Label getPriceLabel() {
        return priceLabel;
    }
}
