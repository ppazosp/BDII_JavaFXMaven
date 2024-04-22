package com.bdii.stimfx.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignInWController implements Controller{

    FachadaGUI fg;
    @FXML
    TextField emailField;

    @FXML
    TextField nameField;
    @FXML
    TextField userField;
    @FXML
    TextField passField;
    @FXML
    TextField pass2Field;
    @FXML
    Label errorLabel;


    public void setMainApp(FachadaGUI mainApp) {
        this.fg = mainApp;
    }

    public void signInCheck (ActionEvent event)
    {
        if(pass2Field.getText().equals(passField.getText())) {
            if (fg.register(userField.getText(), passField.getText(), nameField.getText(), emailField.getText())) {
                fg.showMainWindow(true);
            }
            else{
                errorLabel.setText("Ese id de usuario ya existe");
                errorLabel.setVisible(true);
            }
        }
        else{
            errorLabel.setText("Las dos contraseñas no coinciden");
            errorLabel.setVisible(true);
            //credentialsErrorLabel.setVisible(true);
        }
        userField.clear();
        passField.clear();
        pass2Field.clear();
    }

    public void showMainWindow()
    {
        fg.showMainWindow(true);
    }
}