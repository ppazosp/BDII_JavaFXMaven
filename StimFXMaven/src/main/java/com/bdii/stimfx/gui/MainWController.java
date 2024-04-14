package com.bdii.stimfx.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainWController {

    FachadaGUI fg;

    @FXML
    Label credentialsErrorLabel;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    @FXML
    public void showSiginWindow(ActionEvent event)
    {
        fg.showSigninWindow();
    }

    public void loginCheck (ActionEvent event)
    {
        if(fg.checkCredentials(usernameField.toString(), passwordField.toString()))
        {
            fg.showMainWindow();
        }
        else{
            usernameField.clear();
            passwordField.clear();
            credentialsErrorLabel.setVisible(true);
        }
    }

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}