package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.DLC;
import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DLCItemController implements Controller {

    FachadaGUI fg;
    DLC dlc;
    EditGameWController superController;

    @FXML
    Label nameLabel;


    public void initializeWindow(DLC dlc, EditGameWController superControlller) {
        this.superController = superControlller;

        this.dlc = dlc;
        nameLabel.setText(dlc.getNombre());
    }

    @FXML
    public void editDLC(MouseEvent event)
    {
        fg.showEditDLCW(dlc, superController.game );
        superController.window.close();

    }

    public void setMainApp(FachadaGUI fg) {
        this.fg = fg;
    }
}
