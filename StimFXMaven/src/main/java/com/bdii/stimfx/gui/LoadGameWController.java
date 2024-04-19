package com.bdii.stimfx.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadGameWController implements Initializable, Controller {
    FachadaGUI fg;

    @FXML
    WebView gameLoaded;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load the URL into the WebView
        gameLoaded.getEngine().setJavaScriptEnabled(true);
        gameLoaded.getEngine().load("https://ppazosp.itch.io/robin-run");
    }

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}
