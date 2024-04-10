module com.bdii.stimfx.aplicacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.bdii.stimfx.aplicacion to javafx.fxml;
    exports com.bdii.stimfx.aplicacion;
}