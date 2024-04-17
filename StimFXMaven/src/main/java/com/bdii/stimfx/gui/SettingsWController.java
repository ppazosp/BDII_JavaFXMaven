package com.bdii.stimfx.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SettingsWController {
    FachadaGUI fg;

    @FXML
    public void showShopScene(MouseEvent event)
    {
        fg.showShopScene();
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
    public void showMainScene(MouseEvent event)
    {
        fg.showMainWindow(false);
    }

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}
