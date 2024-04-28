package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.DLC;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DLCCheckItemController implements Controller{
    FachadaGUI fg;

    DLC dlc;

    @FXML
    HBox checkHbox;
    @FXML
    CheckBox checkBox;
    @FXML
    Label priceLabel;

    public void initializeWindow(DLC dlc) {
        this.dlc = dlc;

        checkBox.setText(dlc.getNombre());
        priceLabel.setText(dlc.getPrecio() + "€");
        if (fg.fa.tieneDLC(fg.fa.usuario, dlc)) {
            checkBox.setSelected(true);
            checkHbox.setDisable(true);
        }
    }

    @Override
    public void setMainApp(FachadaGUI fg) {
        this.fg = fg;
    }
}
