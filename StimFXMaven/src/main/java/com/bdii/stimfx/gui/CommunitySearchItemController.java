package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Comunidad;
import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CommunitySearchItemController implements Controller {

    FachadaGUI fg;
    Comunidad com;

    @FXML
    HBox comHbox;
    @FXML
    ImageView iconImage;
    @FXML
    Label nameLabel;
    @FXML
    Label membersLabel;
    @FXML
    HBox enterHbox;

    public void initializeWindow(Comunidad com)
    {
        this.com = com;
        iconImage.setImage(com.getEscudo());
        nameLabel.setText(com.getNombre());
    }


    @FXML
    public void enterCommunity(MouseEvent event)
    {
        if(fg.) fg.fa.salirJugadorEquipo(fg.fa.usuario.getId());
        fg.fa.insertarJugadorEquipo(fg.fa.usuario.getId(), com);

        fg.showCommunityScene();
    }

    public void setMainApp(FachadaGUI fg)
    {
        this.fg = fg;
    }
}
