module com.bdii.stimfx.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.bdii.stimfx.gui to javafx.fxml;
    exports com.bdii.stimfx.gui;
}