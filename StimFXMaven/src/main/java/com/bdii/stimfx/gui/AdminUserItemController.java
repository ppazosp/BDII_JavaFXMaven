package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Usuario;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminUserItemController implements Controller {

    FachadaGUI fg;
    Usuario user;
    AdminWController superController;

    @FXML
    ImageView iconImage;
    @FXML
    Label nameLabel;
    @FXML
    HBox adminHbox;


    public void initializeWindow(Usuario user, AdminWController superController)
    {
        this.user = user;
        this.superController = superController;

        iconImage.setImage(user.getFotoPerfil());
        nameLabel.setText(user.getId());
    }

    public void setMainApp(FachadaGUI fg)
    {
        this.fg = fg;
    }
}
