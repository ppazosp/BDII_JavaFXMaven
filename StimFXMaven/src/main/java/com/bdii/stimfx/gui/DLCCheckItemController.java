package com.bdii.stimfx.gui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DLCCheckItemController implements Controller{
    FachadaGUI fg;

    @FXML
    HBox checkHbox;
    @FXML
    CheckBox checkBox;
    @FXML
    Label priceLabel;

    public HBox getCheckHbox() {
        return checkHbox;
    }
    public Label getPriceLabel() {
        return priceLabel;
    }
    public CheckBox getCheckBox() {
        return checkBox;
    }

    @Override
    public void setMainApp(FachadaGUI fg) {
        this.fg = fg;
    }
}
