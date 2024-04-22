package com.bdii.stimfx.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignInWController implements Controller{

    FachadaGUI fg;
    @FXML
    TextField emailField;

    @FXML
    TextField userField;

    @FXML
    TextField pass2Field;


    public void setMainApp(FachadaGUI mainApp) {
        this.fg = mainApp;
    }

    public void loginCheck (ActionEvent event)
    {

        if(fg.checkCredentials(usernameField.getText(), passwordField.getText()))
        {
            fg.showMainWindow(true);
        }
        else{
            usernameField.clear();
            passwordField.clear();
            credentialsErrorLabel.setVisible(true);
        }
    }

    public void showMainWindow()
    {
        fg.showMainWindow(true);
    }
}