package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Demo;
import com.bdii.stimfx.aplicacion.Torneo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AdminCompItemController implements Controller {

    FachadaGUI fg;
    Torneo torn;
    AdminWController superController;

    @FXML
    ImageView iconImage;
    @FXML
    Label nameLabel;
    @FXML
    Label dateLabel;

    public void initializeWindow(Torneo torn, AdminWController superController)
    {
        this.torn = torn;
        this.superController = superController;

        iconImage.setImage(torn.getVideojuego().getImagen());
        nameLabel.setText(torn.getNombre());
        dateLabel.setText(torn.getFecha_inicio().toString());

    }

    public void setMainApp(FachadaGUI fg)
    {
        this.fg = fg;
    }
}
