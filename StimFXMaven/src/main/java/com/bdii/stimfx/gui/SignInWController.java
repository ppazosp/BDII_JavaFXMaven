package com.bdii.stimfx.gui;

public class SignInWController implements Controller{

    FachadaGUI fg;

    public void setMainApp(FachadaGUI mainApp) {
        this.fg = mainApp;
    }
    public void showMainWindow()
    {
        fg.showMainWindow(true);
    }
}