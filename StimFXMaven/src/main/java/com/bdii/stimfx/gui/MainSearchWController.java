package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

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
            for (Videojuego v : gamesList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/gameSearchItem.fxml"));
                searchVbox.getChildren().add(loader.load());

                GameSearchItemController controller = loader.getController();
                controller.setMainApp(fg);
                controller.initializeWindow(v);
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
    public void showSocialScene(MouseEvent event)
    {
        fg.showSocialScene();
    }

    @FXML
    public void showCommunityScene(MouseEvent event)
    {

    }

    @FXML
    public void showEditScene(MouseEvent event)
    {

    }

    @FXML
    public void showAdminScene(MouseEvent event)
    {

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