package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

public class MainWController implements Controller, Initializable {

    FachadaGUI fg;
    //PROXIMO LANZAMIENTO
    @FXML
    AnchorPane rightPane;


    @FXML
    VBox nextLaunchVbox;
    @FXML
    ImageView launchIconImage;
    @FXML
    Label launchNameLabel;
    @FXML
    Label launchDaysLabel;
    @FXML
    HBox launchIconsHbox;
    @FXML
    ImageView launchMicrosoftIcon;
    @FXML
    ImageView launchAppleIcon;
    @FXML
    ImageView launchPlayStationIcon;
    @FXML
    ImageView launchXboxIcon;
    @FXML
    ImageView launchAndroidIcon;


    @FXML
    VBox topSellersVbox;

    //TOP1
    @FXML
    HBox top1Hbox;
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
    HBox top2Hbox;
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
    HBox top3Hbox;
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
        Videojuego nextLaunch = fg.fa.proximoVideojuego();
        if (nextLaunch != null) {
            launchNameLabel.setText(nextLaunch.getNombre());
            long daysToLaunch = ChronoUnit.DAYS.between(LocalDate.now(ZoneId.systemDefault()), nextLaunch.getFechaSubida().toLocalDate());
            launchDaysLabel.setText(Long.toString(daysToLaunch));
            System.out.println(Long.toString(daysToLaunch));
        }else {
            rightPane.getChildren().remove(nextLaunchVbox);
            nextLaunchVbox= null;
            topSellersVbox.setLayoutX(200);
        }

        List<Videojuego> topSellers = fg.fa.consultaVideoJuegosInicio();
        if(topSellers != null)
        {
            if(topSellers.get(0)!= null) {
                Videojuego top1 = topSellers.get(0);
                top1NameLabel.setText(top1.getNombre());
                top1DateLabel.setText(top1.getFechaSubida().toString());
                top1PriceLabel.setText(top1.getPrecio() + "€");
            }else
            {
                topSellersVbox.getChildren().remove(top1Hbox);
                top1Hbox = null;
            }

            if(topSellers.get(1)!= null) {
                Videojuego top2 = topSellers.get(1);
                top2NameLabel.setText(top2.getNombre());
                top2DateLabel.setText(top2.getFechaSubida().toString());
                top2PriceLabel.setText(top2.getPrecio() + "€");
            }else
            {
                topSellersVbox.getChildren().remove(top2Hbox);
                top2Hbox = null;
            }

            if(topSellers.get(2)!= null) {
                Videojuego top3 = topSellers.get(2);
                top3NameLabel.setText(top3.getNombre());
                top3DateLabel.setText(top3.getFechaSubida().toString());
                top3PriceLabel.setText(top3.getPrecio()+"€");
            }else
            {
                topSellersVbox.getChildren().remove(top3Hbox);
                top3Hbox = null;
            }
        }else
        {
            rightPane.getChildren().remove(topSellersVbox);
            if(nextLaunchVbox != null) nextLaunchVbox.setLayoutX(390);
        }
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