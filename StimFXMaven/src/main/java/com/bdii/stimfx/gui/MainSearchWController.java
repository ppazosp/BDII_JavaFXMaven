package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Demo;
import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

public class MainSearchWController implements Controller {

    FachadaGUI fg;

    @FXML
    TextField searchBar;
    @FXML
    Label resultsLabel;
    @FXML
    VBox searchVbox;

    public void setSearchBar(String text) {
        searchBar.setText(text);
    }

    @FXML
    public void showSearchResults()
    {
        searchVbox.getChildren().clear();
        resultsLabel.setText("Resultados para \"" + searchBar.getText() + "\"");
        List<Videojuego> gamesList = fg.fa.consultarVideojuegos(searchBar.getText());
        searchBar.clear();

        try {
            for (Videojuego v : gamesList) { // Add 10 instances as an example
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/gameSearchItem.fxml"));
                searchVbox.getChildren().add(loader.load());

                GameSearchItemController controller = loader.getController();
                controller.setMainApp(fg);

                controller.getIconImage().setImage(v.getImagen());
                controller.getNameLabel().setText(v.getNombre());
                fg.showPlatforms(v, controller.getIconsHbox());
                controller.getDateLabel().setText(v.getFechaSubida().toString());
                controller.getPriceLabel().setText(v.getPrecio()+"€");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void showMainScene(MouseEvent event)
    {
        fg.showMainWindow(false);
    }

    @FXML
    public void showProfileScene(MouseEvent event)
    {
        fg.showProfileScene();
    }

    @FXML
    public void showLibraryScene(MouseEvent event)
    {
        fg.showLibraryScene();
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