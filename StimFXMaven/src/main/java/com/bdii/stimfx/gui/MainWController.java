package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWController implements Controller, Initializable {

    FachadaGUI fg;

    //TOP1
    @FXML
    ImageView top1IconImage;
    @FXML
    Label top1NameLabel;
    @FXML
    Label top1DateLabel;
    @FXML
    Label top1PriceLabel;
    @FXML
    HBox top1IconsHbox;
    @FXML
    ImageView top1MicrosoftIcon;
    @FXML
    ImageView top1AppleIcon;
    @FXML
    ImageView top1PlayStationIcon;
    @FXML
    ImageView top1XboxIcon;
    @FXML
    ImageView top1AndroidIcon;


    //TOP2
    @FXML
    ImageView top2IconImage;
    @FXML
    Label top2NameLabel;
    @FXML
    Label top2DateLabel;
    @FXML
    Label top2PriceLabel;
    @FXML
    ImageView top2MicrosoftIcon;
    @FXML
    ImageView top2AppleIcon;
    @FXML
    ImageView top2PlayStationIcon;
    @FXML
    ImageView top2XboxIcon;
    @FXML
    ImageView top2AndroidIcon;


    //TOP3
    @FXML
    ImageView top3IconImage;
    @FXML
    Label top3NameLabel;
    @FXML
    Label top3DateLabel;
    @FXML
    Label top3PriceLabel;
    @FXML
    ImageView top3MicrosoftIcon;
    @FXML
    ImageView top3AppleIcon;
    @FXML
    ImageView top3PlayStationIcon;
    @FXML
    ImageView top3XboxIcon;
    @FXML
    ImageView top3AndroidIcon;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Videojuego> topSellers = fg.fa.consultaVideoJuegosInicio();
        if(topSellers != null)
        {
            if(topSellers.get(0)!= null)
            {
                Videojuego top1 = topSellers.get(0);
                top1NameLabel.setText(top1.getNombre());
                top1DateLabel.setText(top1.getFechaSubida().toString());
                top1PriceLabel.setText(top1.getPrecio()+"€");

                if(topSellers.get(1)!= null) {
                    Videojuego top2 = topSellers.get(1);
                    top2NameLabel.setText(top2.getNombre());
                    top2DateLabel.setText(top2.getFechaSubida().toString());
                    top2PriceLabel.setText(top2.getPrecio()+"€");

                    if(topSellers.get(2)!= null) {
                        Videojuego top3 = topSellers.get(2);
                        top3NameLabel.setText(top3.getNombre());
                        top3DateLabel.setText(top3.getFechaSubida().toString());
                        top3PriceLabel.setText(top3.getPrecio()+"€");
                    }
                }

            }
        };
    }


    @FXML
    public void loadGameWindow(MouseEvent event) {
        fg.loadGameWindow();
    }

    @FXML
    public void showProfileScene(MouseEvent event)
    {
        fg.showProfileScene();
    }

    @FXML
    public void showLibraryScene(MouseEvent event)
    {
        fg.showLibraryScene();
    }

    @FXML
    public void showCommunityScene(MouseEvent event)
    {
        fg.showCommunityScene();
    }

    @FXML
    public void showSettingsScene(MouseEvent event)
    {
        fg.showSettingsScene();
    }

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}