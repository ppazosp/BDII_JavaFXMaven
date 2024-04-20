package com.bdii.stimfx.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInWController implements Controller{

    FachadaGUI fg;

    @FXML
    Label credentialsErrorLabel;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    @FXML
    public void showSiginScene(ActionEvent event)
    {
        fg.showSigninScene();
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

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}