package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Comunidad;
import com.bdii.stimfx.aplicacion.Torneo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CompSearchItemController implements Controller {

    FachadaGUI fg;
    Torneo torn;
    CommunityWController superController;

    @FXML
    VBox itemVbox;
    @FXML
    ImageView iconImage;
    @FXML
    Label nameLabel;
    @FXML
    Label startLabel;
    @FXML
    Label endLabel;
    @FXML
    Label prizeLabel;
    @FXML
    HBox partHbox;

    public void initializeWindow(Torneo torn, int opt, CommunityWController superController)
    {
        this.superController = superController;
        this.torn = torn;
        iconImage.setImage(torn.getVideojuego().getImagen());
        nameLabel.setText(torn.getNombre());
        if(opt == 0)
        {
            startLabel.setText(torn.getFecha_inicio().toString());
            endLabel.setText(torn.getFecha_final().toString());
        }else
        {
            startLabel.setText(torn.getFecha_final().toString());
            endLabel.setText(torn.getGanador());
        }
        prizeLabel.setText(torn.getPremio() + "€");
    }


    @FXML
    public void enterTournament(MouseEvent event)
    {

    }

    public void setMainApp(FachadaGUI fg)
    {
        this.fg = fg;
    }
}
