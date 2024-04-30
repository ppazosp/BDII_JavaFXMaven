package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Demo;
import com.bdii.stimfx.aplicacion.FachadaAplicacion;
import com.bdii.stimfx.aplicacion.Resenha;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.InputStream;

public class ReviewItemController implements Controller {

    FachadaGUI fg;
    Resenha res;


    @FXML
    TextArea reviewArea;
    @FXML
    Label likesLabel;
    @FXML
    Label dislikesLabel;
    @FXML
    Label nameLabel;


    @FXML
    HBox starsHbox;
    @FXML
    HBox star1;
    @FXML
    HBox star2;
    @FXML
    HBox star3;
    @FXML
    HBox star4;
    @FXML
    HBox star5;


    public void initializeWindow(Resenha res)
    {
        this.res = res;

        reviewArea.setText(res.getComentario());
        nameLabel.setText(res.getId_usuario());

        switch(res.getValoracion())
        {
            case 1:
                starsHbox.getChildren().remove(star2);
            case 2:
                starsHbox.getChildren().remove(star3);
            case 3:
                starsHbox.getChildren().remove(star4);
            case 4:
                starsHbox.getChildren().remove(star5);
            default:

        }
    }




    public void setMainApp(FachadaGUI fg)
    {
        this.fg = fg;
    }
}
