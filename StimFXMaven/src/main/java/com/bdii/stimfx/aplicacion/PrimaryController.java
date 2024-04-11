package com.bdii.stimfx.aplicacion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class PrimaryController {

    @FXML
    private Button LogInButton;

    @FXML
    private void switchToSecondary() throws IOException {
        FachadaAplicacion.setRoot("secondary");
    }
}