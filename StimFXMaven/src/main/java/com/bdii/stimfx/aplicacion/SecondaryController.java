package com.bdii.stimfx.aplicacion;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        FachadaAplicacion.setRoot("primary");
    }
}