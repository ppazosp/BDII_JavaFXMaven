package com.bdii.stimfx.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LibraryWController {
    FachadaGUI fg;

    @FXML
    public void showShopScene(MouseEvent event)
    {
        fg.showShopScene();
    }

    @FXML
    public void showMainScene(MouseEvent event)
    {
        fg.showMainWindow(false);
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
